/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unab.edu.Entidades;

import lombok.Data;

/**
 *
 * @author DELL
 */
@Data
public class Estacionamiento {
    
    private int id_estacionamiento;
    private int estado;
    private int id_nivel;
    private int id_parqueo;
}
