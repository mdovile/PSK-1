package vu.businessLogic;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
//import vu.interceptors.LoggedInvocation;
import vu.persistence.CatsDAO;
import vu.persistence.SheltersDAO;
import vu.entities.Cat;
import vu.entities.Shelter;

@Model
public class CatsInShelter implements Serializable {

    @Inject
    private SheltersDAO sheltersDAO;

    @Inject
    private CatsDAO catsDAO;

    @Getter @Setter
    private Shelter shelter;

    @Getter @Setter
    private Cat catToCreate = new Cat();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer shelterId = Integer.parseInt(requestParameters.get("shelterId"));
        this.shelter = sheltersDAO.findOne(shelterId);
    }

    @Transactional
    public String createCat() {
        catToCreate.setShelter(this.shelter);
        catsDAO.persist(catToCreate);
        return "cats?faces-redirect=true&shelterId=" + this.shelter.getId();
    }
}
