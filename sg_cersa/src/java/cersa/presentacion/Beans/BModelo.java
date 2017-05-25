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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Insercion() {
        try {
            if (FModelo.insertar(objeto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Datos Ingresados",
                        "Exito. Datos Ingresados"));
                DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                this.objeto = new CModelo();
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
            if (FModelo.modificar(seleccion)) {
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
