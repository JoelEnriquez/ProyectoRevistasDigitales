/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesRevista;

import java.sql.Date;



/**
 *
 * @author joel
 */
public class Publicacion {
    private int id;
    private String nombrePublicacion;
    private String fechaPublicacion;
    private String nombreRevista;
    private String pathArchivo;

    public Publicacion(int id, String nombrePublicacion, String fechaPublicacion, String nombreRevista, String pathArchivo) {
        this.id = id;
        this.nombrePublicacion = nombrePublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.nombreRevista = nombreRevista;
        this.pathArchivo = pathArchivo;
    }

    public Publicacion(String nombrePublicacion, String fechaPublicacion, String nombreRevista, String pathArchivo) {
        this.nombrePublicacion = nombrePublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.nombreRevista = nombreRevista;
        this.pathArchivo = pathArchivo;
    }

    public Publicacion(String nombrePublicacion, String fechaPublicacion, String nombreRevista) {
        this.nombrePublicacion = nombrePublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.nombreRevista = nombreRevista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }

    public void setNombrePublicacion(String nombrePublicacion) {
        this.nombrePublicacion = nombrePublicacion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getPathArchivo() {
        return pathArchivo;
    }

    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }
    
    public Date getFechaFinalDate(){
        return Date.valueOf(fechaPublicacion);
    }
}
