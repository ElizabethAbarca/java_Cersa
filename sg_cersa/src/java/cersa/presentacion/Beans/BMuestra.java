/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CMuestra;
import cersa.negocio.Funciones.FMuestra;
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
public class BMuestra {

    private CMuestra objeto;
    private CMuestra seleccion;
    private ArrayList<CMuestra> listado;
    /**
     * Creates a new instance of BeanTipo
     */
    public BMuestra() {
        this.reinit();
    }
        
    private void reinit() {
        this.objeto = new CMuestra();
        this.seleccion = new CMuestra();
        this.listado = new ArrayList<>();
        this.Visualizacion();        
        //this.objDependenciaSel = this.lstDependencias.get(0);
    }
    private void Visualizacion() {
        try {
            this.listado = FMuestra.obtenerTodas();
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    public CMuestra getObjeto() {
        return objeto;
    }

    public void setObjeto(CMuestra objeto) {
        this.objeto = objeto;
    }

    public CMuestra getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CMuestra seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CMuestra> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CMuestra> listado) {
        this.listado = listado;
    }
    
}
