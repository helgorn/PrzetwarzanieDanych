package files;

import java.math.BigDecimal;

public class Book implements Comparable<Book> {
    private String id;
    private String cat;
    private String name;
    private BigDecimal price;
    private Boolean inStock;
    private String author;
    private String series;
    private int sequenceId;
    private String genre;

    public Book(String id, String cat, String name, BigDecimal price, Boolean inStock, String author, String series, int sequenceId, String genre) {
        this.id = id;
        this.cat = cat;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.author = author;
        this.series = series;
        this.sequenceId = sequenceId;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public String getCat() {
        return cat;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getSeries() {
        return series;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public String getGenre() {
        return genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean isInStock() {
        return inStock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", cat='" + cat + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                ", author='" + author + '\'' +
                ", series='" + series + '\'' +
                ", sequenceId=" + sequenceId +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public int compareTo(Book book) {
        int priceCompare=price.compareTo(book.price);
        if(priceCompare==0){
            return book.inStock.compareTo(inStock);
        }


        return priceCompare;

    }
}
