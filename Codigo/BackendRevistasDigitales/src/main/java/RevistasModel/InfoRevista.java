/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import Convertidores.ConvertidorCategoriaArray;
import Convertidores.ConvertidorRevistaArray;
import EntidadesRevista.Categoria;
import EntidadesRevista.Revista;
import RegisterModel.DBEscogerCategorias;
import com.google.gson.Gson;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class InfoRevista {

    private ConvertidorCategoriaArray categoriaArray;
    private ConvertidorRevistaArray revistaArray;

    public InfoRevista() {
        this.categoriaArray = new ConvertidorCategoriaArray(Categoria[].class);
        this.revistaArray = new ConvertidorRevistaArray(Revista[].class);
    }
    
    public String getlistadoRevistasPropias(String userNameEditor) {
        return new Gson().toJson(new DBRevista().obtenerRevistasPropias(userNameEditor));
    }
    
    public String getListadoCategoriasPreferencia(String userName) {
        return new Gson().toJson(new DBEscogerCategorias().getListadoCategorias(userName));
    }
    
    public String getListadoRevistasPorCategoria(String categoria) {
        return new Gson().toJson(new DBRevista().obtenerRevistasPorCategoria(categoria));
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
