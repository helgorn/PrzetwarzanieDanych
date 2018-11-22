package files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PomiarService {

    private List<Pomiar> pomiars = new ArrayList<>();

    public WeatherResults tempOfDay(LocalDate localDate) {
        WeatherResults weatherResults = null;
        for (Pomiar pomiar : pomiars) {
            if (pomiar.getDate().equals(localDate)) {
                weatherResults = pomiar.getWeatherResults();
                break;
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
                    pomiars.add(new Pomiar(data, weatherResults));
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
        for (Pomiar pomiar : pomiars) {
            if (pomiar.getDate().isEqual(localDate) || (pomiar.getDate().isAfter(localDate) && pomiar.getDate().isBefore(localDate2)) || pomiar.getDate().isEqual(localDate2)) {
                if (pomiar.getWeatherResults().getMax() > max) {
                    max = pomiar.getWeatherResults().getMax();
                }
                if (pomiar.getWeatherResults().getMin() < min) {
                    min = pomiar.getWeatherResults().getMin();
                }
                suma += pomiar.getWeatherResults().getSrednia();
                i++;
            }
            if (pomiar.getDate().isAfter(localDate2)) {
                break;
            }
        }
        WeatherResults weatherResults = new WeatherResults(min, suma / i, max);

        return weatherResults;
    }

    public int hotDays(int temp) {
        int i = 0;
        for (Pomiar pomiar : pomiars) {
            if (pomiar.getWeatherResults().getSrednia() >= temp) {
                i++;
            }
        }
        return i;
    }

    public int[] statistics() {
        int hotYear = pomiars.get(0).getDate().getYear();
        int coldYear = pomiars.get(0).getDate().getYear();
        int suma = 0;
        int sumaM = 0;
        int i = 0;
        int j = 0;
        int maxSrednia = 0;
        int minSrednia = 200;
        int maxSredniaM = 0;
        int hotYearMonth = pomiars.get(0).getDate().getYear();
        int minSredniaM = 200;
        int coldYearMonth = pomiars.get(0).getDate().getYear();
        int maxSredniaD = 0;
        int hotYearDay = pomiars.get(0).getDate().getYear();
        int minSredniaD = 200;
        int coldYearDay = pomiars.get(0).getDate().getYear();
        int tymSrednia = 0;
        int tymSredniaM = 0;
        int hotMonth = pomiars.get(0).getDate().getMonthValue();
        int coldMonth = pomiars.get(0).getDate().getMonthValue();
        int hotDay = pomiars.get(0).getDate().getMonthValue();
        int coldDay = pomiars.get(0).getDate().getMonthValue();
        int currentYear = pomiars.get(0).getDate().getYear();
        int currentMonth = pomiars.get(0).getDate().getMonthValue();

        for (Pomiar pomiar : pomiars) {
            if (pomiar.getDate().getYear() == currentYear) {
                suma += pomiar.getWeatherResults().getSrednia();
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
                currentYear = pomiar.getDate().getYear();
                suma=0;
                i = 1;

            }
            if (pomiar.getDate().getMonthValue() == currentMonth) {
                sumaM += pomiar.getWeatherResults().getSrednia();
                j++;
                tymSredniaM = sumaM / j;
            } else {
                if (tymSredniaM > maxSredniaM) {
                    maxSredniaM = tymSredniaM;
                    hotMonth = currentMonth;
                    hotYearMonth = pomiar.getDate().getYear();
                }
                if (tymSredniaM < minSredniaM) {
                    minSredniaM = tymSredniaM;
                    coldMonth = currentMonth;
                    coldYearMonth = pomiar.getDate().getYear();
                }
                currentMonth = pomiar.getDate().getMonthValue();
                sumaM=0;
                j = 1;

            }
            if (pomiar.getWeatherResults().getSrednia() > maxSredniaD) {
                hotDay = pomiar.getDate().getDayOfYear();
                maxSredniaD = pomiar.getWeatherResults().getSrednia();
                hotYearDay=pomiar.getDate().getYear();
            }
            if (pomiar.getWeatherResults().getSrednia() < minSredniaD) {
                coldDay = pomiar.getDate().getDayOfYear();
                minSredniaD = pomiar.getWeatherResults().getSrednia();
                coldYearDay=pomiar.getDate().getYear();
            }
        }

        int tab[] = {hotYear, coldYear, hotMonth, hotYearMonth, coldMonth, coldYearMonth, hotDay, hotYearDay, coldDay, coldYearDay};
        return tab;
    }

}
