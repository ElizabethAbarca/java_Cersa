/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CTipo;
import cersa.negocio.Funciones.FTipo;
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
public class BTipo {

    private CTipo objeto;
    private CTipo seleccion;
    private ArrayList<CTipo> listado;
    /**
     * Creates a new instance of BeanTipo
     */
    public BTipo() {
        this.reinit();
    }
        
    private void reinit() {
        this.objeto = new CTipo();
        this.seleccion = new CTipo();
        this.listado = new ArrayList<>();
        this.Visualizacion();        
        //this.objDependenciaSel = this.lstDependencias.get(0);
    }
    private void Visualizacion() {
        try {
            this.listado = FTipo.obtenerTodas();
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    public CTipo getObjeto() {
        return objeto;
    }

    public void setObjeto(CTipo objeto) {
        this.objeto = objeto;
    }

    public CTipo getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CTipo seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CTipo> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CTipo> listado) {
        this.listado = listado;
    }
    
}
