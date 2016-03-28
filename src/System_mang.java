
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class System_mang {
    
    public static Connection Con_DB;
    
    
    
    Person Login(String UserName,String Password)
    {
        System_mang.Connect();
        int NumberOfRows=0;
        Manager M=new Manager();
        Employee E=new Employee();
        Customer C=new Customer();        
        PreparedStatement pat = null;
        ResultSet result = null;
        String Query="SELECT EMP_ID FROM employee where EMAIL='"+UserName+"'and PASSWORD='"+Password+"' ";
        try
        {
            pat=System_mang.Con_DB.prepareStatement(Query);
            result=pat.executeQuery();
            while(result.next())
            {
                E=M.SearchEmployee(result.getInt("EMP_ID"));
                return E;
            }
         Query="SELECT CUST_ID FROM customer where MAIL='"+UserName+"'and PASSWORD='"+Password+"' ";
            pat=System_mang.Con_DB.prepareStatement(Query);
            result=pat.executeQuery();
            while(result.next())
            {
                C=M.SearchCustomer(result.getInt("CUST_ID"));
                return C;
            }
        }
        catch(Exception Ex){return null;}
        return null;
    }
    
   
    
    
    public static void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Con_DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/maintenence?zeroDateTimeBehavior=convertToNull","root", "");
            System.out.println("ConnectionDB Done");
        }
        catch(Exception x)
        {
            System.out.println("Eroro LL2sf fe el Coonection");
        }        
    }
    
    public static void Close()
    {
        try {
            Con_DB.close();
            System.out.println("CloseDB Done");
        } catch (SQLException ex) {
            System.out.println("Eror in function close DB");
        }
    }
    
    
     
    public ArrayList<Technical> ShowAllTecnicals(int BranchID)
    {
         try{
            System_mang.Connect();
            String query1 = "select *from employee where BRANCH_ID="+BranchID ;
           PreparedStatement pat = System_mang.Con_DB.prepareStatement(query1);
           ResultSet result =  result = pat.executeQuery(query1);
            if (result != null) {
            Technical E =new Technical();
            ArrayList<Technical>tec=new ArrayList<>(50);       
            while (result.next()) {
                 if(result.getInt("")==0)
                 {
                E.Fname = result.getString("F_name");
                E.Lname = result.getString("L_name");
                E.Ssn = result.getString("SSN");
                E.Email = result.getString("Email");
                E.Password = result.getString("Password");
                E.Salary = result.getDouble("Salary");
                E.Age = result.getInt("Age");
                E.Bearthdate = result.getString("BIRTHDATE");
                E.Gender = result.getString("Gender");
                E.Dateapointment = result.getString("Date_of_appointment");
                E.Branchid = result.getInt("BRANCH_ID");
                E.Quality = result.getInt("Quality");
                E.Workhours = result.getInt("work_hours");
                E.Accountblock = result.getBoolean("account_block");
                E.Phone = result.getString("phone");
                E.Address = result.getString("Address");
                E.ID=result.getInt("EMP_ID");
                E.Region=Service.searchRegion(result.getInt("EMP_ID"));
                E.Quality=result.getInt("QUALITY");
                E.Workhours=result.getInt("WORK_HOURS");
                E.Accountblock=result.getBoolean("ACCOUNT_BLOCK");
               tec.add(E);
               E=new Technical();
            }
            }
            return tec;
        }
            else
                return null;
        }
        catch(Exception ex )
        {
            ex.printStackTrace();
               return null;
        }
    }
    
    
    
    public ArrayList<Service> ShowAllServices(int BranchID)
    {
try{
            Connect();
            ResultSet result = null;
            PreparedStatement pat = null;
            String query1 = "select *from employee where BRANCH_ID="+BranchID ;
            pat = System_mang.Con_DB.prepareStatement(query1);
            result = pat.executeQuery(query1);
            if (result != null) {
            Service E =new Service();
            ArrayList<Service>serv=new ArrayList<>(50);       
            while (result.next()) {
                if(result.getInt("SPECIALIZATION")==1)
                {    
                E.Fname = result.getString("F_name");
                E.Lname = result.getString("L_name");
                E.Ssn = result.getString("SSN");
                E.Email = result.getString("Email");
                E.Password = result.getString("Password");
                E.Salary = result.getDouble("Salary");
                E.Age = result.getInt("Age");
                E.Bearthdate = result.getString("BIRTHDATE");
                E.Gender = result.getString("Gender");
                E.Dateapointment = result.getString("Date_of_appointment");
                E.Branchid = result.getInt("BRANCH_ID");
                E.Quality = result.getInt("Quality");
                E.Workhours = result.getInt("work_hours");
                E.Accountblock = result.getBoolean("account_block");
                E.Phone = result.getString("phone");
                E.Address = result.getString("Address");
                E.ID=result.getInt("EMP_ID");
                E.Quality=result.getInt("QUALITY");
                E.Workhours=result.getInt("WORK_HOURS");
                E.Accountblock=result.getBoolean("ACCOUNT_BLOCK");
                serv.add(E);
               E=new Service();
                }
            }
            return serv;
        }
            return null;
        }
        catch(Exception ex )
        {
            ex.printStackTrace();
            return null;
        } 
    }
    
    
    
    public static Branch SearchBranch(int BranchID)
    {
        return new Branch();
    }
    
    
    
    public  boolean Regist(Customer customer)
    {
        try
        {
            Connect();
            Statement st=Con_DB.createStatement();
            String Query="insert into customer (`F_NAME`, `L_NAME`, `MAIL`, `PASSWORD`, `PHONE`, `ADDRESS`, `QUESTION_NUMBER`, `PROTECT_QUESTION`, `HomePhone`)  values("+"'"+customer.Fname+"' , '"+customer.Lname+
                    "' , '"+customer.Email+"' ,'"+customer.Password+"' , '"+customer.Phone+"' , '"+customer.Address+"' , '"+
                    customer.SecurityQuestion+"' ,'"+customer.SecurityQuestionAnwer+"' , '"+customer.HomePhone+"'"+")";
            st.executeUpdate(Query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    ArrayList<Requist> showallrequists()
    {
        ArrayList<Requist>R=new ArrayList();
        Connection con=null;
        PreparedStatement pat=null;
        ResultSet result=null;
        String Query ="SELECT * FROM requist" ;
        try
        {
            pat=con.prepareStatement(Query);
            result=pat.executeQuery();
            while(result.next())
            {
                Requist requist=new Requist();
                requist.Mycustomerid=result.getInt("CUSTOMER_ID");
                requist.Mydeviceid=result.getInt("DEVICE_ID");
                requist.RequistDate=result.getString("REQUIST_DATE");
                requist.Requistid=result.getInt("REQUIST_ID");
                R.add(requist);
            }
        }
        catch(Exception E)
        {
            
        }
        return R;
    }
    
    
    
    
    public static int ShowEmptyTime(int x,int y)
    {
        return 0;
    }
    
    
    
    ArrayList<CustomerMessage> ShowAllComplains(String Str)
    {
        ArrayList<CustomerMessage>CR=new ArrayList();
        Connection con=null;
        PreparedStatement pat=null;
        ResultSet result=null;
        String Query;
        if(Str.equals("all"))
        {
         Query ="SELECT * FROM complain" ;            
        }
        else
        {
            Query="SELECT *FROM complain where COMPLAIN_STATE ='"+Str+"'";
        }
        try
        {
            pat=con.prepareStatement(Query);
            result=pat.executeQuery();
            while(result.next())
            {
                CustomerMessage cm=new CustomerMessage();
                cm.ID=result.getInt("COMPLAIN_ID");
                cm.Content=result.getString("CONTENT");
                cm.Date=result.getString("DATE");
                cm.MessageState=result.getString("COMPLAIN_STATE");
                cm.MyorderID=result.getInt("ORDER_ID");
                cm.ReciverID=result.getInt("RECIVER_ID");
                CR.add(cm);
            }
        }
        catch(Exception E)
        {
            
        }
        return CR;
    }
    
    
    
    public static String ShowEmptyTechnical(String Date)
    {
        return Date;
    }
}
    