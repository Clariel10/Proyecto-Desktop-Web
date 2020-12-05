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
import com.unab.edu.Entidades.Nivel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsNivel {
    
    ConexionBD classconectar = new ConexionBD();
    Connection conectar = classconectar.RetornarConexion();
    
    public ArrayList<Nivel> MostrarNiveles() {
        ArrayList<Nivel> lista = new ArrayList<>();
        try {
            CallableStatement call = conectar.prepareCall("Call SP_S_Nivel()");
            ResultSet resultado = call.executeQuery();
            while (resultado.next()) {
                Nivel sal = new Nivel();
                sal.setId_nivel(resultado.getInt("id_nivel"));              
                sal.setCapacidad(resultado.getInt("capacidad"));
                sal.setEstado(resultado.getInt("Estado"));
                sal.setId_parking(resultado.getInt("id_parking"));
                lista.add(sal);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return lista;
    }
    
    
    public void GuardarNivel(Nivel sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_I_Nivel(?,?)");
            call.setInt("pcapacidad", sal.getId_nivel());  
            call.setInt("id_parking", sal.getId_nivel()); 
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ActualiarNivel(Nivel sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_U_Nivel(?,?,?)");
            call.setInt("pid", sal.getId_nivel());          
            call.setInt("pcapacidad", sal.getCapacidad());   
            call.setInt("pid_parking", sal.getId_parking());    
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EliminarNivel(Nivel sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_D_Nivel(?)");
            call.setInt("pid", sal.getId_nivel());            
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Eliminado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
