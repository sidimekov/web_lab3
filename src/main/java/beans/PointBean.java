package beans;

import beans.ResultBean;
import entity.ResultEntity;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.inject.Inject;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name = "pointBean")
@RequestScoped
public class PointBean {
    private double x;
    private double y;
    private double r = 3.0;

    @Inject
    private ResultBean resultBean;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setSelectedX(double x) {
        this.x = x;
    }

    public void checkPoint() {
        Instant start = Instant.now();
        boolean isInside = checkInside(x, y, r);
        Instant end = Instant.now();

        double execTime = Duration.between(start, end).toNanos() / 1_000_000.0;

        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ResultEntity result = new ResultEntity(x, y, r, isInside, currentTime, execTime);
        resultBean.addResult(result);
    }

    private boolean checkInside(double x, double y, double r) {
        if (x <= 0 && y >= 0) {
            return x * x + y * y <= r * r;
        }
        if (x <= 0 && y <= 0) {
            return y >= -r && x >= -r / 2;
        }
        if (x >= 0 && y >= 0) {
            return y <= -1 * Math.sqrt(3) * x + r;
        }
        return false;
    }
}
