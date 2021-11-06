/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJasper;

import EntidadesRevista.MeGusta;
import EntidadesRevista.Revista;
import java.util.List;

/**
 *
 * @author joel
 */
public class RevistaReaccion {
    private Revista revista;
    private List<MeGusta> meGusta;

    public RevistaReaccion(Revista revista, List<MeGusta> meGusta) {
        this.revista = revista;
        this.meGusta = meGusta;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public List<MeGusta> getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(List<MeGusta> meGusta) {
        this.meGusta = meGusta;
    }
    
    
}
