/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import Convertidores.ConvertidorCategoriaArray;
import Convertidores.ConvertidorComentarioArray;
import Convertidores.ConvertidorMeGusta;
import Convertidores.ConvertidorPublicacion;
import Convertidores.ConvertidorRevistaArray;
import EntidadesRevista.Categoria;
import EntidadesRevista.Comentario;
import EntidadesRevista.MeGusta;
import EntidadesRevista.Publicacion;
import EntidadesRevista.Revista;
import RegisterModel.DBEscogerCategorias;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class InfoRevista {

    private ConvertidorCategoriaArray categoriaArray;
    private ConvertidorRevistaArray revistaArray;
    private ConvertidorComentarioArray comentarioArray;

    public InfoRevista() {
        this.categoriaArray = new ConvertidorCategoriaArray(Categoria[].class);
        this.revistaArray = new ConvertidorRevistaArray(Revista[].class);
        this.comentarioArray = new ConvertidorComentarioArray(Comentario[].class);
    }
    
    public String getListadoPublicaciones(String nombreRevista){
        return new Gson().toJson(new DBRevista().getListadoPublicaciones(nombreRevista));
    }
    
    public String getListadoComentarios(String nombreRevista){
        //return comentarioArray.toJson((Comentario[])new DBRevista().getListadoPublicaciones(nombreRevista).toArray());
        return new Gson().toJson(new DBRevista().getListadoComentarios(nombreRevista));
    }
    
    public String getlistadoRevistasFiltro(String filtroBusqueda, String busqueda) {
        return new Gson().toJson(new DBRevista().obtenerRevistasParametro(filtroBusqueda,busqueda));
    }
    
    public String getListadoCategoriasPreferencia(String userName) {
        return new Gson().toJson(new DBEscogerCategorias().getListadoCategorias(userName));
    }
    
    public String getListadoEtiquetasPreferencia(String userName) {
        return new Gson().toJson(new DBEscogerEtiquetas().getListadoEtiquetas(userName));
    }
    
    public String cantidadEstadisticaRevista(String nombreRevista, String estadistica){
        return new Gson().toJson(new DBEstadisticasRevista().cantidadEstadisticaRevista(nombreRevista,estadistica));
    }
    
    public String getReaccionRevista(String nombreRevista, String userName){
        return new ConvertidorMeGusta(MeGusta.class).toJson(new DBEstadisticasRevista().getReaccionUser(nombreRevista, userName));
    }
    
    public String getListadoEtiquetasRevista(String nombreRevista) {
        return new Gson().toJson(new DBEtiqueta().getEtiquetasRevista(nombreRevista));
    }

    public void modificarCampoRevista(String nombreRevista, boolean statusNuevo, String campoModificar) {
        DBModificarRevista dbmr = new DBModificarRevista();
        try {
            switch (campoModificar) {
                case "suscribir":
                dbmr.actualizarStatusSuscripcion(nombreRevista, statusNuevo);
                break;
                case "comentar":
                dbmr.actualizarStatusComentar(nombreRevista, statusNuevo);
                break;
                case "reaccionar":
                dbmr.actualizarStatusReaccion(nombreRevista, statusNuevo);
                break;
                default:
                throw new AssertionError();
            }
        } catch (SQLException e) {
        }
    }
}
