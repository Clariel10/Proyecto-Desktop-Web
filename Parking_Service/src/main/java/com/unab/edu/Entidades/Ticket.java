/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.Entidades;

import lombok.Data;
import java.sql.Date;

/**
 *
 * @author DELL
 */
@Data
public class Ticket {
    
    private int id_ticket;
    private Date fecha;
    private int id_auto;
    private int estado;
    
}
