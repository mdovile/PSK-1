package vu.decorators;

import vu.businessLogic.IShelter;
import vu.entities.Shelter;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Decorator
public abstract class SheltersDecoratorGovernmentSupported implements IShelter {

    @Inject
    @Delegate
    @Any
    IShelter shelter;

    @Transactional
    public String createShelter() {
        Shelter toCreate = shelter.getShelterToCreate();
        if(toCreate.getTitle().startsWith("GOV")) {
            toCreate.setDonations(10000);
        }
        shelter.setShelterToCreate(toCreate);
        return shelter.createShelter();
    }
}
