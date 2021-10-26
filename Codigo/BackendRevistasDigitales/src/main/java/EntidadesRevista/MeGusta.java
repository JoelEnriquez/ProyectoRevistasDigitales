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
public class MeGusta {
    private String nombreRevista;
    private String userName;

    public MeGusta(String nombreRevista, String userName) {
        this.nombreRevista = nombreRevista;
        this.userName = userName;
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
    
    
}
