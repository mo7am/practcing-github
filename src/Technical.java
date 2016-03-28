
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Technical extends Employee implements Usage{
    String Region;
    ArrayList<Order>MyOrders;

    
    public void IsFixed (Bill OrderBill,int OrderID,String tecnicalDescription)
    {
        System_mang.Connect();
        String a="Done";
        String state1="update order_information set STATE='"+a+"',TECHNICAL_DESCRIPTION='"+tecnicalDescription+"' where ORDER_ID="+OrderID;
       
         try {
            PreparedStatement st= System_mang.Con_DB.prepareStatement(state1);
            st.execute();
            String state2="INSERT INTO bill (BILL_DATE ,BILL_COST ,ORDER_ID) VALUES (? ,? ,? )";
            st = System_mang.Con_DB.prepareStatement(state2);
            st.setString(1, OrderBill.Date);
            st.setDouble(2, OrderBill.Cost);
            st.setInt(3, OrderBill.Myorder_id);
            st.execute();
             } 
        catch (Exception e) {
            System.out.println("error in function isfixed in class technical");
            System_mang.Close();
        }

        System_mang.Close();
    }
    
    //return to Company
    public void returnOrder(Order order,CustomerMessage returnComplain)
    {
        System_mang.Connect();
       
        String tech_desc = returnComplain.Content;
        String returnOrder_Queary ="UPDATE order_information SET STATE = 'NotDone' , TECHNICAL_DESCRIPTION= '"+tech_desc+"' WHERE ORDER_ID= "+ order.ID ;
        try {
          Statement statt = System_mang.Con_DB.createStatement();
                      statt.executeUpdate(returnOrder_Queary);
                      

        } catch (SQLException ex) {
            System.out.println("error in function returnorder in tecnical");
        }
      
        System_mang.Close();
        
    }
    
    
    
    //salah
    public ArrayList<Order> ShowMyOrder()
    {
        ArrayList <Order> orders =new ArrayList<Order>();
        Order order = new Order();
        try{
          System_mang.Connect();
          ResultSet result = null;
         PreparedStatement pat = null;
          String Query_order = "SELECT * FROM order_information";
           pat = System_mang.Con_DB.prepareStatement(Query_order);
           result = pat.executeQuery();
        if(result != null){  
            System.out.println("saaas");
          while(result.next())
          {
            order.ID = result.getInt("ORDER_ID");
             order.Mytechnical_id = result.getInt("TECHNICAL_ID");
             order.MyDeviceID = result.getInt("DEVICE_ID");
             order.StartDate = result.getString("START_DATE");
             order.Appointmentoffeies = result.getString("APPOINTMENT_OF_RECIEPT");
             order.Myservice_id = result.getInt("SERVICE_ID");
             order.State = result.getString("STATE");
             order.Tecnical_Description = result.getString("TECHNICAL_DESCRIPTION");
             orders.add(order);
             order=new Order();
          }
        }
          
        }
        catch(Exception orde)
        {
            orde.getStackTrace();
            System_mang.Close();
            return null;
        }
        return orders;
    }
    
   
    public void UpComplainToManager(MainMessage AnyMessage) {
        
       
        
       
    }

    @Override
    public void UpCompainToManager(int AnyMessage) {
        
         System_mang.Connect();
        try {
           int id= this.ID;
         
            Branch B =new Branch();        
            PreparedStatement pat = null;
            System_mang.SearchBranch(this.ID);
            String Query = "UPDATE complain set RECIVER_ID= ?WHERE COMPLAIN_ID = ?";
            pat=System_mang.Con_DB.prepareStatement(Query);
            pat.setInt(1, B.MyManager_ID);
            pat.setInt(2, AnyMessage);
            pat.executeQuery();
            
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        
       
    }

}
