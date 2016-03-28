
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.chart.PieChart;


public class Manager extends Employee implements Search{
    
  

   //Adding Employee to DataBase
   public Boolean AddEmployee(Employee employee)
    {
        String SSN = "SELECT SSN FROM employee where employee.SSN ="+employee.Ssn;

            String Quary = "insert into employee ( F_NAME, L_NAME, SSN, EMAIL, PASSWORD, SALARY, AGE, BIRTHDATE, GENDER,"
                    + "                           DATE_OF_APPOINTMENT, SPECIALIZATION, QUALITY, WORK_HOURS, ACCOUNT_BLOCK,"
                    + "                           BRANCH_ID, PHONE, ADDRESS)"
                    + " values(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)" ;
            System_mang.Connect();
            
            // To check if Query is Valide
            PreparedStatement info;
            try {
                
                //Connect with DataBase & get Results
                Statement statment = System_mang.Con_DB.createStatement();
                ResultSet res = statment.executeQuery(SSN);
                String ssn = "";
                if(res.next())
                {
                    ssn= res.getString("SSN");
                }
                System.out.println(ssn);
                //to check if Emplyee is exist or not
                if(ssn.equalsIgnoreCase(employee.Ssn))
                {
                    System.out.println("Employee is Exist");
                    System_mang.Close();
                    return false;
                }
                info = System_mang.Con_DB.prepareStatement(Quary);
                info.setString(1, employee.Fname);
                info.setString(2, employee.Lname);
                info.setString(3, employee.Ssn);
                info.setString(4, employee.Email);
                info.setString(5, employee.Password);
                info.setInt(6, employee.Age);
                info.setDouble(7, employee.Salary);
                info.setString(8, employee.Bearthdate);
                info.setString(9, employee.Gender);
                info.setString(10, employee.Dateapointment);
                info.setString(11, employee.Spetiolization);
                info.setInt(12, employee.Quality);
                info.setInt(13, employee.Workhours);
                info.setBoolean(14, employee.Accountblock);
                info.setInt(15, employee.Branchid);
                info.setString(16, employee.Phone);
                info.setString(17, employee.Address);
                info.execute();
                //check employee is Tecnical to add Region
                if(employee instanceof Technical)
                {
                    Technical Technical_man = (Technical) employee;
                    //make this quary to get Employee_ID
                    String ID_Queary = "SELECT EMP_ID FROM employee where employee.SSN ="+Technical_man.Ssn;
                    
                    ResultSet result = statment.executeQuery(ID_Queary);
                    int Emp_ID = 0;
                    if(result.next())
                    {
                        Emp_ID= result.getInt("EMP_ID"); //employee id that will Add
                    }
                    //to add Region to Employee
                    String Region_Quary = "insert into technical_region ( EMP_ID, REGION)" + "values ( ?, ?)";
                    info = System_mang.Con_DB.prepareStatement(Region_Quary);
                    info.setInt(1, Emp_ID);
                    info.setString(2, Technical_man.Region);
                    info.execute();
                }

            } catch (SQLException ex) {
                System.out.println("error in Function addEmployee");
                System.err.println(ex.getMessage());
                System_mang.Close();
                return false;
            } // end Try
        System.out.println("Add Done");
        System_mang.Close();
        return true;
    }//end AddEmplyee
    
   
   
   
   
