/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterModel;

import Convertidores.ConvertidorCategoriaArray;
import Convertidores.ConvertidorUsuario;
import EntidadesApoyo.Encriptar;
import EntidadesRevista.Categoria;
import Personas.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.Part;

/**
 *
 * @author joel
 */
public class RegistrarUsuario {
    private ConvertidorUsuario cu;
    private ConvertidorCategoriaArray cca;

    public RegistrarUsuario() {
        cu = new ConvertidorUsuario(Usuario.class);
        cca = new ConvertidorCategoriaArray(Categoria[].class);
    }
    
    public Usuario registrarUsuario(String contenido, Part imagen) throws IOException, SQLException, Exception{
        Usuario usuario = cu.fromJson(contenido);
        if (imagen!=null) {
            usuario.setFoto(imagen.getInputStream());
        }
        usuario.setPassword(new Encriptar().encriptar(usuario.getPassword()));
        new DBRegister().insertarUsuario(usuario);
        return usuario;
    }
    
    public String editorRegistrado(Usuario nuevoUsuario){
        nuevoUsuario.setFoto(null); //Borrar la foto del objeto usuario
        return cu.toJson(nuevoUsuario);
    }
    
    public void registrarListadoCategorias(String listado, String userName){
        Categoria[] listadoCategorias = cca.fromJson(listado);
        for (Categoria categoria : listadoCategorias) {
            new DBEscogerCategorias().agregarCategoriasUsuario(userName, categoria);
        }
    }
}
