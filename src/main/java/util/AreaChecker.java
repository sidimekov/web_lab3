package util;

public class AreaChecker {
    public static boolean checkInside(double x, double y, double r) {
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
