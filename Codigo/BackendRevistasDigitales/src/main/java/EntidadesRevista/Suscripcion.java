/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesRevista;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author joel
 */
public class Suscripcion {
    private String fechaSuscripcion;
    private boolean suscripcionActiva;
    private String nombreRevista;
    private String userName;
    private String tipoPago;
    private int cantidadTiempo;
    private String fechaCaducidad;
    private Date fechaSuscripcionDate;
    private Date fechaCaducidadDate;

    public Suscripcion(String fechaSuscripcion, boolean suscripcionActiva, String nombreRevista, String userName) {
        this.fechaSuscripcion = fechaSuscripcion;
        this.suscripcionActiva = suscripcionActiva;
        this.nombreRevista = nombreRevista;
        this.userName = userName;
    }

    public Suscripcion(String fechaSuscripcion, boolean suscripcionActiva, String nombreRevista, String userName, String tipoPago, int cantidadTiempo, String fechaCaducidad) {
        this.fechaSuscripcion = fechaSuscripcion;
        this.suscripcionActiva = suscripcionActiva;
        this.nombreRevista = nombreRevista;
        this.userName = userName;
        this.tipoPago = tipoPago;
        this.cantidadTiempo = cantidadTiempo;
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(String fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public boolean isSuscripcionActiva() {
        return suscripcionActiva;
    }

    public void setSuscripcionActiva(boolean suscripcionActiva) {
        this.suscripcionActiva = suscripcionActiva;
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

    public int getCantidadTiempo() {
        return cantidadTiempo;
    }

    public void setCantidadTiempo(int cantidadTiempo) {
        this.cantidadTiempo = cantidadTiempo;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Date getFechaSuscripcionDate() {
        return fechaSuscripcionDate;
    }

    public void setFechaSuscripcionDate(Date fechaSuscripcionDate) {
        this.fechaSuscripcionDate = fechaSuscripcionDate;
    }

    public Date getFechaCaducidadDate() {
        return fechaCaducidadDate;
    }

    public void setFechaCaducidadDate(Date fechaCaducidadDate) {
        this.fechaCaducidadDate = fechaCaducidadDate;
    }
    
    
    
}
