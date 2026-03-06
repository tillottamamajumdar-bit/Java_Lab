import java.time.LocalDate;

public class Book
{
    public String ISBN;
    public String author;
    public double price;
    public String genre;
    public String title;
    public LocalDate dateOfPub;
  //defaulth constructor
    Book()
    {
        title="Some Title";
        price=2450;
        genre="Default Genre";
        author="xyz";
        ISBN="ISB00000";
        dateOfPub=LocalDate.of(2000,01,01);
    }
  // parametrized construcotr
    public Book(String title, double price,String isbm,String author) throws InvalidBookException
    {
        if(title.equals(""))
            throw new InvalidBookException("Title cannot be blank. Objective creation fail\n");
        if(price<0)
            throw new InvalidBookException("Price cannot be negative. Objective creation fail\n");
        this.title=title;
        this.price=price;
        this.ISBN=isbm;
        this.author=author;
    }
    public Book(String t, String a, double p,String isbm) throws InvalidBookException
    {
        if(p<0)
            throw new InvalidBookException("Price cannot be negative");
        if(t.equals(""))
            throw new InvalidBookException("Title cannot be blank");
        this.author=a;
        this.price=p;
        this.title=t;
        this.ISBN=isbm;

    }
    //copy constructor
    public Book(Book b)
    {
        title=b.title;
        price=b.price;
        author=b.author;
        genre=b.genre;
        ISBN=b.ISBN;
        dateOfPub=b.dateOfPub;
    }
}