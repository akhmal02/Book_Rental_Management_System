/**
 * PROJECT OBJECT OREINTED PROGRAMMING (BOOK LENDER MANAGEMENT SYSTEM)
 * MUHAMMAD AKHMAL(2022870756), NIK YUSUF(2022606254), RIZALIFF NABIL(2022451146), MUHAMMAD SYAFIQ(2022831866)
 * CLASS : CS1102B
 * PREPARED FOR : MADAM NORZALINA BINTI ISMAIL
 * DATE START : 12/06/2023 (WEEK 10)
 * DATE END : 09/07/2023 (WEEK 13)
 */
public class Borrower
{
    //delcaring attributes
    private String ID;
    private String name;
    private String phone;
    
    //normal constructor
    public Borrower(String ID, String name, String phone){
        this.ID=ID;
        this.name=name;
        this.phone=phone;
    }
    
    //accessor method for class
    public void setBorrower(String ID, String name, String phone){
       this.ID=ID;
       this.name=name;
       this.phone=phone; 
    }
    //accessor method for Phone only
    public void setPhone(String phone){this.phone=phone;}
    
    //retriever method for each attributes
    public String getID(){return ID;}
    public String getName(){return name;}
    public String getPhone(){return phone;}
    
    //printer method toString()
    public String toString(){
        return("\n-------------------------"+
               "\nBorrower Information :- "+
               "\n-------------------------"+
               "\nID Borrower: "+ID+
               "\nName: "+name+
               "\nPhone: "+phone);
    }
}