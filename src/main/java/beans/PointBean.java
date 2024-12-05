package beans;

import entity.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO:
//1
//1 - validation
//1 - database entity

@Named("pointBean")
@RequestScoped
@Data
public class PointBean implements Serializable {
    private double x = 0.0;
    private HashMap<Double, Boolean> checkboxesX = new HashMap<>();
    private double y = 0.0;
    private double r = 3.0;

    private static final List<Double> X_VALUES = List.of(-2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0);

    @Inject
    private ResultBean resultBean;

    public PointBean() {
        X_VALUES.forEach(k -> checkboxesX.put(k, false));
        checkboxesX.put(0.0, true);
    }

    public List<Double> getXValues() {
        return X_VALUES;
    }

    public void checkPoint() {
        this.x = getXFromCheckboxes();

        Instant start = Instant.now();
        boolean isInside = checkInside(x, y, r);
        Instant end = Instant.now();

        double execTime = Duration.between(start, end).toNanos() / 1_000_000.0;
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Result result = new Result(x, y, r, isInside, currentTime, execTime);
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

    public double getXFromCheckboxes() {
        if (checkboxesX.values().stream()
                .filter(Boolean::booleanValue)
                .count() == 1
        ) {
            return (checkboxesX.entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .findFirst()
                    .get().getKey()
            );
        }
        return 0.0;
    }

    public void onCheckboxChange(double newX) {
        checkboxesX.replaceAll((key, value) -> false);
        checkboxesX.put(newX, true);
        this.x = newX;
    }

}
