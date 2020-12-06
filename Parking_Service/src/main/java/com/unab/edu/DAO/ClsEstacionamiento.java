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
import com.unab.edu.Entidades.Estacionamiento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsEstacionamiento {
    
    ConexionBD classconectar = new ConexionBD();
    Connection conectar = classconectar.RetornarConexion();
    
    public ArrayList<Estacionamiento> MostrarEstacionamientos() {
        ArrayList<Estacionamiento> lista = new ArrayList<>();
        try {
            CallableStatement call = conectar.prepareCall("Call SP_S_Estacionamiento()");
            ResultSet resultado = call.executeQuery();
            while (resultado.next()) {
                Estacionamiento sal = new Estacionamiento();
                sal.setId_estacionamiento(resultado.getInt("id_estacionamiento"));              
                sal.setId_nivel(resultado.getInt("id_nivel"));
                sal.setEstado(resultado.getInt("Estado"));
                lista.add(sal);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return lista;
    }
    
     public ArrayList<Estacionamiento> MostrarEstacionamientoEspecifico(Estacionamiento sal) {
        ArrayList<Estacionamiento> lista = new ArrayList<>();
        try {
             CallableStatement call = conectar.prepareCall("call SP_S_EstacionamientoEspecifico(?)");
            call.setInt("pid_parqueo", sal.getId_parqueo());                     
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Cargando");
        } catch (Exception e) {
            System.out.println(e);
        }

        return lista;
    }
    
    
    public void GuardarEstacionamiento(Estacionamiento sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_I_Estacionamiento(?)");
            call.setInt("pid_nivel", sal.getId_nivel());                     
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ActualiarEstacionamiento(Estacionamiento sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_U_Estacionamiento(?,?)");
            call.setInt("pid", sal.getId_estacionamiento());          
            call.setInt("pid_nivel", sal.getId_nivel());            
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EliminarEstacionamiento(Estacionamiento sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_D_Estacionamiento(?)");
            call.setInt("pid", sal.getId_estacionamiento());            
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Eliminado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
