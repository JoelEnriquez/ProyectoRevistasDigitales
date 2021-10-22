/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesApoyo;

/**
 *
 * @author joel
 */
public class ValoresGlobales {
    private int porcentajeGanancia;
    private Double costoAnuncioTexto;
    private Double costoAnuncioImagen;
    private Double costoAnuncioVideo;

    public ValoresGlobales(int porcentajeGanancia, Double costoAnuncioTexto, Double costoAnuncioImagen, Double costoAnuncioVideo) {
        this.porcentajeGanancia = porcentajeGanancia;
        this.costoAnuncioTexto = costoAnuncioTexto;
        this.costoAnuncioImagen = costoAnuncioImagen;
        this.costoAnuncioVideo = costoAnuncioVideo;
    }

    public int getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(int porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public Double getCostoAnuncioTexto() {
        return costoAnuncioTexto;
    }

    public void setCostoAnuncioTexto(Double costoAnuncioTexto) {
        this.costoAnuncioTexto = costoAnuncioTexto;
    }

    public Double getCostoAnuncioImagen() {
        return costoAnuncioImagen;
    }

    public void setCostoAnuncioImagen(Double costoAnuncioImagen) {
        this.costoAnuncioImagen = costoAnuncioImagen;
    }

    public Double getCostoAnuncioVideo() {
        return costoAnuncioVideo;
    }

    public void setCostoAnuncioVideo(Double costoAnuncioVideo) {
        this.costoAnuncioVideo = costoAnuncioVideo;
    }
    
    
    
    
}
