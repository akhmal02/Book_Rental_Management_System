/**
 * PROJECT OBJECT OREINTED PROGRAMMING (BOOK LENDER MANAGEMENT SYSTEM)
 * MUHAMMAD AKHMAL(2022870756), NIK YUSUF(2022606254), RIZALIFF NABIL(2022451146), MUHAMMAD SYAFIQ(2022831866)
 * CLASS : CS1102B
 * PREPARED FOR : MADAM NORZALINA BINTI ISMAIL
 * DATE START : 12/06/2023 (WEEK 10)
 * DATE END : 09/07/2023 (WEEK 13)
 */

public abstract class Book
{
    //delcaring attributes
    private String ISBN;
    private String title;
    private String author;
    private boolean status;
    private String material;
    private double deposit;
    private int day;
    protected Borrower cust;
    
    //normal constructor
    public Book(String ISBN, String title, String author, boolean status, String material, double deposit, int day, Borrower cust)
    {
        this.ISBN=ISBN;
        this.title=title;
        this.author=author;;
        this.status=status;
        this.material=material;
        this.deposit=deposit;
        this.day=day;
        this.cust=cust;
    }
    
    //retriever method each attributes
    public String getISBN()
    {
        return ISBN;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public boolean getStatus()
    {
        return status;
    }
    public String getMaterial()
    {
        return material;
    }
    public int getDay()
    {
        return day;
    }
    public double getDeposit()
    {
        return deposit;
    }
    public Borrower getBorrower()
    {
        return cust;
    }
    
    //calculate fee
    public abstract double calcBookFee();

    //print method
    public String toString()
    {
        return("\n-------------------------"+
               "\nBook Information :-"+
               "\n-------------------------"+
               "\nSeries Number(ISBN): "+ISBN+
               "\nTitle: "+title+
               "\nAuthor: "+author+
               "\nMaterial Type (Novel/Reference): "+material+
               "\nStatus Available: "+status+
               "\nDeposit: "+deposit+
               "\nRent for (Day): "+day);
    }
}