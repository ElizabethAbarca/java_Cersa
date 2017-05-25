/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.charts;

import cersa.negocio.Funciones.Database;
import cersa.negocio.Funciones.JasperReportUtil;
import cersa.validacion.validacion;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ELIZABETH-PC
 */
@Named(value = "reportPaca")
@SessionScoped
public class ReportPacaControlador implements Serializable {

    private StreamedContent media;
    private ByteArrayOutputStream outputStream;
    private java.util.Date fechaInicio;
    private java.util.Date fechaFin;
    private int subtipo;
    private JasperPrint resultado;

    /**
     * Creates a new instance of ReportPacaControlador
     */
    public ReportPacaControlador() {
    }

    public String getPathFileJasper() {
        return ("reportes/reportCompactacion.jasper");
    }

    public String getNameFilePdf() {
        return "Reporte_Compactacion_PDF_" + validacion.actualDate() + ".pdf";
    }

    public String getNameFileXLS() {
        return "Reporte_Compactacion_XLS_" + validacion.actualDate() + ".xls";
    }

    public JasperPrint getResultado() {
        return resultado;
    }

    public void setResultado(JasperPrint resultado) {
        this.resultado = resultado;
    }

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(int subtipo) {
        this.subtipo = subtipo;
    }

    public void generateReport() throws Exception {
        try {
            Map par = new HashMap();
            par.put("FIP", fechaInicio);
            par.put("FFP", fechaFin);
            par.put("SP", subtipo);
            resultado = JasperReportUtil.getFromReportPrint(par, getPathFileJasper());
            outputStream = JasperReportUtil.getOutputStreamFromReport(resultado);
            media = JasperReportUtil.getStreamContentFromOutputStream(outputStream, "application/pdf", getNameFilePdf());

        } catch (JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));

        }
    }

    public void downloadFilePDF() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=" + getNameFilePdf());

            OutputStream output = response.getOutputStream();
            output.write(outputStream.toByteArray());
            output.close();

            facesContext.responseComplete();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito. Archivo Descargado",
                            "Exito. Archivo Descargado"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));

        }
    }

    public void downloadFileXLS() throws JRException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + getNameFileXLS());
            ServletOutputStream outStream = response.getOutputStream();

            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, resultado);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporter.exportReport();

            outStream.flush();
            outStream.close();

            facesContext.responseComplete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Exito. Archivo Descargado",
                    "Exito. Archivo Descargado"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));

        }
    }
}
