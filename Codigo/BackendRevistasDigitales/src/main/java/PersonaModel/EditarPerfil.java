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
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.http.Part;

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
    String persona;
    Part foto;

    public EditarPerfil(String persona, Part foto) {
        this.ce = new ConvertidorEditor(Editor.class);
        this.cu = new ConvertidorUsuario(Usuario.class);
        this.cca = new ConvertidorCategoriaArray(Categoria[].class);
        dBEditarPerfil = new DBEditarPerfil();
        dBInfoPersona = new DBInfoPersona();
        this.persona = persona;
        this.foto = foto;
    }
    
    public String actualizarInfoEditor() throws IOException, SQLException{
        Editor editor = ce.fromJson(persona);
        if (foto!=null) {
            editor.setFotoPerfil(foto.getInputStream());
        }
        dBEditarPerfil.actualizarInfoEditor(editor);
        //Set null foto and send to fronted
        editor.setFotoPerfil(null);
        return ce.toJson(editor);
    }
}
