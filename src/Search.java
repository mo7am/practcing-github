/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emad
 */
public interface Search {
    public Order SearchOrder(int OrderID);
    public boolean SendMessage(int ID, EmployeeMessage Message) ;
}
