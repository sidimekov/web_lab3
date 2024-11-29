package beans;

import entity.ResultEntity;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.ArrayList;

@SessionScoped
@ManagedBean
public class ResultControllerBean implements Serializable {
    private ArrayList<ResultEntity> resultEntities;
}
