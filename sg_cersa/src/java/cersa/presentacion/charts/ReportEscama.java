/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.charts;

import cersa.negocio.Clases.CUsuario;
import cersa.negocio.Clases.DEscama;
import cersa.negocio.Funciones.Database;
import cersa.negocio.Funciones.FDatosEscama;
import cersa.negocio.Funciones.FUsuario;
import cersa.negocio.Funciones.JasperReportUtil;
import cersa.validacion.validacion;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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

/**
 *
 * @author ELIZABETH-PC
 */
@ManagedBean
@ViewScoped
public class ReportEscama {

    /**
     * Creates a new instance of reportEscama
     */
    private int tipo;
    private int subtipo;

    public ReportEscama() {
    }

    public String getPathFileJasper() {
        return ("reportes/reportEscama.jasper");
    }

    public String getNameFileXls() {
        return ("reporte_Escama" + validacion.actualDate() + ".xls");
    }

    public String getNameFilePdf() {
        return ("reporte_Escama" + validacion.actualDate() + ".pdf");
    }

    public double promedio_Escama(int tip, int sub) {
        double promedio = 0;
        try {
            ArrayList<CUsuario> listaUsuario = FUsuario.obtener_Rol();
            ArrayList<DEscama> listaEscama = null;
            for (CUsuario usuario : listaUsuario) {
                double suma = 0;
                listaEscama = FDatosEscama.obtenerSuma_Escama_Persona(usuario.getUsuario_id(), tip, sub);
                for (DEscama escama : listaEscama) {
                    suma = suma + escama.getEpeso();
                }
                if (listaEscama.size() != 0) {
                    promedio = promedio + (suma / listaEscama.size());
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
        return promedio;
    }

    public void DescargarExcel() throws Exception {
        try {
            if (this.tipo != 0 && this.subtipo != 0) {
                double pmd = promedio_Escama(this.tipo, this.subtipo);
                Map par = new HashMap();
                par.put("escTipo", this.tipo);
                par.put("escSubtipo", this.subtipo);
                par.put("promedio", pmd);
                JasperPrint resultado = JasperReportUtil.getFromReportPrint(par, getPathFileJasper());

                FacesContext facesContext = FacesContext.getCurrentInstance();

                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=" + getNameFileXls());
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
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Exito. Archivo Descargado",
                                "Exito. Archivo Descargado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Seleccione campos",
                        "Error. Seleccione campos"));
            }

        } catch (JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void DescargarPdf() throws Exception {
        try {
            if (this.tipo != 0 && this.subtipo != 0) {
                double pmd = promedio_Escama(this.tipo, this.subtipo);
                Map par = new HashMap();
                par.put("escTipo", this.tipo);
                par.put("escSubtipo", this.subtipo);
                par.put("promedio", pmd);
                JasperPrint resultado = JasperReportUtil.getFromReportPrint(par, getPathFileJasper());
                ByteArrayOutputStream outputStream = JasperReportUtil.getOutputStreamFromReport(resultado);

                FacesContext facesContext = FacesContext.getCurrentInstance();

                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.reset();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "attachment; filename=" + getNameFilePdf());

                OutputStream output = response.getOutputStream();
                output.write(outputStream.toByteArray());
                output.close();

                facesContext.responseComplete();
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Exito. Archivo Descargado",
                                "Exito. Archivo Descargado"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Seleccione campos",
                        "Error. Seleccione campos"));
            }

        } catch (JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(int subtipo) {
        this.subtipo = subtipo;
    }

}
