/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import Convertidores.ConvertidorEtiquetasArray;
import Convertidores.ConvertidorRevista;
import EntidadesRevista.Etiqueta;
import EntidadesRevista.Revista;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class RegistrarRevista {
    private ConvertidorRevista cr;
    private ConvertidorEtiquetasArray cea;
    private String revista;
    private String listadoEtiquetas;

    public RegistrarRevista(String revista, String listadoEtiquetas) {
        cr = new ConvertidorRevista(Revista.class);
        cea = new ConvertidorEtiquetasArray(Etiqueta[].class);
        this.revista = revista;
        this.listadoEtiquetas = listadoEtiquetas;
    }

    public RegistrarRevista() {
    }
    
    public void registrarRevista() throws SQLException, IOException{
        Revista revistaInsertar = cr.fromJson(revista);
        DBRegisterRevista registerRevista = new DBRegisterRevista();
        registerRevista.insertarRevista(revistaInsertar); //Insertar revista
        //Insertar etiquetas asociadas a la revista
        Etiqueta[] listEtiquetas = cea.fromJson(this.listadoEtiquetas);
        for (Etiqueta etiqueta : listEtiquetas) {
            try {
                new DBEtiqueta().insertEtiqueta(etiqueta); //Insertar etiqueta, si ya existe, proceder a asociarla
            } catch (SQLException e) {
            }
            registerRevista.asociarEtiqueta(revistaInsertar.getNombre(), etiqueta);
        }
    }
    
    public String getRevistasSinAsignarCosto(){
        return new Gson().toJson(new DBRevista().obtenerRevistasSinCostoDiario());
    }
    
    public void asignarCostoDiario(String nombreRevista, String costo){
        new DBRegisterRevista().asignarCostoDiario(Double.valueOf(costo), nombreRevista);
    }
    
    
}
