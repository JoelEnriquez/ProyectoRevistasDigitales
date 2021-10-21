/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterModel;

import Convertidores.ConvertidorBuffer;
import Convertidores.ConvertidorEditor;
import EntidadesInicio.RespuestaRegistro;
import Personas.Editor;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.Part;

/**
 *
 * @author joel
 */
public class RegistrarEditor {
    private ConvertidorEditor ce;

    public RegistrarEditor() {
        ce = new ConvertidorEditor(Editor.class);
    }
    
    public Editor registrarEditor(String contenido, Part imagen) throws IOException, SQLException{
        Editor editor = ce.fromJson(contenido);
        if (imagen!=null) {
            editor.setFoto(imagen.getInputStream());
        }
        new DBRegister().insertEditor(editor);
        return editor;
    }
    
    public String editorRegistrado(Editor nuevoEditor){
        nuevoEditor.setFoto(null); //Borrar la foto del objeto editor
        return ce.toJson(nuevoEditor);
    }
    
    
}