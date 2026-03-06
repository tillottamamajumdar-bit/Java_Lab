import java.time.LocalDate;
import java.util.ArrayList;

public class ArrayListForBooks
{
    public static void main(String args[])  {
        ArrayList<Book> alb= new ArrayList<Book>();
        try {
            Book b1 = new Book();
            alb.add(b1);

            Book b2 = new Book("HarryPotter", 399, "FL0001", "JK Rowling");
            b2.genre = "Fantasy";
            b2.dateOfPub = LocalDate.of(2003, 5, 12);
            alb.add(b2);

            Book b3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 340, "GH120");
            b3.dateOfPub = LocalDate.of(1925, 4, 10);
            b3.genre = "Fiction";
            alb.add(b3);

            Book b4 = new Book("The Girl on the Train", 299, "TR123", "Ruskin Bond");
            b4.genre = "Fiction";
            b4.dateOfPub = LocalDate.of(2022, 11, 5);
            alb.add(b4);

            Book b5 = new Book(b1);
            b5.title = "Icon";
            b5.genre = "Show";
            alb.add(b5);

            Book b6=new Book("tit",-566,"ISM000","JK");
            alb.add(b6);




        }
        catch (InvalidBookException e)
        {
            System.out.println(e.getMessage());
        }
        try{
            Book b7=new Book("",-566,"ISM000","JK");
            alb.add(b7);
        }
        catch (InvalidBookException e)
        {
            System.out.println(e.getMessage());
        }



        alb.forEach(b->{
            System.out.println("TITLE: "+b.title);
            System.out.println("author: "+b.author);
            System.out.println("price: "+b.price);
            System.out.println("ISBN: "+b.ISBN);
            System.out.println("genre: "+b.genre);
            System.out.println("dateOfPub: "+b.dateOfPub);
            System.out.println();
        });

        System.out.println("Title with genre as Fiction: ");
       alb.forEach(b->{
           if(b.genre != null && b.genre.equalsIgnoreCase("Fiction"))
               System.out.println(b.title);
       });

       double avg=0;
       for(Book b:alb)
           avg+=b.price;
       avg=avg/alb.size();
        System.out.println("\nThe Average Cost is: "+avg);
    }
}

