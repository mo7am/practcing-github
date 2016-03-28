
import java.sql.ResultSet;
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
 public class Customer extends Person implements Deals{
    
    String HomePhone;
    String SecurityQuestion;
    String SecurityQuestionAnwer;

  
    
    
     public static boolean MakeRequist (Requist MyRequist)
    {
        try
        {System_mang.Connect();
         Statement stat = System_mang.Con_DB.createStatement();
         String Query="INSERT INTO requist VALUE("+MyRequist.Requistid+","+MyRequist.Mycustomerid+","+MyRequist.Mydeviceid+")";
         stat.executeUpdate(Query);
        }
        
        catch(Exception ex){
        ex.printStackTrace();
        return false;
        }
        return true;
    }
     
     
     
    TimeChooser SelectTime(TimeChooser MyChoose)
    {
        return MyChoose;
    }
    
    
    
    public static Device TrackMyDevice (int DeviceID)
    {
        try
        {
            System_mang.Connect();
            Statement st=System_mang.Con_DB.createStatement();
            String Query="SELECT *FROM damage_device where DEVICE_ID="+DeviceID;
            st.execute(Query);
            ResultSet result=st.executeQuery(Query);
            
               Device de=new Device();
               if(result !=null)
               {
                while(result.next()){  
               de.Address2=result.getString("ADDRESS_2");
               de.Phone2=result.getString("PHONE_2");
               de.Description=result.getString("DESCRIBTION");
               de.DeviceType=result.getString("DEVICE_TYPE");
               de.State=result.getString("STATE");
               de.Marke=result.getString("DEVICE_MARK");
               de.ID=DeviceID;
               de.Requistid=result.getInt("REQUIST_ID");
               de.Customerid=result.getInt("CUST_ID");
               }
                return de;}
               else
                   return null;
         }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }    
    }
    
    
    
   public  boolean MakeComplain (CustomerMessage MyComplain,int IDorder)
    {
       try
       {
           System_mang.Connect();
           Statement st= System_mang.Con_DB.createStatement();
           String Query="Insert into complain (`COMPLAIN_ID`, `ORDER_ID`, `CONTENT`, `COMPLAIN_STATE`) VALUE("+MyComplain.ID+","+IDorder+","+"'"+MyComplain.Content+"'"+","+"'"+MyComplain.MessageState+"'"+")";
        //   System.err.println(Query +"dsd"+MyComplain.Content);
           st.executeUpdate(Query);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
        return true ;
    }
   
   
   
        public boolean MakeFeedBack (FeedBack MyFeedBack ,int  IDMyOrder)
    {
        try
       {
           System_mang.Connect();
           Statement st= System_mang.Con_DB.createStatement();
           String Query="Insert into feedback VALUE("+MyFeedBack.id+","+IDMyOrder+","+MyFeedBack.ServiceQuility+","+"'"+MyFeedBack.PersonalOpenion+"'"+","+MyFeedBack.SystemQuility+")";
           st.executeUpdate(Query);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
        return true ;  
    }

    public ArrayList<Requist> ShowMyRequist(int  CustomerID)
    {
          try
       {
           System_mang.Connect();
           Statement st= System_mang.Con_DB.createStatement();
           String Query="select * from requist  where 	CUSTOMER_ID ="+CustomerID ;
           
           ResultSet result=st.executeQuery(Query);
           if(result!=null)
           {
               ArrayList<Requist> requst=new ArrayList<>(10);
               Requist re=new Requist();
               while(result.next())
               {   
                    re.Mydeviceid=result.getInt("DEVICE_ID");
                    re.Requistid=result.getInt("REQUIST_ID");
                    re.Mycustomerid=CustomerID;
                    re.State=result.getBoolean("STATE");
                    re.Date=result.getString("REQUIST_DATE");
                    requst.add(re);
                    re=new Requist();
               }
               return requst;
               
           }
           else return null;
           
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
           return null;
        
       }
    }



    @Override
    public String ShowRequist(String State) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
