/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import Convertidores.ConvertidorComentario;
import Convertidores.ConvertidorEtiquetasArray;
import Convertidores.ConvertidorMeGusta;
import Convertidores.ConvertidorRevista;
import EntidadesRevista.Comentario;
import EntidadesRevista.Etiqueta;
import EntidadesRevista.MeGusta;
import EntidadesRevista.Revista;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class ActualizarDatosRevista {

    private ConvertidorRevista cr;
    private ConvertidorEtiquetasArray cea;
    private ConvertidorComentario cc;

    public ActualizarDatosRevista() {
        this.cr = new ConvertidorRevista(Revista.class);
        this.cea = new ConvertidorEtiquetasArray(Etiqueta[].class);
        this.cc = new ConvertidorComentario(Comentario.class);
    }

    public void actualizarDatosRevista(String revista, String listaEtiquetas) throws SQLException {
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

    public String actualizarReaccion(String contenidoMeGusta) {
        ConvertidorMeGusta cmg = new ConvertidorMeGusta(MeGusta.class);
        DBModificarRevista modificarRevista = new DBModificarRevista();

        MeGusta meGusta = cmg.fromJson(contenidoMeGusta);
        MeGusta reaccionActual = new DBEstadisticasRevista().getReaccionUser(meGusta.getNombreRevista(), meGusta.getUserName());
        if (reaccionActual.getNombreRevista().equals("") && reaccionActual.getUserName().equals("")) {
            meGusta.setFechaReaccionDate(Date.valueOf(meGusta.getFechaReaccion())); //Transform the string to date
            modificarRevista.agregarReaccionUsuario(meGusta);
            return cmg.toJson(meGusta);
        } else {
            reaccionActual = modificarRevista.eliminarReaccionUsuario(meGusta);
            return cmg.toJson(reaccionActual);
        }
    }

    public String publicarComentario(String comentarioString) throws SQLException {
        Comentario comentario = cc.fromJson(comentarioString);
        DBModificarRevista modificarRevista = new DBModificarRevista();
        modificarRevista.agregarComentario(comentario);
        return cc.toJson(comentario);
    }

}
