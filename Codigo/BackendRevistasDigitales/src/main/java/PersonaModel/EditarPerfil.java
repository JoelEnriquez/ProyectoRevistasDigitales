/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonaModel;

import Convertidores.ConvertidorCategoriaArray;
import Convertidores.ConvertidorEditor;
import Convertidores.ConvertidorUsuario;
import EntidadesRevista.Categoria;
import Personas.Editor;
import Personas.Usuario;

/**
 *
 * @author joel
 */
public class EditarPerfil {
    
    private ConvertidorEditor ce;
    private ConvertidorUsuario cu;
    private ConvertidorCategoriaArray cca;
    private DBEditarPerfil dBEditarPerfil;
    private DBInfoPersona dBInfoPersona;
    String tipoPersona;
    String userName;

    public EditarPerfil(String tipoPersona, String userName) {
        this.ce = new ConvertidorEditor(Editor.class);
        this.cu = new ConvertidorUsuario(Usuario.class);
        this.cca = new ConvertidorCategoriaArray(Categoria[].class);
        dBEditarPerfil = new DBEditarPerfil();
        dBInfoPersona = new DBInfoPersona();
        this.tipoPersona = tipoPersona;
        this.userName = userName;
    }
    
    
    
    
    
    
    
    
}
