/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tareaingreso;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ABRAHAM
 */
public class Cpaciente {
    
    int id;
    String nombre;
    String apellido;
    String sexo;
    String direccion;
    String ingreso;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

 
    public void MostrarPaciente (JTable paramTablaTotalPaciente){
    
    pacienteconexcion objetoConexion = new pacienteconexcion();
    
    
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="";
        
        modelo.addColumn("id");
        modelo.addColumn("nombre");
        modelo.addColumn("apellido");
        modelo.addColumn("sexo");
        modelo.addColumn("direccion");
        modelo.addColumn("ingreso");
        
        
        paramTablaTotalPaciente.setModel(modelo);
        
    
        sql = "select * from pacientes;";
        
        String [] datos = new String [6];
        
        Statement st;
        
        try {
            
            st= objetoConexion.establecerConexion().createStatement();
      
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
         
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                datos[3]= rs.getString(4);
                datos[4]= rs.getString(5);
                datos[5]= rs.getString(6);
                
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalPaciente.setModel(modelo);
            
        } catch (Exception e) {
            
            
            JOptionPane.showMessageDialog(null, "ERROR:"+e.toString());
        }
       
        
        
    
    }
    
    public void insertarPaciente(JTextField paraNombre, JTextField paraApellido, JTextField paraSexo, JTextField paraDireccion, JTextField paraIngreso){
        
        setNombre(paraNombre.getText());
        setApellido(paraApellido.getText());
        setSexo(paraSexo.getText());
        setDireccion(paraDireccion.getText());
        setIngreso(paraIngreso.getText());
        
        
        pacienteconexcion objetoConexion = new pacienteconexcion();
        
        String consulta = "insert into pacientes (nombre,apellido,sexo,direccion,ingreso) values (?,?,?,?,?);";
        
        try {
            CallableStatement cs = objetoConexion.establecerConexion() .prepareCall(consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getSexo());
            cs.setString(4, getDireccion());
            cs.setString(5, getIngreso());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "SE INGRESO CORRECTAMENTE");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "NO SE INGRESO");
        }
 
        
    }

    
   public void SeleccionarPaciente(JTable paramJTable, JTextField paramId, JTextField paramNombre, JTextField paramApellido, JTextField paramSexo, JTextField paramDireccion, JTextField paramIngreso){
     
       try {
           
           int fila =paramJTable.getSelectedRow();
           
           if (fila>=0){
               
               paramId.setText(paramJTable.getValueAt(fila, 0).toString());
               paramNombre.setText(paramJTable.getValueAt(fila, 1).toString());
               paramApellido.setText(paramJTable.getValueAt(fila, 2).toString());
               paramSexo.setText(paramJTable.getValueAt(fila, 3).toString());
               paramDireccion.setText(paramJTable.getValueAt(fila, 4).toString());
               paramIngreso.setText(paramJTable.getValueAt(fila, 5).toString());
                 
           }
           
           else
           {
           JOptionPane.showMessageDialog(null, "FILA NO SELECCIONADA:");    
           }
           
       } catch (Exception e) {
           
           JOptionPane.showMessageDialog(null, "ERROR:"+e.toString());
           
       }
       
       
       
       
   }
   
   
      public void modificarPaciente(JTextField paramId, JTextField paramNombre, JTextField paramApellido, JTextField paramSexo, JTextField paramDireccion, JTextField paramIngreso){
        
        setId(Integer.parseInt(paramId.getText()));
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        setSexo(paramSexo.getText());
        setDireccion(paramDireccion.getText());
        setIngreso(paramIngreso.getText());
        
        
        pacienteconexcion objetoConexion = new pacienteconexcion();
        
        String consulta = "UPDATE pacientes SET nombre= ?, apellido= ?, sexo= ?, direccion= ?, ingreso= ? WHERE pacientes.id=?;";
        
        try {
            CallableStatement cs = objetoConexion.establecerConexion() .prepareCall(consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getSexo());
            cs.setString(4, getDireccion());
            cs.setString(5, getIngreso());
            cs.setInt(6, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "SE MODIFICO CORRECTAMENTE");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "NO SE INGRESO");
        }
 
        
    }
      
           public void eliminarPaciente(JTextField paramId){
        
        setId(Integer.parseInt(paramId.getText()));
        
        
        
        pacienteconexcion objetoConexion = new pacienteconexcion();
        
        String consulta = "DELETE  FROM pacientes WHERE pacientes.id=?";
        
        try {
            CallableStatement cs = objetoConexion.establecerConexion() .prepareCall(consulta);
            
            cs.setInt(1, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "NO SE INGRESO");
        }
 
        
    }
    
}
