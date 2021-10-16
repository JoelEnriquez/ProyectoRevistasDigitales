/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterModel;

import Convertidores.ConvertidorUsuario;
import Personas.Usuario;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class VerificarUsuario {
    private ConvertidorUsuario cu;

    public VerificarUsuario() {
        cu = new ConvertidorUsuario(Usuario.class);
    }
    
    public Boolean verificarUsuario(String usuarioVerificar) throws SQLException{
        Usuario usuario = cu.fromJson(usuarioVerificar);
        new DBRegister().insertarUsuario(usuario); //Verificar si se puede insertar dicho usuario
        new DBEliminarRegistro().eliminarUsuario(usuario.getUserName()); //Si se elimina se puede ingresar el usuario
        return false;
    }
}
