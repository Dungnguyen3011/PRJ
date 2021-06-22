/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnt.DAO;

import dungnt.DBUtils.DBUtils;
import dungnt.DTO.Account;
import dungnt.DTO.Category;
import dungnt.DTO.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent
 */
public class DAO {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    public List<Product> getAllProduct(){
     List<Product> list= new ArrayList<>();
     String query="select id, name, [image], price, title, [description], cateID, sell_ID from product";
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
       
     return list;
     
    }
    
    public List<Category> getAllCategory(){
     List<Category> list= new ArrayList<>();
     String query="select cid, cname from Category";
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
                     
            }
        } catch (Exception e) {
        }
     return list;
    }
    public Product getLast(){
        String query="select id, name, [image], price, title, [description], cateID, sell_ID from product\n" 
                + "order by id desc";
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Product> getProductByCID(String cid){
     List<Product> list= new ArrayList<>();
     String query="select id, name, [image], price, title, [description], cateID, sell_ID from product\n"
                + " where cateID = ?";
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, cid);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
     return list;
    }
    
    public Product getProductByID(String id){
     String query="select id, name, [image], price, title, [description], cateID, sell_ID from product\n"
                + " where id = ?";
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
            return      new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
            
    public List<Product> searchByName(String txtSearch){
     List<Product> list= new ArrayList<>();
     String query="select id, name, [image], price, title, [description], cateID, sell_ID from product\n"
                + " where [name] like ?";
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1,"%"+ txtSearch + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
     return list;
    }
    public Account login(String user, String pass){
        String query="select [uID], [user], pass, isSell, isAdmin from Account\n "
                    +" where [user] = ?\n"
                    +" and pass = ?";
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }
       return null; 
    }
    
     public Account checkAccountExist(String user){
        String query="select [uID], [user], pass, isSell, isAdmin from Account\n "
                    +" where [user] = ?\n";
                    
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, user);
           
            rs=ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }
       return null; 
    }
     public void signup(String user, String pass){
         String query="insert into Account\n"
                 +" values(?,?,0,0)";
         try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
         } catch (Exception e) {
         }
     }
     
     public List<Product> getProductBySellID(int id){
     List<Product> list= new ArrayList<>();
     String query="select id, name, [image], price, title, [description], cateID, sell_ID from product\n"
                + " where sell_ID = ?";
        try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
     return list;
    }
     public void deleteProduct(String pid){
         String query="delete from product\n"
                 +" where id= ?";
         try {
            conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
         } catch (Exception e) {
         }
     }
     
     public void insertProduct(String name, String image, String price, String title, String description, String category, int sid){
         String query="INSERT [dbo].[product] \n"
                 +" ([name], [image], [price], [title], [description], [cateID], [sell_ID])"
                 +" values(?,?,?,?,?,?,?)";
         try {
             conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setInt(7, sid);
            ps.executeUpdate();
         } catch (Exception e) {
         }
     }
     
      public void editProduct(String name, String image, String price, String title, String description, String category, String pid){
         String query="update product\n"
                 +" set [name] = ?,\n"
                 +" [image] = ?,\n"
                 +" price = ?,\n"
                 +" title = ?,\n"
                 +" [description] = ?,\n"
                 +" cateID = ?\n"
                 +" where id = ?\n";
         try {
             conn=new DBUtils().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, pid);
            ps.executeUpdate();
         } catch (Exception e) {
         }
     }
      
   
      public Product getProduct(String txt) {
        String query = "select id, [name] from product where id = ?";
        List<Product> list = new ArrayList<>();
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, txt);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                1);
            }
        } catch (Exception e) {
        }
        return null;
    }
      
      public List<Product> getAll() {
        String query = "select id, name, image, price, amount from product";
        List<Product> list = new ArrayList<>();
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5)));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    public Product getProduct2(String txt) {
        String query = "select id, name, image, price, amount from product where id = ?";
        List<Product> list = new ArrayList<>();
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, txt);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5));
            }
            
        } catch (Exception e) {
        }
        return null;
    }
    public static void main(String[] args) {
        DAO dao=new DAO();
        List<Product> list=dao.getAllProduct();    
    }
}

   
