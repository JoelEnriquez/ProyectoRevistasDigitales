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
public enum RutasEnum {

    RUTA_TO_SAVE_PDF("PDFS/"),
    RUTA_FROM_NO_PROFILE_PICTURE("Image/NoProFilePicture.jpg");
    
    private String ruta;

    private RutasEnum(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
}
