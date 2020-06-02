package task6;

public class Book {
private int id;
private String title;
private String autor;
private double price;
private String genre;


public Book(int id, String title, String autor, double price, String genre){
    this.id = id;
    this.autor = autor;
    this.title = title;
    this.price = price;
    this.genre = genre;

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
