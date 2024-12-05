package entity;

public class Result {
    private double x;
    private double y;
    private double r;
    private boolean isInside;
    private String currentTime;
    private double execTime;

    public Result(double x, double y, double r, boolean in, String currentTime, double execTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInside = in;
        this.currentTime = currentTime;
        this.execTime = execTime;
    }

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

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }

    public double getExecTime() {
        return execTime;
    }

    public void setExecTime(double execTime) {
        this.execTime = execTime;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isInside=" + isInside +
                ", currentTime='" + currentTime + '\'' +
                ", execTime=" + execTime +
                '}';
    }
}
