/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import cersa.negocio.Funciones.Database;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/**
 *
 * @author ELIZABETH-PC
 */
public class JasperReportUtil {
    
    public static JasperPrint getFromReportPrint(Map map, String pathJasper) throws Exception{        
        Connection connection = Database.getConnection();
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) context.getExternalContext().getContext();
        String caminorelativo = servletcontext.getRealPath(pathJasper);
        JasperPrint impresion = JasperFillManager.fillReport(caminorelativo, map,connection);  
        return impresion;
    }

    public static ByteArrayOutputStream getOutputStreamFromReport(JasperPrint print) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();         
        JasperExportManager.exportReportToPdfStream(print, os);
        os.flush();
        os.close();
        return os;
    }

    public static StreamedContent getStreamContentFromOutputStream(ByteArrayOutputStream os, String contentType, String nameFile) throws Exception {
        StreamedContent file = null;
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        file = new DefaultStreamedContent(is, contentType, nameFile);
        return file;
    }
}
