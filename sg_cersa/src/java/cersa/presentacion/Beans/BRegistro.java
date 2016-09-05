/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CRegistro;
import cersa.negocio.Funciones.FRegistro;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
public class BRegistro {

    private ArrayList<CRegistro> listado;
    /**
     * Creates a new instance of BeanRol
     */
    
    public BRegistro() {
        this.reinit();
    }
    
    private void reinit() {
        this.listado = new ArrayList<>();
        this.Visualizacion();        
        //this.objDependenciaSel = this.lstDependencias.get(0);
    }
    private void Visualizacion() {
        try {
            this.listado = FRegistro.obtenerTodas();
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    public ArrayList<CRegistro> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CRegistro> listado) {
        this.listado = listado;
    }

}
