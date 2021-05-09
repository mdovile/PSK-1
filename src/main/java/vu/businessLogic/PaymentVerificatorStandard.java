package vu.businessLogic;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class PaymentVerificatorStandard implements PaymentVerificator, Serializable {

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
