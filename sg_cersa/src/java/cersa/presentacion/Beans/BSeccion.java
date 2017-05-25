/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CSeccion;
import cersa.negocio.Funciones.FSeccion;
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
public class BSeccion {

    private CSeccion objeto;
    private CSeccion seleccion;
    private ArrayList<CSeccion> listado;

    /**
     * Creates a new instance of BSeccion
     */
    public BSeccion() {
        this.reinit();
    }

    @PostConstruct
    private void reinit() {
        this.objeto = new CSeccion();
        this.seleccion = new CSeccion();
        this.listado = new ArrayList<>();
        this.Visualizacion();
    }

    private void Visualizacion() {
        try {
            this.listado = FSeccion.obtenerTodas();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Insercion() {
        try {
            if (FSeccion.insertar(objeto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Datos Ingresados",
                        "Exito. Datos Ingresados"));
                DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                this.objeto = new CSeccion();
                this.reinit();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Datos no Ingresados",
                        "Error. Datos no Ingresados"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Actualizacion() {
        try {
            if (FSeccion.modificar(seleccion)) {
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

    public CSeccion getObjeto() {
        return objeto;
    }

    public void setObjeto(CSeccion objeto) {
        this.objeto = objeto;
    }

    public CSeccion getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CSeccion seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CSeccion> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CSeccion> listado) {
        this.listado = listado;
    }

}
