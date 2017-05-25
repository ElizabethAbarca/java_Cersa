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
import org.primefaces.context.DefaultRequestContext;

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
     * Creates a new instance of BMuestra
     */
    public BMuestra() {
        this.reinit();
    }

    private void reinit() {
        this.objeto = new CMuestra();
        this.seleccion = new CMuestra();
        this.listado = new ArrayList<>();
        this.Visualizacion();
    }

    private void Visualizacion() {
        try {
            this.listado = FMuestra.obtenerTodas();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Insercion() {
        try {
            if (FMuestra.insertar(objeto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Datos Ingresados",
                        "Exito. Datos Ingresados"));
                DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                this.objeto = new CMuestra();
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
            if (FMuestra.modificar(seleccion)) {
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

    public void Eliminacion() {
        try {
            if (FMuestra.eliminar(seleccion.getMuestra_id())) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Eliminados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEliminar').hide()");
                this.reinit();
            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No Eliminados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Exito", new FacesMessage(e.getMessage()));
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
