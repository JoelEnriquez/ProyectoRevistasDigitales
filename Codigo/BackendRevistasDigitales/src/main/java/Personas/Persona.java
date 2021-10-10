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
    private String userName;
    private String password;
    private String nombre;
    private PersonaEnum tipo;

    public Persona(String userName, String password, String nombre, PersonaEnum tipo) {
        this.userName = userName;
        this.password = password;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
    public Persona(String userName, String password, String nombre) {
        this.userName = userName;
        this.password = password;
        this.nombre = nombre;
    }

    public Persona(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
