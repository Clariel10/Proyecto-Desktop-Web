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
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsJoinParkingNivel {
    
    ConexionBD cn = new ConexionBD();
    Connection con = cn.RetornarConexion();
    
    public ArrayList <Nivel> MostrarJoinParkingNivel(){
    
    ArrayList <Nivel> lista = new ArrayList<>();
    
        try {
            
            CallableStatement st = con.prepareCall("call SP_S_ParkingjoinNivel");
        
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Nivel es = new Nivel();
                es.setId_parking(rs.getInt("id_parking"));
                es.setUbicacion(rs.getString("ubicacion"));
                es.setId_nivel(rs.getInt("id_nivel"));
                
                lista.add(es);
                
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        
        return lista;
    
    
}
    
}
