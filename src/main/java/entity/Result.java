package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double x;
    private double y;
    private double r;
    private boolean inside;
    private String currentTime;
    private double execTime;

    public Result() {
    }

    public Result(double x, double y, double r, boolean in, String currentTime, double execTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.inside = in;
        this.currentTime = currentTime;
        this.execTime = execTime;
    }

}
