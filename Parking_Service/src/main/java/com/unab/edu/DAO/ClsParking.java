/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

/**
 *
 * @author DELL
 */

import com.unab.edu.Conexionmysql.ConexionBD;
import com.unab.edu.Entidades.Parking;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsParking {
    
    
    ConexionBD classconectar = new ConexionBD();
    Connection conectar = classconectar.RetornarConexion();
    
    public ArrayList<Parking> MostrarParkings() {
        ArrayList<Parking> lista = new ArrayList<>();
        try {
            CallableStatement call = conectar.prepareCall("Call SP_S_Parking()");
            ResultSet resultado = call.executeQuery();
            while (resultado.next()) {
                Parking sal = new Parking();
                sal.setId_parking(resultado.getInt("id_parking"));              
                sal.setUbicacion(resultado.getString("ubicacion"));
                sal.setCapacidad(resultado.getInt("capacidad"));
                sal.setEstado(resultado.getInt("Estado"));
                lista.add(sal);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return lista;
    }
    
    
    public void GuardarParking(Parking sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_I_Parking(?,?)");
            call.setString("pubicacion", sal.getUbicacion());  
            call.setInt("pcapacidad", sal.getCapacidad()); 
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ActualiarParking(Parking sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_U_Parking(?,?,?)");
            call.setInt("pid", sal.getId_parking());          
            call.setString("pubicacion", sal.getUbicacion());   
            call.setInt("pcapacidad", sal.getCapacidad());    
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EliminarParking(Parking sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_D_Parking(?)");
            call.setInt("pid", sal.getId_parking());            
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Eliminado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
