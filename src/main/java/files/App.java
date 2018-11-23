package files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {

        File file = new File("src\\main\\resources\\users.txt");
        List<User> listM = new ArrayList<>();
        List<User> listK = new ArrayList<>();
        LineIterator fileContents = FileUtils.lineIterator(file, "UTF-8");
        while (fileContents.hasNext()) {
            String[] line = fileContents.nextLine().split(" ");
            int wiek = Integer.valueOf(line[2]);
            if (line[0].endsWith("a") && wiek >= 18) {
                listK.add(new User(line[0], line[1], wiek));
            } else if (wiek >= 18) {
                listM.add(new User(line[0], line[1], wiek));
            }
        }
        System.out.println(listK);
        System.out.println(listM);


        File file2 = new File("src\\main\\resources\\books.csv");
        List<Book> bookList = new ArrayList<>();
        LineIterator fileContents2 = FileUtils.lineIterator(file2, "UTF-8");
        while (fileContents2.hasNext()) {
            String[] line = fileContents2.nextLine().split(",");
            if (!(line[0].equals("id"))) {
                bookList.add(new Book( line[2], BigDecimal.valueOf(Double.valueOf(line[3])), Boolean.valueOf(line[4]), line[5]));
            }
        }

        Collections.sort(bookList);

        System.out.println(bookList);
        File file3 = new File("D:\\example.txt");

        List<String> textLines = new ArrayList<>();
        for (Book book : bookList) {
            String newLine = (book.getName() + ", " + book.getAuthor() + ", " + book.getPrice() + ", " + book.isInStock());
            textLines.add(newLine);

        }
        FileUtils.writeLines(file3, textLines);


        File filePogoda = new File("src\\main\\resources\\weather-data.csv");

        PomiarService pomiarService = new PomiarService();
        pomiarService.addFile(filePogoda);
        LocalDate localDate = LocalDate.of(1948, 7, 5);
        LocalDate localDate2 = LocalDate.of(2015, 7, 5);

        System.out.println(pomiarService.tempOfDay(localDate));


        System.out.println(pomiarService.przedział(localDate, localDate2));


        System.out.println(pomiarService.hotDays(20));
        int tab[] = pomiarService.statistics();
        for (int wart : tab) {
            System.out.print(wart + " ");
        }
        System.out.println();

        File fileUsers = new File("src\\main\\resources\\userActions.csv");

        UserService userService = new UserService();
        userService.addFile(fileUsers);
        System.out.println(userService);

        File fileFlights = new File("src\\main\\resources\\flights.csv");

        FlightsService flightsService = new FlightsService(fileFlights);

        System.out.println(flightsService.allPassengers());
        int[] passengersPerYear = flightsService.podzialNaLata();
        int currentYear = 1949;
        for (int wart : passengersPerYear) {
            System.out.print(currentYear + ": " + wart + ", ");
            currentYear++;
        }
        int[] passengersPerMonth = flightsService.podzialNaMiesiace();
        System.out.println();
        for (int wart : passengersPerMonth) {
            System.out.print(wart + ", ");
        }
        String minMonthName;
        String maxMonthName;
        int[] minMaxMonth = flightsService.minMaxMonth();
        int[] minMaxYear = flightsService.minMaxYear();

        minMonthName = flightsService.whichMonth(minMaxMonth[0]);
        maxMonthName = flightsService.whichMonth(minMaxMonth[1]);

        System.out.println();
        System.out.println("Maksymalna i minimalna liczba pasazerow (miesiace): " + maxMonthName + ": " + minMaxMonth[1] + minMonthName + ": " + minMaxMonth[3]);
        System.out.println("Maksymalna i minimalna liczba pasazerow (lata): " + minMaxYear[0] + ": " + minMaxYear[1] + ", " + minMaxYear[2] + ": " + minMaxYear[3]);
        System.out.println("Roznica dla miesiecy: " + (minMaxMonth[1] - minMaxMonth[3]));
        System.out.println("Roznica dla lat: " + (minMaxYear[1] - minMaxYear[3]));

        float[] procenty = flightsService.procenty(passengersPerYear);
        System.out.print("Zmiany procentowe: ");
        for (float procent : procenty) {
            System.out.print(procent + "% ");
        }
    }
}
