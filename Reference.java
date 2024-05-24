/**
 * PROJECT OBJECT OREINTED PROGRAMMING (BOOK LENDER MANAGEMENT SYSTEM)
 * MUHAMMAD AKHMAL(2022870756), NIK YUSUF(2022606254), RIZALIFF NABIL(2022451146), MUHAMMAD SYAFIQ(202283166)
 * CLASS : CS1102B
 * PREPARED FOR : MADAM NORZALINA BINTI ISMAIL
 * DATE START : 12/06/2023 (WEEK 10)
 * DATE END : 09/07/2023 (WEEK 13)
 */
public class Reference extends Book
{
    //attributes declaring
    private int edition;
    private String subject;
    private boolean student;
    
    //normal constructor
    public Reference(String ISBN, String title, String author, boolean status, String material, double deposit, int day, Borrower cust, String subject, int edition, boolean student){
        super(ISBN, title,author,status, material, deposit, day, cust);
        this.subject=subject;
        this.edition=edition;
        this.student=student;
    }
    
    //retriever method
    public String getSubject(){return subject;}
    public int getEdition(){return edition;}
    public boolean getStudent(){return student;}
    
    //calculation method
    public double calcBookFee(){
        double totRef=0.0, ref=0;
        if(getSubject().equalsIgnoreCase("CSC186"))
            ref = 0.10 * getDay();
        else if(getSubject().equalsIgnoreCase("CSC159"))
            ref = 0.30 * getDay();
        else if(getSubject().equalsIgnoreCase("ACC117"))
            ref = 0.50 * getDay();
        else
            ref = 1.00 * getDay();
            
            if(getStudent()==true)      //deposit will be deducted in file apps
            totRef = ref - (ref * 0.02);
            else
            totRef = ref;
        return totRef;
    }
    
    //print method
    public String toString(){
        return(super.toString()+"\nSubject: "+subject+"\nEdition: "+edition+ 
               "\nStudent UiTM? (True/False): "+student + cust.toString());
    }
}