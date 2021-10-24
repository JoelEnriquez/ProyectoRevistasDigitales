/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesApoyo;

import EntidadesApoyo.RutasEnum;

/**
 *
 * @author joel
 */
public enum FiltroEnum {
    CATEGORIA("categoria"),
    ETIQUETA("etiqueta");
    
    private String filtro;

    private FiltroEnum(String filtro) {
        this.filtro = filtro;
    }

    public String getFiltro() {
        return filtro;
    }
}
