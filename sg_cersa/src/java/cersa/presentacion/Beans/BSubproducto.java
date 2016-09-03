/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FSubproducto;
import cersa.negocio.Clases.CSubproducto;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class BSubproducto {
    
    private CSubproducto objeto;
    private CSubproducto seleccion;
    private ArrayList<CSubproducto> listado;
    /**
     * Creates a new instance of BeanSubproducto
     */
    public BSubproducto() {
        this.reinit();
    }
    
    private void reinit() {
        this.objeto = new CSubproducto();
        this.seleccion = new CSubproducto();
        this.listado = new ArrayList<>();
        this.Visualizacion();        
        //this.objDependenciaSel = this.lstDependencias.get(0);
    }
    private void Visualizacion() {
        try {
            this.listado = FSubproducto.obtenerTodas();
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    public CSubproducto getObjeto() {
        return objeto;
    }

    public void setObjeto(CSubproducto objeto) {
        this.objeto = objeto;
    }

    public CSubproducto getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CSubproducto seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CSubproducto> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CSubproducto> listado) {
        this.listado = listado;
    }
    
}
