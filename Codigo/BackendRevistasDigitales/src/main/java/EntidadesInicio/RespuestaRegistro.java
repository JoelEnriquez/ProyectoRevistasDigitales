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
public class RespuestaRegistro {
    private boolean nombreRepetido;
    private Persona persona;

    public RespuestaRegistro(boolean nombreRepetido, Persona persona) {
        this.nombreRepetido = nombreRepetido;
        this.persona = persona;
    }
    
    public boolean isNombreRepetido() {
        return nombreRepetido;
    }

    public void setNombreRepetido(boolean nombreRepetido) {
        this.nombreRepetido = nombreRepetido;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

   
    
    
}
