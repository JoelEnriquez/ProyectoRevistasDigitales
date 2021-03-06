/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileModel;

import Convertidores.ConvertidorCategoriaArray;
import Convertidores.ConvertidorEditor;
import Convertidores.ConvertidorUsuario;
import EntidadesApoyo.RutasEnum;
import EntidadesRevista.Categoria;
import PersonaModel.DBEditarPerfil;
import PersonaModel.DBInfoPersona;
import Personas.Editor;
import Personas.Usuario;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
public class FileModel {
    
    private ConvertidorEditor ce;
    private ConvertidorUsuario cu;
    private ConvertidorCategoriaArray cca;
    private DBEditarPerfil dBEditarPerfil;
    private DBInfoPersona dBInfoPersona;

    public FileModel() {
        this.ce = new ConvertidorEditor(Editor.class);
        this.cu = new ConvertidorUsuario(Usuario.class);
        this.cca = new ConvertidorCategoriaArray(Categoria[].class);
        dBEditarPerfil = new DBEditarPerfil();
        dBInfoPersona = new DBInfoPersona();
    }
    
    public String obtenerInfoEditor(String userName){
        return ce.toJson(dBInfoPersona.getInfoEditor(userName));
    }
    
    public String obtenerInfoUsuario(String userName){
        return cu.toJson(dBInfoPersona.getInfoUsuario(userName));
    }
    
    public InputStream getFotoPerfil(String userName){
        return dBInfoPersona.getFotoPerfil(userName);
    }
    
    public void mostrarFoto(InputStream path, HttpServletResponse response) throws FileNotFoundException, IOException{
        try (BufferedInputStream fileStream = new BufferedInputStream(path)) {
            response.setContentType("image/jpeg");
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        } 
    }
    
    public void mostrarFotoNeutral(HttpServletResponse response) throws FileNotFoundException, IOException{
        try (BufferedInputStream fileStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("NoProFilePicture.jpg"))) {
            response.setContentType("image/jpeg");
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        } 
    }
    
    public void mostrarPDF(HttpServletResponse response, String pathFile) throws FileNotFoundException, IOException{
        try (BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(pathFile))) {
            response.setContentType("application/pdf");
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        } 
    }
    
     public void descargarPDF(HttpServletResponse response, String path) throws FileNotFoundException, IOException {
        FileInputStream inputStream = new FileInputStream(path);
        File f = new File(path);
        String filename = f.getName();
        descargar(response, inputStream, filename);
    }
     
     public void descargar(HttpServletResponse response, InputStream inputStream, String fileName) throws IOException {
        try (BufferedInputStream fileStream = new BufferedInputStream(inputStream)) {
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        }
    }
}
