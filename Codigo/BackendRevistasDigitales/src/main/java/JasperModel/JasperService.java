/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JasperModel;

import java.io.InputStream;
import java.io.OutputStream;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author joel
 */
public class JasperService {
    
    

    public void imprimirReporteBeans(OutputStream targetStream, String path, JRDataSource parametros) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream(path);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, parametros);
        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }
}
