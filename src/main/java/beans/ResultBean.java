package beans;

import entity.Result;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.*;

@Named("resultBean")
@SessionScoped
public class ResultBean implements Serializable {
    private LinkedList<Result> results;

    @PostConstruct
    public void init() {
        results = new LinkedList<>();
    }

    public List<Result> getResults() {
        return results;
    }

    public void addResult(Result result) {
        results.addFirst(result);
    }
}
