package beans;

import entity.Result;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import util.AreaChecker;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Named("pointBean")
@SessionScoped
@Data
public class PointBean implements Serializable {
    private double x = 0.0;
    // ключ - число x, значение - включен ли чекбокс или нет
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

        Instant start = Instant.now();
        boolean isInside = AreaChecker.checkInside(x, y, r);
        Instant end = Instant.now();

        double execTime = Duration.between(start, end).toNanos() / 1_000_000.0;
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Result result = new Result(x, y, r, isInside, currentTime, execTime);
        resultBean.addResult(result);

        resultBean.save(result);
    }

//    public double getXFromCheckboxes() throws IllegalArgumentException {
//        if (checkboxesX.values().stream()
//                .filter(Boolean::booleanValue)
//                .count() == 1
//        ) {
//            return (checkboxesX.entrySet().stream()
//                    .filter(Map.Entry::getValue)
//                    .findFirst()
//                    .get().getKey()
//            );
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Нужно выбрать один чекбокс X.", "а тут не один"));
//            throw new IllegalArgumentException("Incorrect X checkboxes");
//        }
//    }

    public void onCheckboxChange(double newX) {
        checkboxesX.replaceAll((key, value) -> false);
        checkboxesX.put(newX, true);
        this.x = newX;
        System.out.println("новый X: " + x);
    }

}
