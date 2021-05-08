package vu.businessLogic;

import vu.entities.Shelter;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import java.io.Serializable;

@SessionScoped
@Specializes
public class DonationCalculatorWithTaxes extends DonationCalculator implements Serializable {

    public double calculateFunds(Shelter shelter, double donation) {
        return shelter.getDonations() + donation * 0.85;
    }
}
