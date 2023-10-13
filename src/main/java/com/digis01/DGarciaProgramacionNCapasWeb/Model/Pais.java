/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasWeb.Model;


/**
 *
 * @author ALIEN 34
 */
public class Pais {
    
    private int idpais;
    private String nombre;

    public Pais() {
    }

    public Pais(int idpais) {
        this.idpais = idpais;
    }

    public Pais(int idpais, String nombre) {
        this.idpais = idpais;
        this.nombre = nombre;
    }

    public int getIdpais() {
        return idpais;
    }

    public void setIdpais(int idpais) {
        this.idpais = idpais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
    
    
}
