/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CListaProducto;
import cersa.negocio.Funciones.Database;
import cersa.negocio.Funciones.FListaProducto;
import cersa.negocio.Funciones.JasperReportUtil;
import cersa.validacion.validacion;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
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
 * @author RitaElizabeth
 */
@ManagedBean
@ViewScoped
public class BListaProducto {

    private CListaProducto objeto;
    private CListaProducto seleccion;
    private ArrayList<CListaProducto> listado;

    /**
     * Creates a new instance of BListaProducto
     */
    public BListaProducto() {
        this.reinit();
    }

    @PostConstruct
    private void reinit() {
        this.objeto = new CListaProducto();
        this.seleccion = new CListaProducto();
        this.listado = new ArrayList<>();
        this.Visualizacion();

    }

    private void Visualizacion() {
        try {
            this.listado = FListaProducto.lista_Producto_Fecha();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error" + e.getMessage(),
                            "Error" + e.getMessage()));
        }
    }

    public String getPathFileJasper() {
        return ("reportes/reportProducto.jasper");
    }

    public String getNameFileXls() {
        return ("reporte_Lista_XLS_" + validacion.actualDate() + ".xls");
    }
    
    public String getNameFilePdf() {
        return ("reporte_Lista_XLS_" + validacion.actualDate() + ".pdf");
    }

    public void DescargarExcel() throws Exception {
        try {
            if (seleccion != null) {
                Connection connection = Database.getConnection();
                FacesContext context = FacesContext.getCurrentInstance();

                Map par = new HashMap();
                par.put("fechaPaquete", seleccion.getFecha());
                par.put("sumaPaq", seleccion.getTotal_dia());

                ServletContext servletcontext = (ServletContext) context.getExternalContext().getContext();
                String caminorelativo = servletcontext.getRealPath(getPathFileJasper());
                JasperPrint impresion = JasperFillManager.fillReport(caminorelativo, par, connection);

                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.setContentType("application/xls");
                response.addHeader("Content-disposition", "attachment; filename=" + getNameFileXls());
                ServletOutputStream outStream = response.getOutputStream();

                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, impresion);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporter.exportReport();

                outStream.flush();
                outStream.close();
                FacesContext.getCurrentInstance().responseComplete();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito. Archivo Descargado", "Exito. Archivo Descargado");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error. Seleccione una fila",
                                "Error. Seleccione una fila"));
            }

        } catch (JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error" + e.getMessage(),
                            "Error" + e.getMessage()));
        }
    }
    
        public void DescargarPdf() throws Exception {
        try {
            if (seleccion != null) {
                Map par = new HashMap();
                par.put("fechaPaquete", seleccion.getFecha());
                par.put("sumaPaq", seleccion.getTotal_dia());
                
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
                } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error. Seleccione una fila",
                                "Error. Seleccione una fila"));
            }

        } catch (JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public CListaProducto getObjeto() {
        return objeto;
    }

    public void setObjeto(CListaProducto objeto) {
        this.objeto = objeto;
    }

    public CListaProducto getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CListaProducto seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CListaProducto> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CListaProducto> listado) {
        this.listado = listado;
    }

}
