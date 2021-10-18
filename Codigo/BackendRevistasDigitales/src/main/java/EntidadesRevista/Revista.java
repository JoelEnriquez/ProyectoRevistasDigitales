/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesRevista;

/**
 *
 * @author joel
 */
public class Revista {
    private String nombre;
    private String descripcion;
    private boolean suscribir;
    private boolean comentar;
    private boolean reaccionar;
    private boolean pago;
    private Double costoSuscripcion;
    private Double costoDia;
    private String nombreCategoria;
    private String userName;

    /**
     * Constructor without costo suscripcion
     * @param nombre
     * @param descripcion
     * @param suscribir
     * @param comentar
     * @param reaccionar
     * @param pago
     * @param nombreCategoria
     * @param userName 
     */
    public Revista(String nombre, String descripcion, boolean suscribir, boolean comentar, boolean reaccionar, boolean pago, String nombreCategoria, String userName) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.suscribir = suscribir;
        this.comentar = comentar;
        this.reaccionar = reaccionar;
        this.pago = pago;
        this.nombreCategoria = nombreCategoria;
        this.userName = userName;
    }

    /**
     * Constructor with costo suscripcion
     * @param nombre
     * @param descripcion
     * @param suscribir
     * @param comentar
     * @param reaccionar
     * @param pago
     * @param costoSuscripcion
     * @param nombreCategoria
     * @param userName 
     */
    public Revista(String nombre, String descripcion, boolean suscribir, boolean comentar, boolean reaccionar, boolean pago, Double costoSuscripcion, String nombreCategoria, String userName) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.suscribir = suscribir;
        this.comentar = comentar;
        this.reaccionar = reaccionar;
        this.pago = pago;
        this.costoSuscripcion = costoSuscripcion;
        this.nombreCategoria = nombreCategoria;
        this.userName = userName;
    }
    
    public Revista(String nombre, String descripcion, boolean suscribir, boolean comentar, boolean reaccionar, boolean pago, Double costoSuscripcion, Double costoDia, String nombreCategoria, String userName) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.suscribir = suscribir;
        this.comentar = comentar;
        this.reaccionar = reaccionar;
        this.pago = pago;
        this.costoSuscripcion = costoSuscripcion;
        this.costoDia = costoDia;
        this.nombreCategoria = nombreCategoria;
        this.userName = userName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSuscribir() {
        return suscribir;
    }

    public void setSuscribir(boolean suscribir) {
        this.suscribir = suscribir;
    }

    public boolean isComentar() {
        return comentar;
    }

    public void setComentar(boolean comentar) {
        this.comentar = comentar;
    }

    public boolean isReaccionar() {
        return reaccionar;
    }

    public void setReaccionar(boolean reaccionar) {
        this.reaccionar = reaccionar;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Double getCostoSuscripcion() {
        return costoSuscripcion;
    }

    public void setCostoSuscripcion(Double costoSuscripcion) {
        this.costoSuscripcion = costoSuscripcion;
    }

    public Double getCostoDia() {
        return costoDia;
    }

    public void setCostoDia(Double costoDia) {
        this.costoDia = costoDia;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
   
    
    
}
