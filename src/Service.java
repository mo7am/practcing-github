

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emad
 */
public class Service extends Employee implements Search{
    
    //salah 
   public boolean DeleteRequest(int Requistid) throws SQLException
    {
      
        System_mang.Connect(); 
        try{
            
            String Dlt_request = "DELETE  FROM requist WHERE REQUIST_ID = ?";
            PreparedStatement st = System_mang.Con_DB.prepareStatement(Dlt_request);
            st.setInt(1, Requistid);
            st.execute();
            
            System_mang.Close();
           return true;  
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System_mang.Close();
            return false;
        }
    }
    
   
   
      public static String searchRegion(int EmployeeID) {
        try {
            System_mang.Connect();
            
            String Query = "select REGION from technical_region  where EMP_ID= ?";
            PreparedStatement stat = System_mang.Con_DB.prepareStatement(Query);
            stat.setInt(1, EmployeeID);
            stat.execute();
            ResultSet result = stat.getResultSet();
            if (result != null) {
                String region = null;
                while (result.next()) {
                    region = result.getString("REGION");
                }
                return region;
            } else {
                return null;
            }
            } 
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
   
    
    Requist SearchRequist(int Requistid) {
        Requist R = new Requist();
        System_mang.Connect();
        ResultSet result = null;
        PreparedStatement pat = null;
        String Query = "SELECT * FROM damage_device where feedback= ?";
        try {
            pat = System_mang.Con_DB.prepareStatement(Query);
            pat.setInt(1, Requistid);
            pat.execute();
            result = pat.getResultSet();
            while (result.next()) {
                R.Requistid = result.getInt("REQUIST_ID‏");
                R.Mydeviceid = result.getInt("DEVICE_ID");
                R.Mycustomerid = result.getInt("CUSTOMER_ID");
                R.RequistDate=result.getString("REQUIST_DATE");
            }
        } catch (Exception E) {}
        return R;
    }

    
    
    
    
    //salah
    public static boolean DoneComplain (int Complain)
    {
        System_mang.Connect();


          String Query ="update complain set COMPLAIN_STATE =1 where COMPLAIN_ID= ?";
          try
              {
                PreparedStatement st = System_mang.Con_DB.prepareStatement(Query);
                st.setInt(1, Complain);
                st.execute();
              }
        catch(Exception x)
        {
        x.printStackTrace();
        return  false;

        }
     return true;
    }
    
    
    
    //salah
     public  boolean GiveOrder(int  IDorder,int IDtechincal)
    {
        System_mang.Connect();

          String Query;
       Query = "update order_information set TECHNICAL_ID =?,SERVICE_ID = ? where ORDER_ID= ?";
          try
              {
                PreparedStatement st = System_mang.Con_DB.prepareStatement(Query);
                st.setInt(1, IDtechincal);
                st.setInt(2, ID);
                st.setInt(3, IDorder);
                st.execute();
              }
         catch(Exception x)
        {
        x.printStackTrace();
        return  false;

        }
        return true;
    }

     
 
    
    
    @Override
    public Order SearchOrder(int OrderID) {
        Order ord = new Order();
        System_mang.Connect();
        ResultSet result = null;
       
        String Query_search = "SELECT * FROM order_information WHERE ORDER_ID = ?";
        try {
            PreparedStatement st = System_mang.Con_DB.prepareStatement(Query_search);
            st.setInt(1, OrderID);
            st.execute();
            while (result.next()) {
                 ord.ID = result.getInt("ORDER_ID");
                 ord.Mytechnical_id = result.getInt("TECHNICAL_ID");
                 ord.MyDeviceID = result.getInt("DEVICE_ID");
                 ord.StartDate = result.getString("START_DATE");
                 ord.Appointmentoffeies = result.getString("APPOINTMENT_OF_RECIEPT");
                 
                 ord.Myservice_id = result.getInt("SERVICE_ID");
            }
        }
            catch(Exception search)
                    {
                    search.getStackTrace();
                    }
        return ord;
    }
    
    
     //salah
     public boolean SendMessage(int ID, EmployeeMessage Message) {
         System_mang.Connect();
        Manager M = new Manager();
        try {
            if (M.SearchCustomer(ID) == null) {
                System.out.println("error");
                System_mang.Close();
                return false;  
            } else {
                 int state = 0;
                 String Query = "INSERT INTO message(MESSAGE_ID,SENDER_ID,CONTENT,STATE,DATE,RECIVER_ID) values (?,?,?,?,?,?)";
                 PreparedStatement st = System_mang.Con_DB.prepareStatement(Query);
                 st.setInt(1, Message.ID);
                 st.setInt(2, ID);
                 st.setString(3, Message.Content);
                 st.setInt(4, state);
                 st.setString(5, Message.Date);
                 st.setInt(6, Message.SenderID);
                 st.execute();
            }
        } catch (SQLException ex) {   
        
            ex.getStackTrace();
            System_mang.Close();
            return false;
        }
        return true;
    }
    
    
    
    Bill SearchBill(int Billid) {
        System_mang.Connect();
        ResultSet result = null;
        PreparedStatement pat = null;
        String Query = "SELECT * FROM bill where BILL_ID= ? ";
        Bill bb = new Bill();
        try {
            pat = System_mang.Con_DB.prepareStatement(Query);
            pat.setInt(1, Billid);
            pat.execute();
            result = pat.getResultSet();
            while (result.next()) {
                bb.ID = result.getInt("BILL_ID");
                bb.Date = result.getString("BILL_DATE");
                bb.Cost = result.getDouble("BILL_COST");
                bb.Myorder_id=result.getInt("ORDER_ID");
            }
        } catch (Exception E) {}
        return bb;
    }
    
    
    
    
    Device SearchDevice(int Deviceid) {
        Device D = new Device();
        System_mang.Connect();
        ResultSet result = null;
        PreparedStatement pat = null;
        String Query = "SELECT * FROM damage_device where feedback= ? ";
        try {
            pat = System_mang.Con_DB.prepareStatement(Query);
            pat.setInt(1, Deviceid);
            pat.execute();
            result = pat.getResultSet();
            while (result.next()) {
                D.ID = result.getInt("DEVICE_ID");
                D.Marke = result.getNString("DEVICE_MARK");
                D.DeviceType = result.getNString("DEVICE_TYPE");
                D.Address2=result.getString("ADDRESS_2");
                D.State=result.getString("STATE");
                D.Requistid=result.getInt("REQUIST_ID");
                D.Description=result.getString("DESCRIPTION");
                D.Phone2=result.getString("PHONE_2");
                D.Customerid=result.getInt("CUST_ID");
            }
        } catch (Exception E) {}
        return D;
    }
    
    
    
    
    
    
    
    FeedBack SearchFeedback(int orderid) {
        FeedBack F = new FeedBack();
        System_mang.Connect();
        ResultSet result = null;
        PreparedStatement pat = null;
        String Query = "SELECT * FROM bill where feedback= ? ";
        try {
            pat = System_mang.Con_DB.prepareStatement(Query);
            pat.setInt(1, orderid);
            pat.execute();
            result = pat.getResultSet();
            while (result.next()) {
                F.id = result.getInt("FEEDBACK_ID");
                F.OrderID = result.getInt("ORDER_ID");
                F.PersonalOpenion = result.getNString("PERSONAL_OPENION");
                F.ServiceQuility=result.getInt("SYSTEM_QUALITY");
            }
        } catch (Exception E) {}
        return F;
    }
    
    
}
