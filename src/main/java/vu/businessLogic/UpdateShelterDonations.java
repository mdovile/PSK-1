package vu.businessLogic;

import lombok.Getter;
import lombok.Setter;
import vu.entities.Shelter;
import vu.interceptors.MyInterceptor;
import vu.persistence.SheltersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
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

    @PostConstruct
    private void init() {
        System.out.println("UpdateShelterDonations INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer shelterId = Integer.parseInt(requestParameters.get("shelterId"));
        this.shelter = sheltersDAO.findOne(shelterId);
    }

    @Transactional
    @MyInterceptor
    public String updateShelterDonations() {
        if(paymentVerificator.verifyPayment()) {
            this.shelter.setDonations(donationCalculator.calculateFunds(this.shelter, this.amount));
            sheltersDAO.update(this.shelter);
        }
        return "cats.xhtml?shelterId=" + this.shelter.getId() + "&faces-redirect=true";
    }
}
