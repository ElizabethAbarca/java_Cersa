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
import org.primefaces.context.DefaultRequestContext;

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
     * Creates a new instance of BMuestra
     */
    public BTipo() {
        this.reinit();
    }

    private void reinit() {
        this.objeto = new CTipo();
        this.seleccion = new CTipo();
        this.listado = new ArrayList<>();
        this.Visualizacion();
    }

    private void Visualizacion() {
        try {
            this.listado = FTipo.obtenerTodas();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Insercion() {
        try {
            if (FTipo.insertar(objeto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Datos Ingresados",
                        "Exito. Datos Ingresados"));
                DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                this.objeto = new CTipo();
                this.reinit();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Datos no Ingresados",
                        "Errot. Datos no Ingresados"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Actualizacion() {
        try {
            if (FTipo.modificar(seleccion)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Datos Actualizados",
                        "Exito. Datos Actualizados"));
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEditar').hide()");
                this.reinit();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Datos no Actualizados",
                        "Error. Datos no Actualizados"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
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
