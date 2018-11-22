package files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FlightsService {

    private List<Flight> flightList = new ArrayList<>();

    public void addFile(File file) throws IOException {
        LineIterator fileContents = FileUtils.lineIterator(file, "UTF-8");
        while (fileContents.hasNext()) {
            String[] line = fileContents.nextLine().split(",");

            if (!(line[0].equals("year"))) {
                flightList.add(new Flight(Integer.valueOf(line[0]), line[1], Integer.valueOf(line[2])));
            }
        }
    }

    public int allPassengers() {
        int suma = 0;
        for (Flight flight : flightList) {
            suma += flight.getPassengers();
        }
        return suma;
    }

    public int[] podzialNaLata() {
        int[] tab = new int[12];
        int sumaR = 0;
        int i = 0;
        int currentYear = flightList.get(0).getYear();
        for (Flight flight : flightList) {
            if (flight.getYear() == currentYear) {
                sumaR += flight.getPassengers();
            } else {
                tab[i] = sumaR;
                currentYear = flight.getYear();
                sumaR = flight.getPassengers();
                i++;
            }
        }
        tab[i] = sumaR;
        return tab;
    }

    public int[] podzialNaMiesiace() {
        int[] tab = new int[12];

        for (Flight flight : flightList) {
            if (flight.getMonth().equals("January")) {
                tab[0] += flight.getPassengers();
            } else if (flight.getMonth().equals("February")) {
                tab[1] += flight.getPassengers();
            } else if (flight.getMonth().equals("March")) {
                tab[2] += flight.getPassengers();
            } else if (flight.getMonth().equals("April")) {
                tab[3] += flight.getPassengers();
            } else if (flight.getMonth().equals("May")) {
                tab[4] += flight.getPassengers();
            } else if (flight.getMonth().equals("June")) {
                tab[5] += flight.getPassengers();
            } else if (flight.getMonth().equals("July")) {
                tab[6] += flight.getPassengers();
            } else if (flight.getMonth().equals("September")) {
                tab[7] += flight.getPassengers();
            } else if (flight.getMonth().equals("August")) {
                tab[8] += flight.getPassengers();
            } else if (flight.getMonth().equals("October")) {
                tab[9] += flight.getPassengers();
            } else if (flight.getMonth().equals("November")) {
                tab[10] += flight.getPassengers();
            } else if (flight.getMonth().equals("December")) {
                tab[11] += flight.getPassengers();
            }
        }
        return tab;
    }


    public int[] minMaxMonth() {
        int[] minMax = new int[4];
        int min = podzialNaMiesiace()[0];
        int max = podzialNaMiesiace()[0];
        int minMonth = 0;
        int maxMonth = 0;

        for (int i = 0; i < podzialNaMiesiace().length; i++) {
            if (min > podzialNaMiesiace()[i]) {
                min = podzialNaMiesiace()[i];
                minMonth = i;
            }
            if (max < podzialNaMiesiace()[i]) {
                max = podzialNaMiesiace()[i];
                maxMonth = i;
            }
        }
        minMax[0] = maxMonth;
        minMax[1] = max;
        minMax[2] = minMonth;
        minMax[3] = min;

        return minMax;
    }

    public int[] minMaxYear() {
        int minMax[] = new int[4];
        int min = podzialNaLata()[0];
        int max = podzialNaLata()[0];
        int minYear = 0;
        int maxYear = 0;
        for (int i = 0; i < podzialNaLata().length; i++) {
            if (min > podzialNaLata()[i]) {
                min = podzialNaLata()[i];
                minYear = i;
            }
            if (max < podzialNaLata()[i]) {
                max = podzialNaLata()[i];
                maxYear = i;
            }
        }
        minYear += 1949;
        maxYear += 1949;
        minMax[0] = maxYear;
        minMax[1] = max;
        minMax[2] = minYear;
        minMax[3] = min;

        return minMax;
    }

    public float[] procenty(int[] lata){
        float poprzedniRok = 0;
        float tenRok;
        float[] procenty = new float[11];
        int i=0;
        System.out.print("Zmiany procentowe: ");
        for (int procent : lata) {
            tenRok = procent;
            if (poprzedniRok != 0) {
                procenty[i]=(tenRok * 100 / poprzedniRok) - 100;
                i++;
            }
            poprzedniRok = procent;
        }

        return procenty;
    }
}
