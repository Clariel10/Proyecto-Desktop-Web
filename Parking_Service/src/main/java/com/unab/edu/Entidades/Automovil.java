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
public class Automovil {
    
    private int id_auto;
    private String placa;
    private String color;
    private int id_parqueo;
    private int estado;
            
    
}
