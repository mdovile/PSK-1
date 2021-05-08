package vu.businessLogic;

import vu.entities.Shelter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class DonationCalculator implements Serializable{

    public double calculateFunds(Shelter shelter, double donation) {
        return shelter.getDonations() + donation;
    }
}
