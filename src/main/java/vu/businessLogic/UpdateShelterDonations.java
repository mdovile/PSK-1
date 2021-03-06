package vu.businessLogic;

import lombok.Getter;
import lombok.Setter;
import vu.entities.Shelter;
import vu.interceptors.MyInterceptor;
import vu.persistence.SheltersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequestScoped
@Named
@Getter
@Setter
public class UpdateShelterDonations implements Serializable {

    private Shelter shelter;
    private double amount;
    @Inject
    private DonationCalculator donationCalculator;

    @Inject
    private SheltersDAO sheltersDAO;

    @Inject
    private PaymentVerificator paymentVerificator;
    private CompletableFuture<Boolean> paymentVerificationTask = null;

    @PostConstruct
    private void init() {
        System.out.println("UpdateShelterDonations INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer shelterId = Integer.parseInt(requestParameters.get("shelterId"));
        this.shelter = sheltersDAO.findOne(shelterId);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @MyInterceptor
    public String updateShelterDonations() {
        paymentVerificationTask = CompletableFuture.supplyAsync(() -> paymentVerificator.verifyPayment());
        Boolean result = paymentVerificationTask.join();

        if (result) {
            try {
                this.shelter.setDonations(donationCalculator.calculateFunds(this.shelter, this.amount));
                sheltersDAO.update(this.shelter);
            } catch (OptimisticLockException e) {
                System.out.println(e);
                return "cats.xhtml?shelterId=" + this.shelter.getId() + "&faces-redirect=true" + "&error=optimistic-lock-exception";
            }
        }
        return "cats.xhtml?shelterId=" + this.shelter.getId() + "&faces-redirect=true";
    }

    public boolean isPaymentVerificationRunning() {
        return paymentVerificationTask != null && !paymentVerificationTask.isDone();
    }

    public Boolean getPaymentVerificationStatus() throws ExecutionException, InterruptedException {
        if (paymentVerificationTask == null) {
            return false;
        } else if (isPaymentVerificationRunning()) {
            return false;
        }
        return paymentVerificationTask.get();
    }
}
