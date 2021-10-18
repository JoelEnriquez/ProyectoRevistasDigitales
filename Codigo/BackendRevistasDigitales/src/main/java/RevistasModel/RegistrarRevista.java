/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import Convertidores.ConvertidorBuffer;
import Convertidores.ConvertidorRevista;
import EntidadesRevista.Revista;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class RegistrarRevista {
     private BufferedReader br;
    private ConvertidorRevista cr;

    public RegistrarRevista(BufferedReader br) {
        this.br = br;
        cr = new ConvertidorRevista(Revista.class);
    }
    
    public void registrarRevista() throws SQLException, IOException{
        String contenido = new ConvertidorBuffer().extraerContenido(br);
        Revista revista = cr.fromJson(contenido);
        new DBRevista().insertarRevista(revista);
    }
}
