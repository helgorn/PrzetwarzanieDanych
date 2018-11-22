package files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        //System.out.println( "Hello World!" );

//        File file = new File("C:\\Users\\Michal\\IdeaProjects\\KursProgramowanie1\\PrzetwarzanieDanych\\src\\main\\resources\\simpleExample.txt");
//        LineIterator fileContents= FileUtils.lineIterator(file, "UTF-8");
//        while(fileContents.hasNext()){
//            System.out.println(fileContents.nextLine());
//        }
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
                bookList.add(new Book(line[0], line[1], line[2], BigDecimal.valueOf(Double.valueOf(line[3])), Boolean.valueOf(line[4]), line[5], line[6], Integer.valueOf(line[7]), line[8]));
            }
        }

        Collections.sort(bookList);
//        for (int i = 0; i < (bookList.size() - 1); i++) {
//            for (int j = 0; j < (bookList.size() - 1); j++) {
//                if (bookList.get(i).getPrice() < bookList.get(i + 1).getPrice()) {
//                    Book tmp = bookList.get(i);
//                    bookList.remove(i);
//                    bookList.add(i, bookList.get(i + 1));
//                    bookList.remove(i + 1);
//                    bookList.add(i + 1, tmp);
//                }
//            }
//        }


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

        FlightsService flightsService = new FlightsService();
        flightsService.addFile(fileFlights);
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
        String minMonthName = "";
        String maxMonthName = "";
        int[] minMaxMonth = flightsService.minMaxMonth();
        int[] minMaxYear = flightsService.minMaxYear();

        switch (minMaxMonth[0]) {
            case 0:
                minMonthName = "January";
                break;
            case 1:
                minMonthName = "February";
                break;
            case 2:
                minMonthName = "March";
                break;
            case 3:
                minMonthName = "April";
                break;
            case 4:
                minMonthName = "May";
                break;
            case 5:
                minMonthName = "June";
                break;
            case 6:
                minMonthName = "July";
                break;
            case 7:
                minMonthName = "September";
                break;
            case 8:
                minMonthName = "August";
                break;
            case 9:
                minMonthName = "October";
                break;
            case 10:
                minMonthName = "November";
                break;
            case 11:
                minMonthName = "December";
                break;
        }
        switch (minMaxMonth[2]) {
            case 0:
                maxMonthName = "January";
                break;
            case 1:
                maxMonthName = "February";
                break;
            case 2:
                maxMonthName = "March";
                break;
            case 3:
                maxMonthName = "April";
                break;
            case 4:
                maxMonthName = "May";
                break;
            case 5:
                maxMonthName = "June";
                break;
            case 6:
                maxMonthName = "July";
                break;
            case 7:
                maxMonthName = "September";
                break;
            case 8:
                maxMonthName = "August";
                break;
            case 9:
                maxMonthName = "October";
                break;
            case 10:
                maxMonthName = "November";
                break;
            case 11:
                maxMonthName = "December";
                break;
        }
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
