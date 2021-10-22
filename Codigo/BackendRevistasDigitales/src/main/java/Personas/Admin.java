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
public class Admin extends Persona {

    public Admin(String userName, String password, String nombre, PersonaEnum tipo) {
        super(userName, password, nombre, tipo);
    }

    public Admin(String userName, String password, String nombre) {
        super(userName, password, nombre);
    }

    public Admin(String userName, String password) {
        super(userName, password);
    }
    
    
}
