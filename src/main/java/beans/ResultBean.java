package beans;

import entity.Result;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.*;

@Getter
@Named("resultBean")
@SessionScoped
public class ResultBean implements Serializable {
    private LinkedList<Result> results;

    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        results = new LinkedList<>();
    }

    public void addResult(Result result) {
        results.addFirst(result);
    }

//    public List<Result> getAllResults() {
//        return entityManager.createQuery("SELECT r FROM Result r", Result.class).getResultList();
//    }
    @Transactional
    public void save(Result entity) {
        entityManager.persist(entity);
    }
}
