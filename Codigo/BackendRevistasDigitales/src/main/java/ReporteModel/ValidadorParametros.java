/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReporteModel;

import java.time.LocalDate;

/**
 *
 * @author joel
 */
public class ValidadorParametros {
    
    public boolean fechasInvervaloCorrecto(LocalDate fecha1, LocalDate fecha2) throws Exception{
        if (fecha2.isBefore(fecha1)) {   
            throw new Exception("La fecha 1 tiene que estar antes que la fecha 2");          
        } else {
            return true;
        }
    }
    
    public boolean ambasFechasIngresadas(String fecha1, String fecha2) throws Exception{
        if ((fecha1.equals("") && !fecha2.equals("")) || (!fecha1.equals("") && fecha2.equals(""))) {
            throw new Exception("Ingrese ambas fechas para el filtrado por intervalo");
        } else {
            return fechasInvervaloCorrecto(LocalDate.parse(fecha1), LocalDate.parse(fecha2));
        }
    }
    
    public boolean fechasIngresadas(String fecha1, String fecha2){
        return fecha1!=null && fecha2!=null;
    }
    
    public boolean unaFechaAlMenos(String fecha1, String fecha2){
        return !fecha1.equals("") || !fecha2.equals("");
    }
}
