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
public class MeGusta {
    private String nombreRevista;
    private String userName;
    private String fechaReaccion;
    private Date fechaReaccionDate;

    public MeGusta(String nombreRevista, String userName, String fechaReaccion) {
        this.nombreRevista = nombreRevista;
        this.userName = userName;
        this.fechaReaccion = fechaReaccion;
    }

    public MeGusta(String nombreRevista, String userName, Date fechaReaccionDate) {
        this.nombreRevista = nombreRevista;
        this.userName = userName;
        this.fechaReaccionDate = fechaReaccionDate;
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

    public String getFechaReaccion() {
        return fechaReaccion;
    }

    public void setFechaReaccion(String fechaReaccion) {
        this.fechaReaccion = fechaReaccion;
    }

    public Date getFechaReaccionDate() {
        return fechaReaccionDate;
    }

    public void setFechaReaccionDate(Date fechaReaccionDate) {
        this.fechaReaccionDate = fechaReaccionDate;
    }
    
    
}
