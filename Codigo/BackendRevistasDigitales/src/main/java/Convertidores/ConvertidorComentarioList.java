/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Convertidores;

import EntidadesRevista.Comentario;
import java.util.List;

/**
 *
 * @author joel
 */
public class ConvertidorComentarioList extends Convertidor<List<Comentario>> {

    public ConvertidorComentarioList(Class<List<Comentario>> claseConvertir) {
        super(claseConvertir);
    }
    
}
