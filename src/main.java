
import com.sun.prism.PixelFormat;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import org.json.simple.ItemList;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moroclash
 */
public class main {
    public static void main(String[] args) throws SQLException {
      /*  Branch b = new Branch();
        b.setID(1);
        Technical t = new Technical();
        t.Address ="20-st/mamama";
        t.AccountBlock=false;
        t.Age=20;
        t.Bearthdate="20-10-15";
        t.Dateapointment="20-14-14";
        t.Email="moroclash@gmail.com";
        t.Fname="moro";
        t.Gender="male";
        t.Lname="Clash";
        t.BranchID=1;
        t.Password="moroclash";
        t.Phone="0112311154";
        t.Quality=50;
        t.Region="Cairo";
        t.Salary=201.12;
        t.Spetiolization="Tecnical";
        t.Ssn="123456789";
        t.Workhours=15;
        
        new Manager().AddEmployee(t);*/
      
      
        /*EmployeeMessage e = new EmployeeMessage();
        e.Content= "ezy omk";
        e.Date = "12-12-12";
        e.MessageState="1212";
        e.ReciverID = 121;
        e.SenderID = 124;
        
        new Manager().SendMessageToEmployee(e);*/
        
        
       /* Order o = new Manager().SearchOrder(544);
        System.out.println(o.ID);
        System.out.println(o.Appointmentoffeies);
        System.out.println(o.Mybill_id);
        System.out.println(o.Mytechnical_id);
        System.out.println(o.Mytechnical_id);*/
       
        System.out.println("enter id");
        Scanner see = new Scanner(System.in);
        Requist r = new Requist();
        r.Requistid=see.nextInt();
        
        Service s = new Service();
        s.DeleteRequest(r.Requistid);
        
    }
}