   Employee SearchEmployee(int EmployeeID) throws SQLException {
        System_mang.Connect();
        ResultSet result = null;
        PreparedStatement pat = null;

        Employee E = new Employee();
        String query1 = "select *from employee where EMP_ID=" + "" + EmployeeID + "";
        String query2 = "select *from technical_region where emp_ID=" + " " + EmployeeID + "";
        pat = System_mang.Con_DB.prepareStatement(query1);
        result = pat.executeQuery();
            while (result.next()) {
                E.Fname = result.getString("F_name");
                E.Lname = result.getString("L_name");
                E.Ssn = result.getString("SSN");
                E.Email = result.getString("Email");
                E.Password = result.getString("Password");
                E.Salary = result.getDouble("Salary");
                E.Bearthdate = result.getString("BIRTHDATE");
                E.Gender = result.getString("Gender");
                E.Dateapointment = result.getString("Date_of_appointment");
                E.Branchid = result.getInt("BRANCH_ID");
                E.Spetiolization = result.getString("SPECIALIZATION");
                E.Quality = result.getInt("Quality");
                E.Workhours = result.getInt("work_hours");
                E.Accountblock = result.getBoolean("account_block");
                E.Phone = result.getString("phone");
                E.Address = result.getString("Address");
                return E;
            }

        return null;
    }
    
    
    
    
    //Delete Employee from DataBase
    public Boolean DeleteEmployee(int EmployeeID)
    {
        System_mang.Connect();
        String specialization_Queary ="SELECT * FROM employee where EMP_ID ="+EmployeeID;
        try
        {
            Statement stat = System_mang.Con_DB.createStatement();
            ResultSet res = stat.executeQuery(specialization_Queary);
            String Specialization = null;
            if(res.next())
            {
                Specialization = res.getString("SPECIALIZATION"); 
                System.out.println(Specialization);
            }
            
            if(Specialization.equals("")||Specialization.equals(null))
            {
                System.err.println("Error Null Delet Function");
                System_mang.Close();
                return false;
            }
            Statement st =System_mang.Con_DB.createStatement();
            //to delete form Employee Table
            String Delet_Queary = "DELETE FROM employee WHERE Emp_id="+EmployeeID ;
            st.executeUpdate(Delet_Queary);
            if(Specialization.equals("Technical"));
            {
                Delet_Queary = "DELETE FROM Technical_Region WHERE Emp_id=" +EmployeeID;
                st.executeUpdate(Delet_Queary);
            }
            Delet_Queary = "DELETE FROM Massage WHERE Emp_id=" +EmployeeID;
            st.executeUpdate(Delet_Queary);
            System.out.println("Suucess");
            System_mang.Close();
            return true;
        }
        catch(SQLException ex)
        {
            System.out.println("error in Function Delete");
            System_mang.Close();
            return false;
        }
    }//end DeleteEmployee
    
    
    
    
    
    
    double BranchQuality() throws SQLException {
        System_mang.Connect();
        ResultSet result = null;
        PreparedStatement pat = null;
        int TotalQuality = 0;
        int Counter_Rows = 0;
        int Persant;
        String Query = "select *from Employee";
        pat = System_mang.Con_DB.prepareStatement(Query);
        result = pat.executeQuery();
        while (result.next()) {
            TotalQuality += result.getInt("Quality");
            Counter_Rows++;
        }
        Persant = TotalQuality / (Counter_Rows * 100) * 100;
        return Persant;
    }
    
    
    

    void Block(int Emp_id) {
        System_mang.Connect();
        ResultSet result = null;
        PreparedStatement pat = null;
        String Query = "update Employee set Account_Block='"+1+"' where Emp_id=" + " " + Emp_id + "";
        try{
       pat = System_mang.Con_DB.prepareStatement(Query);
       pat.execute();
        }
        catch(Exception E)
        {
        }
 
    }
    
    
    
