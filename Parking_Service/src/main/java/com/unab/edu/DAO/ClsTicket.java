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
import com.unab.edu.Entidades.Ticket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsTicket {
    
    ConexionBD classconectar = new ConexionBD();
    Connection conectar = classconectar.RetornarConexion();
    
    public ArrayList<Ticket> MostrarTicket() {
        ArrayList<Ticket> lista = new ArrayList<>();
        try {
            CallableStatement call = conectar.prepareCall("Call SP_S_Ticket()");
            ResultSet resultado = call.executeQuery();
            while (resultado.next()) {
                Ticket sal = new Ticket();
                sal.setId_ticket(resultado.getInt("id_tickect"));              
                sal.setFecha(resultado.getDate("fecha"));
                sal.setId_auto(resultado.getInt("id_auto"));
                sal.setEstado(resultado.getInt("estado"));
                lista.add(sal);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return lista;
    }
    
    
    public void GuardarTicket(Ticket sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_I_Ticket(?,?)");
            call.setDate("pfecha", sal.getFecha());  
            call.setInt("pid_auto", sal.getId_auto()); 
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void EliminarTicket(Ticket sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_D_Ticket(?)");
            call.setInt("pid", sal.getId_ticket());            
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Eliminado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
