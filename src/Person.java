
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emad
 */
public abstract class Person {
    int ID;    
    String Fname;
    String Lname;
    String Phone;
    String Address;
    String Email;
    String Password;
    Boolean Accountblock;
    ArrayList<MainMessage> inbox;
    
    ArrayList<MainMessage> ShowMyMassages()
    {
        return null;
    }
}
