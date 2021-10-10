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

/**
 *
 * @author joel
 */
public class RegistrarEditor {
    private BufferedReader br;
    private ConvertidorEditor ce;

    public RegistrarEditor(BufferedReader br) {
        this.br = br;
        ce = new ConvertidorEditor(Editor.class);
    }
    
    public Editor registrarEditor() throws IOException, SQLException{
        String contenido = new ConvertidorBuffer().extraerContenido(br);
        Editor editor = ce.fromJson(contenido);
        new DBRegister().insertEditor(editor);
        return editor;
    }
    
    public String editorRegistrado(Editor nuevoEditor){
        return ce.toJson(nuevoEditor);
    }
    
    
}
