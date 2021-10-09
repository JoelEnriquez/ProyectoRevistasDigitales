/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personas;

/**
 *
 * @author joel
 */
public class Persona {
    private String codigo;
    private String password;
    private String nombre;
    private PersonaEnum tipo;

    public Persona(String codigo, String password, String nombre, PersonaEnum tipo) {
        this.codigo = codigo;
        this.password = password;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
    public Persona(String codigo, String password, String nombre) {
        this.codigo = codigo;
        this.password = password;
        this.nombre = nombre;
    }

    public Persona(String codigo, String password) {
        this.codigo = codigo;
        this.password = password;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PersonaEnum getTipo() {
        return tipo;
    }

    public void setTipo(PersonaEnum tipo) {
        this.tipo = tipo;
    }
    
    
}
