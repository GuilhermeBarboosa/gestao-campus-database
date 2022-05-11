/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Usuario
 */
public class ConnectionFactory {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
     private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gestao";

     public static Connection createConnectionToMySql() throws Exception{
         try{
              Class.forName("com.mysql.jdbc.Driver");
         }catch(ClassNotFoundException e){
             System.out.println("Conexao nao encontrada");
         }
        
         
         Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
         
         return connection;
     }
     
     public static void main(String[] args) throws Exception {
        Connection con = createConnectionToMySql();
        
        if(con != null){
            System.out.println("Conexão criada");
            con.close();
        }
    }

}
