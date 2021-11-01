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
public class GananciaEditor {
    private Revista revista;
    private List<Suscripcion> suscripciones;

    public GananciaEditor(Revista revista, List<Suscripcion> suscripciones) {
        this.revista = revista;
        this.suscripciones = suscripciones;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public List<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<Suscripcion> suscripciones) {
        this.suscripciones = suscripciones;
    }
    
    
}
