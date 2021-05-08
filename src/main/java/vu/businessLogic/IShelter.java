package vu.businessLogic;

import vu.entities.Shelter;

public interface IShelter {
    void init();
    String createShelter();
    Shelter getShelterToCreate();
    void setShelterToCreate(Shelter shelterToCreate);
    void loadAllShelters();
}
