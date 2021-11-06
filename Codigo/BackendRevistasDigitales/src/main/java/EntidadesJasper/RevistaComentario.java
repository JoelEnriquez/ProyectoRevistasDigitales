/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJasper;

import EntidadesRevista.Comentario;
import EntidadesRevista.Revista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joel
 */
public class RevistaComentario {
    
    private Revista revista;
    private List<Comentario> comentarios;

    public RevistaComentario(Revista revista, List<Comentario> comentarios) {
        this.revista = revista;
        this.comentarios = comentarios;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    
}
