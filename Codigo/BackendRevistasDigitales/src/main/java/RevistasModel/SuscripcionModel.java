/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import Convertidores.ConvertidorSuscripcion;
import EntidadesRevista.Pago;
import EntidadesRevista.Suscripcion;
import ValoresGlobalesModel.DBValoresGlobales;
import com.google.gson.Gson;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author joel
 */
public class SuscripcionModel {
    
    private ConvertidorSuscripcion cs;
    private DBSuscripcion dBSuscripcion;

    public SuscripcionModel() {
        this.cs = new ConvertidorSuscripcion(Suscripcion.class);
        dBSuscripcion = new DBSuscripcion();
    } 
    
    public String verificarSuscripcionActiva(String nombreRevista, String userName){
        return new Gson().toJson(new DBSuscripcion().suscripcionActiva(nombreRevista, userName));
    }
    
    public void registrarSuscripcion(String infoSuscripcion) throws SQLException{
        Suscripcion suscripcion = cs.fromJson(infoSuscripcion);
        //Convert to localdate la fecha de suscripcion
        LocalDate suscripcionLD = LocalDate.parse(suscripcion.getFechaSuscripcion());
        suscripcion.setFechaSuscripcionDate(Date.valueOf(suscripcionLD)); //Set fecha en formato date
        //Revista de Pago
        if (suscripcion.getTipoPago()!=null) {
            Double monto = new DBRevista().getCostoSuscripcion(suscripcion.getNombreRevista());
            int cantidadTiempo = suscripcion.getCantidadTiempo();
            String tipoPago = suscripcion.getTipoPago();
            
            //Agregamos la cantidad de monto por el tiempo
            monto*=cantidadTiempo;
            
            //Estado inicial de fecha de caducidad
            LocalDate caducidadLD  = suscripcionLD;
            if (tipoPago.equals("mensual")) {         
                caducidadLD = caducidadLD.plusMonths(cantidadTiempo);
            } else {
                caducidadLD = caducidadLD.plusYears(cantidadTiempo);
                monto*=12;
            }
            //Convert LocalDate en Date y Guardar en DB
            suscripcion.setFechaCaducidadDate(Date.valueOf(caducidadLD));
            int idSuscripcion = dBSuscripcion.registrarNuevaSuscripcionPago(suscripcion);
            
            //Guardar Pago en DB
            registrarNuevoPago(suscripcionLD, idSuscripcion, monto);
            
        } else {
        dBSuscripcion.registrarNuevaSuscripcion(suscripcion);
        }
    }
    
    public void registrarNuevoPago(LocalDate fechaPago, int idSuscripcion, Double monto) throws SQLException{
        Pago pago = new Pago(monto, new DBValoresGlobales().getPorcentajeGanancia(), idSuscripcion,Date.valueOf(fechaPago));
        dBSuscripcion.agregarPagoRevista(pago);
    }
}
