/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FUsuario;
import cersa.negocio.Clases.CUsuario;
import cersa.negocio.Funciones.FRol;
import cersa.validacion.validacion;
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
public class BUsuario {

    private CUsuario objeto;
    private CUsuario seleccion;
    private ArrayList<CUsuario> listado;

    private int rol;

    /**
     * Creates a new instance of BUsuario
     */
    public BUsuario() {
        this.reinit();
    }

    private void reinit() {
        this.objeto = new CUsuario();
        this.seleccion = new CUsuario();
        this.listado = new ArrayList<>();
        this.Visualizacion();
    }

    private void Visualizacion() {
        try {
            this.listado = FUsuario.obtenerTodas();
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

     public void Insercion() {
        try {
            objeto.setUsuario_rol(FRol.obtener_Id(rol));
            if (validacion.validacionCedula(objeto.getUsuario_cedula())) {
                if (FUsuario.insertar(objeto)) {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Ingresados");
                    FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                    DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                    objeto = null;
                    this.reinit();
                } else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No Ingresados");
                    FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                }
            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cédula Incorrecta", "No Ingresados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Error", new FacesMessage(e.getMessage()));
        }
    }

    public void Actualizacion() {
        try {
            seleccion.setUsuario_rol(FRol.obtener_Id(rol));
            if (FUsuario.update(seleccion)) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Actulizados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEditar').hide()");
                this.reinit();
            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No Actulizados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Error", new FacesMessage(e.getMessage()));
        }
    }
    
    public void EditarDatos(CUsuario usuario) {
        try {
            if (FUsuario.update(usuario)) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Actulizados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEditar').hide()");
                this.reinit();
            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No Actulizados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Error", new FacesMessage(e.getMessage()));
        }
    }

    public CUsuario getObjeto() {
        return objeto;
    }

    public void setObjeto(CUsuario objeto) {
        this.objeto = objeto;
    }

    public CUsuario getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CUsuario seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CUsuario> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CUsuario> listado) {
        this.listado = listado;
    }

    /**
     * @return the rol
     */
    public int getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

}
