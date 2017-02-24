/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.charts;

import cersa.negocio.Clases.CUsuario;
import cersa.negocio.Clases.DEscama;
import cersa.negocio.Funciones.FDatosEscama;
import cersa.negocio.Funciones.FUsuario;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class GEscama implements Serializable {

    private LineChartModel lineModel2;

    @PostConstruct
    public void init() {
        createLineModels();
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    private void createLineModels() {
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("(ZOOM)");
        lineModel2.setZoom(true);
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxis(AxisType.Y).setLabel("Peso(Kg)");
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50); 
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        axis.setMax(anio+"-"+mes+"-"+dia);
        axis.setTickFormat("%b %#d, %y");
        lineModel2.getAxes().put(AxisType.X, axis);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
        try {
            ArrayList<CUsuario> listaUsuario = FUsuario.obtener_Rol();
            ArrayList<DEscama> listaEscama = null;
            for (CUsuario usuario : listaUsuario) {
                ChartSeries series = new ChartSeries();
                series.setLabel(usuario.getUsuario_nombre() + " " + usuario.getUsuario_apellido());
                listaEscama = FDatosEscama.obtenerSuma_Escama_Persona(usuario.getUsuario_id());
                for (DEscama escama : listaEscama) {
                    series.set(escama.getEfecga().getTime(), escama.getEpeso());
                }
                model.addSeries(series);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Exito", new FacesMessage(e.getMessage()));
        }
        return model;
    }

    
}
