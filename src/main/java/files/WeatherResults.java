package files;

public class WeatherResults {
    private int min;
    private int srednia;
    private int max;

    public WeatherResults(int min, int srednia, int max) {
        this.min = min;
        this.srednia = srednia;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSrednia() {
        return srednia;
    }

    public void setSrednia(int srednia) {
        this.srednia = srednia;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "WeatherResults{" +
                "min=" + min +
                ", srednia=" + srednia +
                ", max=" + max +
                '}';
    }
}
