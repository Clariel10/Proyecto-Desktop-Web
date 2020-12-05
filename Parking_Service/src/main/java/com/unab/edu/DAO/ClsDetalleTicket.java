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
import com.unab.edu.Entidades.Detalle_Ticket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsDetalleTicket {
    
    ConexionBD classconectar = new ConexionBD();
    Connection conectar = classconectar.RetornarConexion();
    
    public ArrayList<Detalle_Ticket> MostrarDetalleTicket() {
        ArrayList<Detalle_Ticket> lista = new ArrayList<>();
        try {
            CallableStatement call = conectar.prepareCall("Call SP_S_Detalle_Ticket()");
            ResultSet resultado = call.executeQuery();
            while (resultado.next()) {
                Detalle_Ticket sal = new Detalle_Ticket();
                sal.setId_detalle(resultado.getInt("id_detalle"));              
                sal.setId_ticket(resultado.getInt("id_Tickect"));
                sal.setCosto(resultado.getDouble("costo"));
                sal.setHora_inicial(resultado.getTime("hora_inicial"));
                sal.setHora_final(resultado.getTime("hora_final"));
                sal.setEstadia(resultado.getInt("estadia"));
                sal.setEstado(resultado.getInt("estado"));
                lista.add(sal);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return lista;
    }
    
     public void ActualiarDetalleTicket(Detalle_Ticket sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_U_Detalle_Ticket(?)");
            call.setInt("pid", sal.getId_detalle());                      
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void EliminarDetalleTicket(Detalle_Ticket sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_D_Detalle_Ticket(?,?)");
            call.setInt("pid", sal.getId_detalle());  
             call.setInt("pid_ticket", sal.getId_ticket()); 
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Eliminado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
