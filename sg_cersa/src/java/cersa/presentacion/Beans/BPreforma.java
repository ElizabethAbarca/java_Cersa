/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FPreforma;
import cersa.negocio.Clases.CPreforma;
import cersa.negocio.Funciones.FSubproducto;
import cersa.negocio.Funciones.FUsuario;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
public class BPreforma {

    private CPreforma objeto;
    private CPreforma seleccion;
    private ArrayList<CPreforma> listado;

    @ManagedProperty(value = "#{bSesionManager}")
    private BSesionManager session;

    /**
     * Creates a new instance of BEscamas
     */
    public BPreforma() {
        this.reinit();
    }

    private int sub;

    @PostConstruct
    private void reinit() {
        this.objeto = new CPreforma();
        this.seleccion = new CPreforma();
        this.listado = new ArrayList<>();
        this.Visualizacion();
    }

    private void Visualizacion() {
        try {
            this.listado = FPreforma.obtener_Preforma_Persona(session.getEmpleado().getUsuario_id());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Insercion() {
        try {
            objeto.setPreforma_subtipo(FSubproducto.obtener_Id(sub));
            objeto.setPreforma_usuario(FUsuario.obtener_Id(session.getEmpleado().getUsuario_id()));
            if (objeto.getPreforma_pesoingreso() > 0) {
                if (FPreforma.insertar(objeto)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito. Datos Ingresados",
                            "Exito. Datos Ingresados"));
                    DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                    this.objeto = new CPreforma();
                    this.reinit();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error. Datos no Ingresados",
                            "Error. Datos no Ingresados"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Peso, deber ser  mayor a cero",
                        "Peso, deber ser mayor a cero"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Actualizacion() {
        try {
            seleccion.setPreforma_subtipo(FSubproducto.obtener_Id(sub));
            seleccion.setPreforma_usuario(FUsuario.obtener_Id(session.getEmpleado().getUsuario_id()));
            if (seleccion.getPreforma_pesoingreso() > 0) {
                if (FPreforma.update(seleccion)) {
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
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Peso, deber ser  mayor a cero",
                        "Peso, deber ser mayor a cero"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Eliminacion() {
        try {
            if (FPreforma.eliminar(seleccion.getPreforma_id())) {
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

    public CPreforma getObjeto() {
        return objeto;
    }

    public void setObjeto(CPreforma objeto) {
        this.objeto = objeto;
    }

    public CPreforma getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CPreforma seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CPreforma> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CPreforma> listado) {
        this.listado = listado;
    }

    public BSesionManager getSession() {
        return session;
    }

    public void setSession(BSesionManager session) {
        this.session = session;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

}
