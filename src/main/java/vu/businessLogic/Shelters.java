package vu.businessLogic;

import lombok.Getter;
import lombok.Setter;
import vu.persistence.SheltersDAO;
import vu.entities.Shelter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Shelters implements IShelter {

    @Inject
    private SheltersDAO sheltersDAO;

    @Getter @Setter
    private Shelter shelterToCreate = new Shelter();

    public Shelter getShelterToCreate() {
        return shelterToCreate;
    }

    public void setShelterToCreate(Shelter shelterToCreate) {
        this.shelterToCreate = shelterToCreate;
    }

    @Getter
    private List<Shelter> allShelters;

    @PostConstruct
    public void init(){
        loadAllShelters();
    }

    @Transactional
    public String createShelter(){
        this.sheltersDAO.persist(shelterToCreate);
        return "index?faces-redirect=true";
    }

    public void loadAllShelters(){
        this.allShelters = sheltersDAO.loadAll();
    }
}
