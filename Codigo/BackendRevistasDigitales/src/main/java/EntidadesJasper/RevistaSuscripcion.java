/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJasper;

import EntidadesRevista.Revista;
import EntidadesRevista.Suscripcion;
import java.util.List;

/**
 *
 * @author joel
 */
public class RevistaSuscripcion {
    private Revista revista;
    private List<Suscripcion> listadoSuscripciones;

    public RevistaSuscripcion(Revista revista, List<Suscripcion> listadoSuscripciones) {
        this.revista = revista;
        this.listadoSuscripciones = listadoSuscripciones;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public List<Suscripcion> getListadoSuscripciones() {
        return listadoSuscripciones;
    }

    public void setListadoSuscripciones(List<Suscripcion> listadoSuscripciones) {
        this.listadoSuscripciones = listadoSuscripciones;
    }
    
    
}
