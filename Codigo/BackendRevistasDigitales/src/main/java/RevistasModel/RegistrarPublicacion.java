/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import Convertidores.ConvertidorPublicacion;
import EntidadesApoyo.RutasEnum;
import EntidadesRevista.Publicacion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import javax.servlet.http.Part;

/**
 *
 * @author joel
 */
public class RegistrarPublicacion {
    
    private ConvertidorPublicacion cp;
    private DBRegistrarPublicacion dbrp;
    private Part file;
    private String publicacion;
    private String pathRelativo;
    private String filePath = "";
    private int idPublicacion = 0;

    public RegistrarPublicacion(Part file, String publicacion, String pathRelativo) {
        this.cp = new ConvertidorPublicacion(Publicacion.class);
        this.file = file;
        this.publicacion = publicacion;
        dbrp = new DBRegistrarPublicacion();
        this.pathRelativo = pathRelativo;
    }
    
    public void insertarPublicacion() throws DateTimeParseException, SQLException, Exception{
        Publicacion nuevaPublicacion = cp.fromJson(publicacion);
        //Insertar publicacion en db
        idPublicacion =  dbrp.insertarPublicacion(nuevaPublicacion);
    }
    
    public void guardarArchivoEnServer() throws IOException{
        InputStream archivo = file.getInputStream();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(archivo))) {
            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }
            filePath = pathRelativo + RutasEnum.RUTA_TO_SAVE_PDF.getRuta()+file.getSubmittedFileName();
            file.write(filePath);
        } catch (IOException ex) {
            throw new IOException("No se ha podido guardar el archivo. Es probable que no haya provisto el formato correcto");
        }
    }
    
    public void guardarRutaEnDB(){
        dbrp.guardarRuta(filePath, idPublicacion);
    }
    
    
}
