  /**
 * PROJECT OBJECT OREINTED PROGRAMMING (BOOK LENDER MANAGEMENT SYSTEM)
 * MUHAMMAD AKHMAL(2022870756), NIK YUSUF(2022606254), RIZALIFF NABIL(2022451146), MUHAMMAD SYAFIQ(2022831866)
 * CLASS : CS1102B
 * PREPARED FOR : MADAM NORZALINA BINTI ISMAIL
 * DATE START : 12/06/2023 (WEEK 10)
 * DATE END : 09/07/2023 (WEEK 13)
 */

import java.util.*;
import java.io.*;
import java.util.StringTokenizer;
import java.text.*;
public class mainBookProgram
{
    public static void main(String args[]) throws IOException //must throws IOException for file input/output 
    {   
        try{//start try
        //declare an array of object with size 
        final int size = 50;
        Book [] b =new Book[size];
        
        Scanner inputText= new Scanner(System.in);
        Scanner inputNum=new Scanner(System.in);
        //insert data from BorrowerDetails.txt into array of object
        
        //to open input file
        FileReader fr=new FileReader("BorrowerDetails.txt"); 
        BufferedReader br = new BufferedReader(fr);
        DecimalFormat decfor = new DecimalFormat("0.00");
        int count=0;
        StringTokenizer st=null;
        String dataRow=br.readLine(); //to read one line of data  // [1]
        while(dataRow!=null)            //to make sure read until the end of data
        {   st=new StringTokenizer(dataRow,","); //to cut the word based on "*" delimiter
            
            String ISBN=st.nextToken();   
            String title = st.nextToken(); 
            String author = st.nextToken();
            boolean status = Boolean.parseBoolean(st.nextToken());
            String material = st.nextToken();
            double deposit = Double.parseDouble(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            String ID = st.nextToken();
            String name = st.nextToken();
            String phone = st.nextToken();
            
            //divide into 2 subclassess
            if(material.equalsIgnoreCase("Novel")){   
             String genre = st.nextToken();
             boolean local = Boolean.parseBoolean(st.nextToken());
             b[count] = new Novel(ISBN, title, author, status, material, deposit, day, new Borrower(ID, name, phone), genre, local);
            }
            else if(material.equalsIgnoreCase("Reference")){
             String subject = st.nextToken();
             int edition = Integer.parseInt(st.nextToken());
             boolean student = Boolean.parseBoolean(st.nextToken());
             b[count] = new Reference(ISBN, title, author, status, material, deposit, day, new Borrower(ID, name, phone), subject, edition, student);
            }
            count++;
            dataRow=br.readLine();  //to read the new line of data
        }
        
        //display the content in the array of object into BorrowerData.txt 
        FileWriter fwData=new FileWriter("BorrowerData.txt");  //create file "BorrowerData.txt"
        BufferedWriter bwData=new BufferedWriter(fwData);
        PrintWriter pwData=new PrintWriter(bwData);  //[2]
        
        //display and rewrite into txt file(avoid eficiency distribution)
        pwData.println("\n***Welcome to the Book Rental Management System !***"+
                       "\n\n+--------------------------------------------------+"+
                       "\n|  Details Borrower Book Rental Management System  |"+
                       "\n+--------------------------------------------------+");
        for(int i=0;i<count;i++)
        {
            pwData.println(b[i].toString());
        }
        
        //display the detail of List Novel Rental in file NovelData.txt and the console
        //to open output file
        FileWriter fwN=new FileWriter("NovelData.txt"); //create file "NovelData.txt"
        BufferedWriter bwN=new BufferedWriter(fwN);
        PrintWriter pwN=new PrintWriter(bwN); //3
        
        pwN.println("\n+--------------------------------------------------+"+
                    "\n|    Details of Borrower that rent Novel Book:     | "+
                    "\n+--------------------------------------------------+");
        for(int i=0;i<count;i++)
        {
            if (b[i].getMaterial().equalsIgnoreCase("Novel")){
                pwN.println(b[i].toString());
            }
        }
        
        //display the detail of List Reference Rental in file ReferenceData.txt and the console
        FileWriter fwR=new FileWriter("ReferenceData.txt"); //create file "ReferenceData.txt"
        BufferedWriter bwR=new BufferedWriter(fwR);
        PrintWriter pwR=new PrintWriter(bwR); //3
        pwR.println("\n+---------------------------------------------------+"+
                    "\n|   Details of Borrower that rent Reference Book:   | "+
                    "\n+---------------------------------------------------+");
        for(int i=0;i<count;i++)
        {
            if (b[i].getMaterial().equalsIgnoreCase("Reference")){
                pwR.println(b[i].toString());
            }
        }

        //find the most borrowed reference subject
        System.out.println("***Welcome to the Book Rental Management System !***");
        String mostBorrowedSubject = "";
        int maxBorrowCount = 0;
        for (int i = 0; i < count; i++) {
            //  Use Instance of Objects that is Reference and its [Matetrial == "Reference" (just to make sure)] 
            if (b[i] instanceof Reference && b[i].getMaterial().equalsIgnoreCase("Reference")) {
                Reference referenceBook = (Reference) b[i]; // Type casting to access Reference method. ITC getSubject();
                String subject = referenceBook.getSubject(); // Copy the object subject to new Variable Subject
                int borrowCount = 0; // initialize this subject count to zero

                for (int j = 0; j < count; j++) {
                    if (b[j] instanceof Reference && b[j].getMaterial().equalsIgnoreCase("Reference")) { // same shit as above
                        Reference otherReferenceBook = (Reference) b[j]; // same shit as above
                        if (otherReferenceBook.getSubject().equalsIgnoreCase(subject)) { // Filter the Arrays if the subject == to subject above
                            borrowCount++; // add 1 since same
                        }
                    }
                }
                // same concept as subsequent value trasfer. IE. highestNum OR lowestNum
                if (borrowCount > maxBorrowCount) {
                    maxBorrowCount = borrowCount;      // same concept as highestNum. The highest count is copied
                    mostBorrowedSubject = subject;     // As long as the specific subject is greater than og value,it copies
                }
            }
        }
        System.out.println("\nMost Borrowed Reference Subject: " + mostBorrowedSubject);
        
        //total fee for each borrower
        double fee=0;
        double totFee=0;
        FileWriter fwF=new FileWriter("BorrowerFee.dat");
        BufferedWriter bwF=new BufferedWriter(fwF);
        PrintWriter pwF=new PrintWriter(bwF); //3
        
        System.out.println("\n+---------------------------------------------------+"+
                           "\n|              Fee for each Borrower:               | "+
                           "\n+---------------------------------------------------+");
        pwF.println("\n+---------------------------------------------------+"+
                           "\n|              Fee for each Borrower:               | "+
                           "\n+---------------------------------------------------+");
        for(int i=0;i<count;i++)
        {
            if(b[i].getMaterial().equalsIgnoreCase("Reference") ||b[i].getMaterial().equalsIgnoreCase("Novel") )
            {
                fee=b[i].calcBookFee();
                System.out.println("\nID: "+b[i].getBorrower().getID()+"\nName: "+b[i].getBorrower().getName()+"\nFee: RM "+ decfor.format(fee));
                pwF.println("\nID: "+b[i].getBorrower().getID()+"\nName: "+b[i].getBorrower().getName()+"\nFee: RM "+ decfor.format(fee));
                totFee=totFee+fee;
            }
        }
        System.out.println("\nTotal Fee: RM "+decfor.format(totFee));
        
        // add new data
        FileWriter fwBd = new FileWriter("BorrowerDetails.txt", true); // Open file in append mode
        BufferedWriter bwBd = new BufferedWriter(fwBd);
        PrintWriter pwBd = new PrintWriter(bwBd);
        Scanner inText = new Scanner(System.in);
        Scanner inNum = new Scanner(System.in);
        char choice;
        System.out.println("\nDo you want to add a new data log? (Y/N): ");
        choice = inputText.next().charAt(0);
        
        if(choice=='Y' || choice=='y'){
            System.out.println("How many logs do you want to add?: ");
            int saiz = inNum.nextInt();
            for(int k=0; k<saiz; k++) {
            System.out.print("ISBN: ");
            String ISBN=inText.nextLine(); 
            System.out.print("Title: ");
            String title = inText.nextLine();
            System.out.print("Author: ");
            String author = inText.nextLine();
            System.out.print("Status Available (true/false): ");
            boolean status = inputText.nextBoolean();
            System.out.print("Material (Novel/Reference): ");
            String material = inText.nextLine();
            System.out.print("Deposit (RM): ");
            double deposit = inputNum.nextDouble();
            System.out.print("Day Borrowed: ");
            int day = inputNum.nextInt();
            System.out.print("Borrower ID: ");
            String ID = inText.nextLine();
            System.out.print("Borrower Name: ");
            String name = inText.nextLine();
            System.out.print("Borrower Phone: ");
            String phone = inText.nextLine();
            
            //divide into 2 subclassess
            if(material.equalsIgnoreCase("Novel")){ 
             System.out.print("Genre: ");
             String genre = inText.nextLine();
             System.out.print("Local (true/false): ");
             boolean local = inputText.nextBoolean();
             b[count] = new Novel(ISBN, title, author, status, material, deposit, day, new Borrower(ID, name, phone), genre, local);
             pwBd.println(ISBN + ","+ title + ","+ author + ","+status + ","+ material + ","+ deposit + ","+ day + ","+ ID + ","+ name + ","+ phone + ","+ genre + ","+ local);
            }
            else if(material.equalsIgnoreCase("Reference")){
             System.out.print("Subject: ");
             String subject = inText.nextLine();
             System.out.print("Edition: ");
             int edition = inputNum.nextInt();
             System.out.print("Student? (true/false): ");
             boolean student = inputText.nextBoolean();
             b[count] = new Reference(ISBN, title, author, status, material, deposit, day, new Borrower(ID, name, phone), subject, edition, student);
             pwBd.println(ISBN + ","+ title + ","+ author + ","+status + ","+ material + ","+ deposit + ","+ day + ","+ ID + ","+ name + ","+ phone + ","+ subject + ","
             + edition + "," + student);
                }
            }
            System.out.println("\n+---------------------------------------------------+"+
                           "\n|           Add Details Book and Borrower:          | "+
                           "\n+---------------------------------------------------+");
            System.out.println(b[count].toString());
        }
        
        //update borrower phone number
        Scanner inputString = new Scanner(System.in);
        String condition;
        System.out.println("\nDo you want to update a borrower's phone number? (Yes/No): ");  //ask user to update or not
        condition = inputString.nextLine();
        
        if (condition.equalsIgnoreCase("Yes")) {
            System.out.println("Enter borrower ID: ");
            String borrowUpdate = inputString.nextLine();
            
            int data = -1;
            for (int i = 0; i < count; i++) {
                if (b[i].getBorrower().getID().equalsIgnoreCase(borrowUpdate)) {  //comparison input with ID in borrower class
                    data = i;
                    break;
                }
            }
            
            if (data == -1) {
                System.out.println("Sorry, borrower does not exist.");
            } else {
                System.out.println("Enter new phone number: "); 
                String newPhone = inputString.nextLine();
                
                b[data].getBorrower().setPhone(newPhone); //replace new number that get from user into the array [i]
                System.out.println("Phone number has been updated successfully.");
                System.out.println("\nUpdated Borrower Details:-"+ b[data].getBorrower().toString());  //display the update borrower information
            }
        }
    
        //searching for borrower's existence  
        int found=-1;
        Scanner inString = new Scanner(System.in);
        String nameSearch;
        System.out.println("\nStatus Fee rental Book based on Borrower Name (Searching)");  //ask user to input name 
        System.out.println("Enter borrower name: "); //ask user to input borrower name 
        nameSearch=inString.nextLine();
        for (int i=0;i<count;i++)
        {
            if(b[i].getBorrower().getName().equalsIgnoreCase(nameSearch))
            {
                found=i;  //copy data in found
            }
        }  
        
        //display and declare overpaid or balance to borrower
        if(found==-1)
        System.out.println("The user does not exist");
        else
        {
            System.out.println("Name: "+b[found].getBorrower().getName());
            double balance;//=b[found].calcBookFee-b[found].getDeposit();
            if(b[found].getDeposit()>b[found].calcBookFee())
            {
                balance=b[found].getDeposit()-b[found].calcBookFee();
                System.out.println("Overpaid fee: "+"RM "+decfor.format(balance));
            }
            else if(b[found].getDeposit()<b[found].calcBookFee())
            {
                balance=b[found].calcBookFee()-b[found].getDeposit();
                System.out.println("Balance you have to pay: "+"RM "+decfor.format(balance));
            }
        } 
        br.close();
        pwData.close();
        pwN.close();
        pwR.close();
        pwF.close();
        pwBd.close(); // Close the PrintWriter
        bwBd.close(); // Close the BufferedWriter   
        }// end try
        catch(EOFException eof) //to display a message if an error related to file occur
        {    System.out.println("Problem: "+eof.getMessage());}
        catch(FileNotFoundException e)
        {    System.out.println("Problem: "+e.getMessage());}
        catch(IOException ioe)
        {    System.out.println("Problem: "+ioe.getMessage());}
        finally
        { System.out.println("\nEnd of the program"); }
    }
}