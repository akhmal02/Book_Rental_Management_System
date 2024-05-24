/**
 * PROJECT OBJECT OREINTED PROGRAMMING (BOOK LENDER MANAGEMENT SYSTEM)
 * MUHAMMAD AKHMAL(2022870756), NIK YUSUF(2022606254), RIZALIFF NABIL(2022451146), MUHAMMAD SYAFIQ(2022838166)
 * CLASS : CS1102B
 * PREPARED FOR : MADAM NORZALINA BINTI ISMAIL
 * DATE START : 12/06/2023 (WEEK 10)
 * DATE END : 09/07/2023 (WEEK 13)
 */
public class Novel extends Book
{
    //attributes declaring
    private String genre;
    private boolean local;
    
    //normal constructor
    public Novel(String ISBN, String title, String author, boolean status, String material, double deposit, int day, Borrower cust, String genre, boolean local){
        super(ISBN, title,author, status, material, deposit, day, cust);
        this.genre=genre;
        this.local=local;
    }
    
    //retriever method
    public String getGenre(){return genre;}
    public boolean getLocal(){return local;}
    
    //calculation method
    public double calcBookFee(){
        double price=0;
        double totNov=0;
        if(getGenre().equalsIgnoreCase("Romance"))
            price = 0.50 * getDay();
        else if(getGenre().equalsIgnoreCase("Adventure"))
            price = 0.40 * getDay();
        else if(getGenre().equalsIgnoreCase("Supernatural"))
            price = 0.70 * getDay();
        else
            price = 1.00 * getDay();
            
            if(getLocal()==true)
                totNov = price - (price*0.02);  //customer will get 2% discount for supporting Local book
            else
                totNov = price;
        return totNov;
    }
    
    //print method
    public String toString(){
        return(super.toString()+"\nGenre: "+genre+"\nLocal (True/False): "+local +cust.toString());
    }
}