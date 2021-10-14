/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personas;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author joel
 */
public class Editor extends Persona{
    private String hobbies;
    private String descripcion;
    private InputStream foto;

    public Editor(String userName, String password, String nombre, PersonaEnum tipo,String hobbies, String descripcion, String foto) {
        super(userName, password, nombre, tipo);
        this.hobbies = hobbies;
        this.descripcion = descripcion;
        this.foto = new ByteArrayInputStream(foto.getBytes());
    }

    public Editor(String hobbies, String descripcion, InputStream foto, String userName, String password, String nombre) {
        super(userName, password, nombre);
        this.hobbies = hobbies;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Editor(String hobbies, String descripcion, InputStream foto, String userName, String password) {
        super(userName, password);
        this.hobbies = hobbies;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
    
    
    
}