package files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PomiarService {

    private List<Data> data = new ArrayList<>();

    public WeatherResults tempOfDay(LocalDate localDate) {
        WeatherResults weatherResults = null;
        for (Data data : this.data) {
            if (data.getDate().equals(localDate)) {
                return data.getWeatherResults();
                }
        }
        return weatherResults;
    }

    public void addFile(File file) throws IOException {
        LineIterator fileContents3 = FileUtils.lineIterator(file, "UTF-8");
        while (fileContents3.hasNext()) {
            String[] line = fileContents3.nextLine().split(",");
            if (!(line[0].equals("Date"))) {
                String[] dateLine = line[0].split("/");
                int rok = Integer.valueOf(dateLine[2]);
                int miesiac = Integer.valueOf(dateLine[0]);
                int dzien = Integer.valueOf(dateLine[1]);
                LocalDate data = LocalDate.of(rok, miesiac, dzien);
                try {
                    int max = Integer.valueOf(line[1]);
                    int srednia = Integer.valueOf(line[2]);
                    int min = Integer.valueOf(line[3]);
                    WeatherResults weatherResults = new WeatherResults(min, srednia, max);
                    this.data.add(new Data(data, weatherResults));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
    }

    public WeatherResults przedział(LocalDate localDate, LocalDate localDate2) {
        int min = 20;
        int max = 0;
        int suma = 0;
        int i = 0;
        for (Data data : this.data) {
            if (data.getDate().isEqual(localDate) || (data.getDate().isAfter(localDate) && data.getDate().isBefore(localDate2)) || data.getDate().isEqual(localDate2)) {
                if (data.getWeatherResults().getMax() > max) {
                    max = data.getWeatherResults().getMax();
                }
                if (data.getWeatherResults().getMin() < min) {
                    min = data.getWeatherResults().getMin();
                }
                suma += data.getWeatherResults().getSrednia();
                i++;
            }
            if (data.getDate().isAfter(localDate2)) {
                break;
            }
        }
        WeatherResults weatherResults = new WeatherResults(min, suma / i, max);

        return weatherResults;
    }

    public int hotDays(int temp) {
        int i = 0;
        for (Data data : this.data) {
            if (data.getWeatherResults().getSrednia() >= temp) {
                i++;
            }
        }
        return i;
    }

    public int[] statistics() {
        int hotYear = data.get(0).getDate().getYear();
        int coldYear = data.get(0).getDate().getYear();
        int suma = 0;
        int sumaM = 0;
        int i = 0;
        int j = 0;
        int maxSrednia = 0;
        int minSrednia = data.get(0).getWeatherResults().getSrednia();
        int maxSredniaM = 0;
        int hotYearMonth = data.get(0).getDate().getYear();
        int minSredniaM = data.get(0).getWeatherResults().getSrednia();
        int coldYearMonth = data.get(0).getDate().getYear();
        int maxSredniaD = 0;
        int hotYearDay = data.get(0).getDate().getYear();
        int minSredniaD = data.get(0).getWeatherResults().getSrednia();
        int coldYearDay = data.get(0).getDate().getYear();
        int tymSrednia = 0;
        int tymSredniaM = 0;
        int hotMonth = data.get(0).getDate().getMonthValue();
        int coldMonth = data.get(0).getDate().getMonthValue();
        int hotDay = data.get(0).getDate().getMonthValue();
        int coldDay = data.get(0).getDate().getMonthValue();
        int currentYear = data.get(0).getDate().getYear();
        int currentMonth = data.get(0).getDate().getMonthValue();

        for (Data data : this.data) {
            if (data.getDate().getYear() == currentYear) {
                suma += data.getWeatherResults().getSrednia();
                i++;
                tymSrednia = suma / i;
            } else {
                if (tymSrednia > maxSrednia) {
                    maxSrednia = tymSrednia;
                    hotYear = currentYear;
                }
                if (tymSrednia < minSrednia) {
                    minSrednia = tymSrednia;
                    coldYear = currentYear;
                }
                currentYear = data.getDate().getYear();
                suma=0;
                i = 1;

            }
            if (data.getDate().getMonthValue() == currentMonth) {
                sumaM += data.getWeatherResults().getSrednia();
                j++;
                tymSredniaM = sumaM / j;
            } else {
                if (tymSredniaM > maxSredniaM) {
                    maxSredniaM = tymSredniaM;
                    hotMonth = currentMonth;
                    hotYearMonth = data.getDate().getYear();
                }
                if (tymSredniaM < minSredniaM) {
                    minSredniaM = tymSredniaM;
                    coldMonth = currentMonth;
                    coldYearMonth = data.getDate().getYear();
                }
                currentMonth = data.getDate().getMonthValue();
                sumaM=0;
                j = 1;

            }
            if (data.getWeatherResults().getSrednia() > maxSredniaD) {
                hotDay = data.getDate().getDayOfYear();
                maxSredniaD = data.getWeatherResults().getSrednia();
                hotYearDay= data.getDate().getYear();
            }
            if (data.getWeatherResults().getSrednia() < minSredniaD) {
                coldDay = data.getDate().getDayOfYear();
                minSredniaD = data.getWeatherResults().getSrednia();
                coldYearDay= data.getDate().getYear();
            }
        }

        int tab[] = {hotYear, coldYear, hotMonth, hotYearMonth, coldMonth, coldYearMonth, hotDay, hotYearDay, coldDay, coldYearDay};
        return tab;
    }



}
