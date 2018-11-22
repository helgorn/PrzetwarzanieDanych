package files;

public class Flight {

    private int year;
    private String month;
    private int passengers;

    public Flight(int year, String month, int passengers) {
        this.year = year;
        this.month = month;
        this.passengers = passengers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
