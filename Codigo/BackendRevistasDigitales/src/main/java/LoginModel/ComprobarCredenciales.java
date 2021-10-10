/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginModel;

import Convertidores.ConvertidorBuffer;
import Convertidores.ConvertidorPersona;
import Personas.Persona;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author joel
 */
public class ComprobarCredenciales {
    
    private BufferedReader br;
    private ConvertidorPersona cel;

    public ComprobarCredenciales(BufferedReader br) {
        this.br = br;
        cel = new ConvertidorPersona(Persona.class);
    }
    
    public Persona verificarPersona() throws IOException{
        String contenido = new ConvertidorBuffer().extraerContenido(br);
        return new DBLogin().getTipoPersona(cel.fromJson(contenido)); //Verificar que tipo de persona es, en base a sus credenciales 
    }
    
    public String personaVerificada(Persona persona){
        return cel.toJson(persona);
    }
}
