/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CModelo;
import cersa.negocio.Funciones.FModelo;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;

/**
 *
 * @author RitaElizabeth
 */
@ManagedBean
@ViewScoped
public class BModelo {
private CModelo objeto;
    private CModelo seleccion;
    private ArrayList<CModelo> listado;
    /**
     * Creates a new instance of BModelo
     */
    public BModelo() {
    this.reinit();
    }
    
    @PostConstruct
    private void reinit() {
        this.objeto = new CModelo();
        this.seleccion = new CModelo();
        this.listado = new ArrayList<>();
        this.Visualizacion();        
    }
    private void Visualizacion() {
        try {
            this.listado = FModelo.obtenerTodas();
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }
    
     public void Insercion()
    {
        try {                
            if(FModelo.insertar(objeto))
            {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Ingresados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                objeto = null;
                this.reinit();
            }
            else
            {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No Ingresados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }
                
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage( "Error",new FacesMessage(e.getMessage()));
        }
    }

    public void Actualizacion() {
        try {            
            if (FModelo.modificar(seleccion)) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Actulizados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEditar').hide()");
                this.reinit();
            }
            else
            {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No Actulizados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }
                
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage( "Error",new FacesMessage(e.getMessage()));
        }
    }

    public CModelo getObjeto() {
        return objeto;
    }

    public void setObjeto(CModelo objeto) {
        this.objeto = objeto;
    }

    public CModelo getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CModelo seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CModelo> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CModelo> listado) {
        this.listado = listado;
    }
    
}
