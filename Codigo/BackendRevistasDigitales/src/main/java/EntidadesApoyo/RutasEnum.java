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

    RUTA_TO_SAVE_PDF("/home/joel/Documentos/PDFS/"),
    RUTA_FROM_NO_PROFILE_PICTURE("/home/joel/Documentos/Image/NoProFilePicture.jpg");
    
    private String ruta;

    private RutasEnum(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
}
