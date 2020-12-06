/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.Entidades;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DELL
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Nivel extends Parking{
    
    private int id_nivel;
    private int capacidad;
    private int estado;
    private int id_parking;
    
}
