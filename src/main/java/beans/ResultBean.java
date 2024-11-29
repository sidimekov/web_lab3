package beans;

import entity.ResultEntity;
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ResultBean implements Serializable {
    private List<ResultEntity> results;

    @PostConstruct
    public void init() {
        results = new ArrayList<>();
    }

    public List<ResultEntity> getResults() {
        return results;
    }

    public void addResult(ResultEntity result) {
        results.add(result);
    }
}
