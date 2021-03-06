/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CTurno;
import cersa.negocio.Funciones.FTurno;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
public class BTurno {

    private CTurno objeto;
    private CTurno seleccion;
    private ArrayList<CTurno> listado;

    /**
     * Creates a new instance of BTurno
     */
    public BTurno() {
        this.reinit();
    }

    private void reinit() {
        this.objeto = new CTurno();
        this.seleccion = new CTurno();
        this.listado = new ArrayList<>();
        this.Visualizacion();
    }

    private void Visualizacion() {
        try {
            this.listado = FTurno.obtenerTodas();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public CTurno getObjeto() {
        return objeto;
    }

    public void setObjeto(CTurno objeto) {
        this.objeto = objeto;
    }

    public CTurno getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CTurno seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CTurno> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CTurno> listado) {
        this.listado = listado;
    }

}
