/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginModel;

import Convertidores.ConvertidorBuffer;
import Convertidores.ConvertidorPersona;
import EntidadesApoyo.Encriptar;
import Personas.Editor;
import Personas.Persona;
import Personas.PersonaEnum;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author joel
 */
public class ComprobarCredenciales {
    
    private BufferedReader br;
    private ConvertidorPersona cel;
    private DBLogin dbLogin;

    public ComprobarCredenciales(BufferedReader br) {
        this.br = br;
        cel = new ConvertidorPersona(Persona.class);
        dbLogin = new DBLogin();
    }
    
    public Persona verificarPersona() throws IOException, Exception{
        String contenido = new ConvertidorBuffer().extraerContenido(br);
        Persona persona = cel.fromJson(contenido);
        persona.setPassword(new Encriptar().encriptar(persona.getPassword()));
        return dbLogin.getTipoPersona(persona); //Verificar que tipo de persona es, en base a sus credenciales 
    }
    
    public String personaVerificada(Persona persona){
        return cel.toJson(persona);
    }
    
    
}
