package files;

import java.time.LocalDate;

public class Pomiar {
    private LocalDate date;
    private WeatherResults weatherResults;


    public Pomiar(LocalDate date, WeatherResults weatherResults) {
        this.date = date;
        this.weatherResults = weatherResults;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public WeatherResults getWeatherResults() {
        return weatherResults;
    }

    public void setWeatherResults(WeatherResults weatherResults) {
        this.weatherResults = weatherResults;
    }

}
