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
        loadData();
    }

    public void addResult(Result result) {
        results.addFirst(result);
    }

    public void loadData() {
        List<Result> resultsList = entityManager.createQuery("SELECT r FROM Result r", Result.class)
                .getResultList();

        results = new LinkedList<>();

        for (int i = resultsList.size() - 1; i >= 0; i--) {
            results.add(resultsList.get(i));
        }
    }

    @Transactional
    public void save(Result entity) {
        entityManager.persist(entity);
    }
}
