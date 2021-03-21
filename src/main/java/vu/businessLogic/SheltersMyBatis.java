package vu.businessLogic;

import lombok.Getter;
import lombok.Setter;
import vu.mybatis.dao.ShelterMapper;
import vu.mybatis.model.Shelter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class SheltersMyBatis {
    @Inject
    private ShelterMapper shelterMapper;

    @Getter
    private List<Shelter> allShelters;

    @Getter @Setter
    private Shelter shelterToCreate = new Shelter();

    @PostConstruct
    public void init() {
        this.loadAllShelters();
    }

    private void loadAllShelters() {
        this.allShelters = shelterMapper.selectAll();
    }

    @Transactional
    public String createShelter() {
        shelterMapper.insert(shelterToCreate);
        return "/myBatis/shelters?faces-redirect=true";
    }
}
