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
public class Comentario {
    private int id;
    public String contenido;
    public String fechaComentario;
    public String nombreRevista;
    public String userName;
    
    private Date fechaComentarioDate;

    public Comentario(int id, String contenido, String fechaComentario, String nombreRevista, String userName) {
        this.id = id;
        this.contenido = contenido;
        this.fechaComentario = fechaComentario;
        this.nombreRevista = nombreRevista;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getFechaComentarioDate() {
        return fechaComentarioDate;
    }

    public void setFechaComentarioDate(Date fechaComentarioDate) {
        this.fechaComentarioDate = fechaComentarioDate;
    }
    
    
}
