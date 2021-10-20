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
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class ActualizarDatosRevista {
    private ConvertidorRevista cr;
    private ConvertidorEtiquetasArray cea;

    public ActualizarDatosRevista() {
        this.cr = new ConvertidorRevista(Revista.class);
        this.cea = new ConvertidorEtiquetasArray(Etiqueta[].class);
    }
    
    public void actualizarDatosRevista(String revista, String listaEtiquetas) throws SQLException{
        Revista revistaUpdate = cr.fromJson(revista);
        Etiqueta[] etiquetasUpdate = cea.fromJson(listaEtiquetas);
        
        DBModificarRevista modificarRevista = new DBModificarRevista();
        //Actualizar info revista
        modificarRevista.actualizarDatosDeRevista(revistaUpdate);
        
        //Eliminar referencias actuales de etiqueta
        modificarRevista.eliminarEtiquetasAsociadas(revistaUpdate.getNombre());
        
        for (Etiqueta etiqueta : etiquetasUpdate) {
            try {
                new DBEtiqueta().insertEtiqueta(etiqueta); //Insertar etiqueta, si ya existe no se inserta.
            } catch (SQLException e) {
            }
            new DBRegisterRevista().asociarEtiqueta(revistaUpdate.getNombre(), etiqueta);
        }
    }
    
    
}
