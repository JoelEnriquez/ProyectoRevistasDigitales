/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Convertidores;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author joel
 */
public class ConvertidorBuffer {
    
    public String extraerContenido(BufferedReader br) throws IOException{
        String contenido = "";
        String linea = br.readLine();
        while (linea != null) {
            contenido = contenido + linea;
            linea = br.readLine();
        }
        
        return contenido;
    }
}
