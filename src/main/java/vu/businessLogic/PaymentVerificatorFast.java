package vu.businessLogic;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;

@Alternative
@ApplicationScoped
public class PaymentVerificatorFast implements PaymentVerificator, Serializable {
    @Override
    public boolean verifyPayment() {
        try {
            Thread.sleep(1000);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
