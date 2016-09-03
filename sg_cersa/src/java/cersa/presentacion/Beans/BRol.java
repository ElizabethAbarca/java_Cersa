/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FRol;
import cersa.negocio.Clases.CRol;
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
public class BRol {
    
    private CRol objeto;
    private CRol seleccion;
    private ArrayList<CRol> listado;
    /**
     * Creates a new instance of BeanRol
     */
    public BRol() {
        this.reinit();
    }
    
    private void reinit() {
        this.objeto = new CRol();
        this.seleccion = new CRol();
        this.listado = new ArrayList<>();
        this.Visualizacion();        
        //this.objDependenciaSel = this.lstDependencias.get(0);
    }
    private void Visualizacion() {
        try {
            this.listado = FRol.obtenerTodas();
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    public CRol getObjeto() {
        return objeto;
    }

    public void setObjeto(CRol objeto) {
        this.objeto = objeto;
    }

    public CRol getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CRol seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CRol> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CRol> listado) {
        this.listado = listado;
    }
    
}
