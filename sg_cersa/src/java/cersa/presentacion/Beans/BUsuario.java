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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Insercion() {
        try {
            objeto.setUsuario_rol(FRol.obtener_Id(rol));            
            if (validacion.validacionCedula(objeto.getUsuario_cedula())) {
                if (FUsuario.insertar(objeto)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito. Datos Ingresados",
                            "Exito. Datos Ingresados"));
                    DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                    this.objeto = new CUsuario();
                    this.reinit();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error. Datos no Ingresados",
                            "Error. Datos no Ingresados"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. CI de Identidad no correcta",
                        "Error. CI de Identidad no correcta"));
            }
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Actualizacion() {
        try {
            seleccion.setUsuario_rol(FRol.obtener_Id(rol));
            if (FUsuario.update(seleccion)) {
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

    public void EditarDatos(CUsuario usuario) {
        try {
            if (FUsuario.update(usuario)) {
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
