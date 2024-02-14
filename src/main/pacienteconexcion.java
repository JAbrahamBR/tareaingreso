/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tareaingreso;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ABRAHAM
 */
public class pacienteconexcion {

Connection conectar = null;

 String usuario = "postgres";
 String contraseña = "muni123$";
 String bd= "basepaciente";
 String ip = "localhost";
 String puerto = "5432";
 
 String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
 
public Connection establecerConexion(){
  
    
    try {
        
        Class.forName("org.postgresql.Driver");
        
        conectar = DriverManager.getConnection(cadena,usuario,contraseña);
        
        //JOptionPane.showMessageDialog(null, "SE CONECTO CORRECTAMENTE A LA BASE DE DATOS");
        
    } catch (Exception e) {
        
        JOptionPane.showMessageDialog(null, "ERROR NO CONECTO A LA BASE DE DATOS"+ e.toString());
    }
    
    return conectar;
}
    
}
 
 
