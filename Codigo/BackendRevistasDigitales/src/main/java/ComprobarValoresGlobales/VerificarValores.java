/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprobarValoresGlobales;

import Convertidores.ConvertidorBuffer;
import Convertidores.ConvertidorValoresGlobales;
import EntidadesApoyo.ValoresGlobales;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class VerificarValores {

    private ConvertidorValoresGlobales cvg;
    private VerificarDB vdb;
    private BufferedReader br;
    
    public VerificarValores() {
        cvg = new ConvertidorValoresGlobales(ValoresGlobales.class);
        vdb = new VerificarDB();
    }

    public VerificarValores(BufferedReader br) {
        this.br = br;
        cvg = new ConvertidorValoresGlobales(ValoresGlobales.class);
        vdb = new VerificarDB();
    }
    
    public String verificarValores(){
        return String.valueOf(vdb.existenciaValores());
    }
    
    public void insertarValores() throws IOException, SQLException, Exception{
        String valores = "";
        try {
           valores = new ConvertidorBuffer().extraerContenido(br);
        } catch (IOException e) {
            throw new IOException("Error en el formato del porcentaje de ganancia");
        }
        ValoresGlobales valoresGlobales = cvg.fromJson(valores);
        vdb.insertarValoresGlobales(valoresGlobales);
        vdb.crearPersonas(); //Insertar personas con contraseña encriptada
    }
    
}
