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

    RUTA_TO_SAVE_PDF("Files/"),
    RUTA_FROM_NO_PROFILE_PICTURE("Image/NoProFilePicture.jpg"),
    RUTA_TO_EDITOR_REPORTS("ProyectoRevistas/Editor/"),
    RUTA_TO_EDITOR_SUB_REPORTS("ProyectoRevistas/Editor/SubReporte/"),
    RUTA_TO_ADMIN_REPORTS("ProyectoRevistas/Administrador/"),
    RUTA_TO_ADMIN_SUB_REPORTS("ProyectoRevistas/Administrador/SubReporte/");
    
    private String ruta;

    private RutasEnum(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
}
