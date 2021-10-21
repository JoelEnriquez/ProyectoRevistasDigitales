/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErrorAPI;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
public class ErrorResponse {
    
    public static void mostrarError(HttpServletResponse response,String messageError) throws IOException{
        response.getWriter().append(new Convertidores.ErrorBackendModelConverter(ErrorBackendModel.class).toJson(new ErrorBackendModel(messageError)));
        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }
}
