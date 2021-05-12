package vu.persistence;

import vu.entities.Shelter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SheltersDAO {

    @Inject
    private EntityManager em;

    public List<Shelter> loadAll() {
        return em.createNamedQuery("Shelter.findAll", Shelter.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Shelter shelter){
        this.em.persist(shelter);
    }

    public Shelter findOne(Integer id) {
        return em.find(Shelter.class, id);
    }

    public Shelter update(Shelter shelter){
        shelter = em.merge(shelter);
        em.flush();
        return shelter;
    }
}
