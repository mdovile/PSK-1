package vu.persistence;

import vu.entities.Cat;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CatsDAO {

    @Inject
    private EntityManager em;

    public void persist(Cat cat){
        this.em.persist(cat);
    }

    public Cat findOne(Integer id){
        return em.find(Cat.class, id);
    }

    public Cat update(Cat cat){
        return em.merge(cat);
    }
}
