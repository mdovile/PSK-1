package vu.businessLogic;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class PaymentVerificatorStandart implements PaymentVerificator, Serializable {

    @Override
    public boolean verifyPayment() {
        try {
            Thread.sleep(5000);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