    //set Account unblock
    void UnBlock(int Emp_id)
    {
        String Update_Quarey ="update Employee set Account_block = 0 where Emp_id="+Emp_id;
       try {
             PreparedStatement pre = System_mang.Con_DB.prepareStatement(Update_Quarey);
       } catch (SQLException ex) {
             Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//end unBlock
    
    
    
   Customer SearchCustomer(int ID) throws SQLException {
        System_mang.Connect();
        ResultSet result = null;
        PreparedStatement pat = null;
        Customer C = new Customer();
        String Query = "select *from Customer where Cust_ID=" + " " + ID + "";
        pat = System_mang.Con_DB.prepareStatement(Query);
        result = pat.executeQuery();
        while (result.next()) {
            C.ID = result.getInt("CUST_ID");
            C.Fname = result.getString("F_NAME");
            C.Lname = result.getString("L_name");
            C.Email = result.getString("MAIL");
            C.Password = result.getString("PASSWORD");
            C.Address = result.getString("ADDRESS");
            C.Phone = result.getString("Phone");
            C.SecurityQuestion=result.getString("QUESTION_NUMBER");
            C.SecurityQuestionAnwer= result.getString("QUESTION_NUMBER");
            return C;
        }
        return null;
    }
   
   
   
   
   
    //send Massage to Employee
    Boolean SendMessageToEmployee(EmployeeMessage Massage)
    {
        System_mang.Connect();
        String Massage_Queary = "insert into message (RECIVER_ID ,CONTENT ,STATE ,Date ,SENDER_ID)"
                               +"values(? ,? ,? ,? ,?)";
       try {
           String SSN_Queary ="SELECT SSN FROM employee where EMP_ID ="+Massage.ReciverID;
           Statement stat = System_mang.Con_DB.createStatement();
           ResultSet res = stat.executeQuery(SSN_Queary);
           if(res.next())
           {
               String ssn = res.getString("SSN");
               if(ssn.equals("")||ssn.equals(null))
               {
                   System.out.println("Employee Don't exist");
                   System_mang.Close();
                   return false;
               }
           }
           PreparedStatement prestat = System_mang.Con_DB.prepareStatement(Massage_Queary);
           prestat.setInt(1, Massage.ReciverID);
           prestat.setString(2, Massage.Content);
           prestat.setString(3, Massage.MessageState);
           prestat.setString(4, Massage.Date);
           prestat.setInt(5, Massage.SenderID);
           
           prestat.execute();
           System.out.println("Send Massage is Done");
           System_mang.Close();
           return true;
       } catch (SQLException ex) {
           System.out.println("error in function SendMessageToEmployee");
           System_mang.Close();
           return false;
       }    
    }
    //close Send massage

    
    
    //search order
    public Order SearchOrder(int OrderID) {
       try {
           System_mang.Connect();
           String order_Queary ="SELECT ORDER_ID FROM order_information where ORDER_ID ="+OrderID;
           Statement stat = System_mang.Con_DB.createStatement();
           ResultSet res = stat.executeQuery(order_Queary);
           if(res.next())
           {
               String ssn = res.getString("ORDER_ID");
               if(ssn.equals("")||ssn.equals(null))
               {
                   System.out.println("Oreder Don't exist");
                   System_mang.Close();
                   return null;
               }
               else
               {
                   order_Queary = "select * from order_information where ORDER_ID="+OrderID;
                   res = stat.executeQuery(order_Queary);
                   if(res.next())
                   {
                       Order order = new Order();
                       order.ID = res.getInt("ORDER_ID");
                       order.Myservice_id = res.getInt("SERVICE_ID");
                       order.Mytechnical_id = res.getInt("TECHNICAL_ID");
                       order.StartDate = res.getString("START_DATE");
                       order.Appointmentoffeies =res.getString("APPOINTMENT_OF_RECIEPT");
                       order.MyDeviceID = res.getInt("DEVICE_ID");
                       
                       System.out.println("Done");
                       System_mang.Close();
                       return order;
                   }
               }
           }
           
       } catch (SQLException ex) {
           System_mang.Close();
           System.out.println("Error in function SearchOrder"); 
           return null;
       }
       System_mang.Close();
       return null;
    }

    
    public boolean SendMessage(int ID, EmployeeMessage Message) {
        System_mang.Connect();
        PreparedStatement pat = null;
        ResultSet result = null;
        Manager M = new Manager();
        try {
            if (M.SearchCustomer(ID) == null) {
                return false;
            } else {
                String Query = "INSERT INTO message(MESSAGE_ID,SENDER_ID,CONTENT,STATE,RECIVER_ID,DATE) values ('" + Message.ID + "','" + Message.SenderID + "','" + Message.Content + "','" + Message.MessageState + "','" + ID + "','" + Message.Date + "')";
                pat = System_mang.Con_DB.prepareStatement(Query);
                pat.execute();
            }
        } catch (SQLException ex) {
        }
        return true;
    }
    
    
    public boolean UpdateEmployee(Employee employee, int EmployeeID) {
        System_mang.Connect();
        PreparedStatement pat = null;
        ResultSet result = null;
        Manager M = new Manager();
        try {
            if (M.SearchEmployee(EmployeeID)== null) {
                return false;
            }
            else
            {
                String Query="update employee set F_NAME='"+employee.Fname+"',L_NAME='"+employee.Lname+"',SSN='"+employee.Ssn+"',EMAIL='"+employee.Email+"',PASSWORD='"+employee.Password+"',SALARY='"+employee.Salary+"',BIRTHDATE='"+employee.Bearthdate+"',GENDER='"+employee.Gender+"',DATE_OF_APPOINTMENT='"+employee.Dateapointment+"',BRANCH_ID='"+employee.Branchid+"',PHONE='"+employee.Phone+"',ADDRESS='"+employee.Address+"',SPECIALIZATION='"+employee.Spetiolization+"',QUALITY='"+employee.Quality+"',WORK_HOURS='"+employee.Workhours+"' where EMP_ID='"+EmployeeID+"'";
                pat=System_mang.Con_DB.prepareStatement(Query);
                pat.execute();
            }
        } catch (Exception E) {
            return false;
        }

        return true;
    }

    
    
    
    
    
}
