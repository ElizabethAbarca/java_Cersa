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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
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

    public void Insercion() {
        try {
            if (FSubproducto.insertar(objeto)) {
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wglInsertar.hide()");
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Insertados", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);

            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Error");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Exito", new FacesMessage(e.getMessage()));
        }
    }

    public void Actualizacion() {
        try {
            if (FSubproducto.update(seleccion)) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Actulizados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEditar').hide()");
                this.reinit();
            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Datos No Actualizados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }

            objeto = null;
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Exito", new FacesMessage(e.getMessage()));
        }
    }

    public void Eliminacion() {
        try {
            if (FSubproducto.eliminar(seleccion.getSubproducto_id())) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Eliminados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEliminar').hide()");
                this.reinit();

            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Datos No Eliminados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);

            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Exito", new FacesMessage(e.getMessage()));
        }

    }
    
}
