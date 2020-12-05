/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.Entidades;

import lombok.Data;
import java.sql.Time;

/**
 *
 * @author DELL
 */
@Data

public class Detalle_Ticket {
    
    private int id_detalle;
    private int id_ticket;
    private double costo;
    private Time hora_inicial;
    private Time hora_final;
    private int estadia;
    private int estado;
    
}
