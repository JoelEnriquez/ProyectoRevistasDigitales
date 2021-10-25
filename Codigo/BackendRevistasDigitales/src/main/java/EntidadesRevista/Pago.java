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
public class Pago {
    
    private Double montoPagar;
    private int porcentajeGanancia;
    private String fechaPago;
    private int idSuscripcion;
    private Date datePago;

    public Pago(Double montoPagar, int porcentajeGanancia, String fechaPago, int idSuscripcion) {
        this.montoPagar = montoPagar;
        this.porcentajeGanancia = porcentajeGanancia;
        this.fechaPago = fechaPago;
        this.idSuscripcion = idSuscripcion;
    }

    /**
     * Pago luego de realizar una suscripcion
     * @param montoPagar
     * @param porcentajeGanancia
     * @param idSuscripcion
     * @param datePago 
     */
    public Pago(Double montoPagar, int porcentajeGanancia, int idSuscripcion, Date datePago) {
        this.montoPagar = montoPagar;
        this.porcentajeGanancia = porcentajeGanancia;
        this.idSuscripcion = idSuscripcion;
        this.datePago = datePago;
    }

    
    
    public Double getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Double montoPagar) {
        this.montoPagar = montoPagar;
    }

    public int getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(int porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public Date getDatePago() {
        return datePago;
    }

    public void setDatePago(Date datePago) {
        this.datePago = datePago;
    }
    
    
    
}
