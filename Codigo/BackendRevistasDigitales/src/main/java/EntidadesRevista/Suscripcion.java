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
    private int id;
    private String fechaSuscripcion;
    private boolean suscripcionActiva;
    private String nombreRevista;
    private String userName;
    private String tipoPago;
    private int cantidadTiempo;
    private String fechaCaducidad;
    private Date fechaSuscripcionDate;
    private Date fechaCaducidadDate;
    
    private Double montoGanancia;

    /**
     * Constructor for get data to DB
     * @param id
     * @param fechaSuscripcionDate
     * @param fechaCaducidadDate
     * @param suscripcionActiva
     * @param nombreRevista
     * @param userName 
     */
    public Suscripcion(int id, Date fechaSuscripcionDate, Date fechaCaducidadDate, boolean suscripcionActiva,String nombreRevista,String userName) {
        this.id = id;
        this.fechaSuscripcionDate = fechaSuscripcionDate;
        this.fechaCaducidadDate = fechaCaducidadDate;
        this.suscripcionActiva = suscripcionActiva;
        this.nombreRevista = nombreRevista;
        this.userName = userName;
    }

    
    
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

    /**
     * Constructor for jasper
     * @param id
     * @param suscripcionActiva
     * @param userName
     * @param fechaSuscripcionDate
     * @param fechaCaducidadDate 
     * @param montoGanancia 
     */
    public Suscripcion(int id, Date fechaSuscripcionDate, Date fechaCaducidadDate,boolean suscripcionActiva, String userName,  Double montoGanancia) {
        this.id = id;
        this.suscripcionActiva = suscripcionActiva;
        this.userName = userName;
        this.fechaSuscripcionDate = fechaSuscripcionDate;
        this.fechaCaducidadDate = fechaCaducidadDate;
        this.montoGanancia = montoGanancia;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMontoGanancia() {
        return montoGanancia;
    }

    public void setMontoGanancia(Double montoGanancia) {
        this.montoGanancia = montoGanancia;
    }
    
    
    
}
