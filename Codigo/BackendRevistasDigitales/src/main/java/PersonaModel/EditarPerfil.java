/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonaModel;

import Convertidores.ConvertidorCategoriaArray;
import Convertidores.ConvertidorEditor;
import Convertidores.ConvertidorUsuario;
import EntidadesRevista.Categoria;
import Personas.Editor;
import Personas.Usuario;
import RegisterModel.DBEscogerCategorias;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.http.Part;

/**
 *
 * @author joel
 */
public class EditarPerfil {

    private ConvertidorEditor ce;
    private ConvertidorUsuario cu;
    private ConvertidorCategoriaArray cca;
    private DBEditarPerfil dBEditarPerfil;
    private DBEscogerCategorias dbec;
    private String persona;
    private Part foto;
    private boolean cambioImagen;

    public EditarPerfil(String persona, Part foto, String cambioImagen) {
        this.ce = new ConvertidorEditor(Editor.class);
        this.cu = new ConvertidorUsuario(Usuario.class);
        this.cca = new ConvertidorCategoriaArray(Categoria[].class);
        dBEditarPerfil = new DBEditarPerfil();
        dbec = new DBEscogerCategorias();
        this.persona = persona;
        this.foto = foto;
        this.cambioImagen = Boolean.valueOf(cambioImagen);
    }

    public String actualizarInfoEditor() throws IOException, SQLException {
        Editor editor = ce.fromJson(persona);
        dBEditarPerfil.actualizarInfoEditor(editor);
        if (foto != null && cambioImagen) {
            dBEditarPerfil.cambiarFotoPerfil(foto.getInputStream(), editor.getUserName());
        }
        return ce.toJson(editor);
    }

    public String actualizarInfoUsuario(String categorias, String etiquetas) throws IOException, SQLException {
        Usuario usuario = cu.fromJson(persona);
        dBEditarPerfil.actualizarInfoUsuario(usuario);

        if (foto != null && cambioImagen) {
            dBEditarPerfil.cambiarFotoPerfil(foto.getInputStream(), usuario.getUserName());
        }

        //Eliminar categorias anteriores y Asociar listado de categorias actuales
        Categoria[] listCategorias = cca.fromJson(categorias);
        dbec.eliminarCategoriasUsuario(usuario.getUserName());
        for (Categoria categoria : listCategorias) {
            dbec.agregarCategoriasUsuario(usuario.getUserName(), categoria);
        }

        return cu.toJson(usuario);
    }
}
