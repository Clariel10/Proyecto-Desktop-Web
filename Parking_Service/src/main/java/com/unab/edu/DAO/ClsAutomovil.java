/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.DAO;

import com.unab.edu.Conexionmysql.ConexionBD;
import com.unab.edu.Entidades.Automovil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class ClsAutomovil {
    
    ConexionBD classconectar = new ConexionBD();
    Connection conectar = classconectar.RetornarConexion();
    
    public ArrayList<Automovil> MostrarAutos() {
        ArrayList<Automovil> lista = new ArrayList<>();
        try {
            CallableStatement call = conectar.prepareCall("Call SP_S_Automovil()");
            ResultSet resultado = call.executeQuery();
            while (resultado.next()) {
                Automovil sal = new Automovil();
                sal.setId_auto(resultado.getInt("id_auto"));
                sal.setColor(resultado.getString("color"));
                sal.setPlaca(resultado.getString("placa"));
                sal.setId_parqueo(resultado.getInt("id_parqueo"));
                sal.setEstado(resultado.getInt("estado"));
                lista.add(sal);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return lista;
    }
    
    
    public void GuardarAuto(Automovil sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_I_Automovil(?,?,?)");
            call.setString("pplaca", sal.getPlaca());
            call.setString("pcolor", sal.getColor());
            call.setInt("pid_parqueo", sal.getId_parqueo());            
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ActualiarAuto(Automovil sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_U_Automovil(?,?,?,?)");
            call.setInt("pid", sal.getId_auto());
            call.setString("pplaca", sal.getPlaca());
            call.setString("pcolor", sal.getColor());
            call.setInt("pid_parqueo", sal.getId_parqueo());            
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EliminarAuto(Automovil sal) {
        try {
            CallableStatement call = conectar.prepareCall("call SP_D_Automovil(?,?)");
            call.setInt("pid", sal.getId_auto());
            call.setInt("pid_parqueo", sal.getId_parqueo());
            call.executeQuery();
            conectar.close();
            JOptionPane.showMessageDialog(null, "Eliminado con exito");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
