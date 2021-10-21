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
public enum Rutas {

    RUTA_TO_SAVE_PDF("/home/joel/Documentos/PDFS/");
    private String ruta;

    private Rutas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
}
