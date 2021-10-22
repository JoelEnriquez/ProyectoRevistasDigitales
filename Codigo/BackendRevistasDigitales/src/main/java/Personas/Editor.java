/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personas;

import java.io.InputStream;

/**
 *
 * @author joel
 */
public class Editor extends Persona{
    private String hobbies;
    private String descripcion;
    private InputStream fotoPerfil;

    public Editor(String userName, String password, String nombre, PersonaEnum tipo,String hobbies, String descripcion) {
        super(userName, password, nombre, tipo);
        this.hobbies = hobbies;
        this.descripcion = descripcion;
    }

    
    
    public Editor(String userName, String password, String nombre, PersonaEnum tipo) {
        super(userName, password, nombre, tipo);
    }

    
    /**
     * Constructor with info json
     * @param hobbies
     * @param descripcion
     * @param userName
     * @param password
     * @param nombre
     * @param tipo 
     */
    public Editor(String hobbies, String descripcion, String userName, String password, String nombre, PersonaEnum tipo) {
        super(userName, password, nombre, tipo);
        this.hobbies = hobbies;
        this.descripcion = descripcion;
    }
    
    /**
     * Constructor with all info
     * @param hobbies
     * @param descripcion
     * @param pathFoto
     * @param foto
     * @param userName
     * @param password
     * @param nombre
     * @param tipo 
     */
    public Editor(String hobbies, String descripcion, InputStream pathFoto, String userName, String password, String nombre, PersonaEnum tipo) {
        super(userName, password, nombre, tipo);
        this.hobbies = hobbies;
        this.descripcion = descripcion;
        this.fotoPerfil = pathFoto;
    }

    public Editor(String hobbies, String descripcion, InputStream pathFoto, String userName, String password) {
        super(userName, password);
        this.hobbies = hobbies;
        this.descripcion = descripcion;
        this.fotoPerfil = pathFoto;
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

    public InputStream getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(InputStream pathFoto) {
        this.fotoPerfil = pathFoto;
    }

    
    
    
    
}