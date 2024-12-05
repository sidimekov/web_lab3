package beans;

import entity.Result;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;

@Getter
@Named("resultBean")
@SessionScoped
public class ResultBean implements Serializable {
    private LinkedList<Result> results;

    @PostConstruct
    public void init() {
        results = new LinkedList<>();
    }

    public void addResult(Result result) {
        results.addFirst(result);
    }
}
