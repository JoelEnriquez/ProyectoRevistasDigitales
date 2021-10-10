/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesInicio;

import Personas.Persona;

/**
 *
 * @author joel
 */
public class EntidadLogin {
    private Persona persona;
    private String mensaje;

    public EntidadLogin(Persona persona, String mensaje) {
        this.persona = persona;
        this.mensaje = mensaje;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
