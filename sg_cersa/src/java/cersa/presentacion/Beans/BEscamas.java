/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CEscama;
import cersa.negocio.Funciones.FEscama;
import cersa.negocio.Funciones.FModelo;
import cersa.negocio.Funciones.FTipo;
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
public class BEscamas {

    private CEscama objeto;
    private CEscama seleccion;
    private ArrayList<CEscama> listado;

    @ManagedProperty(value = "#{bSesionManager}")
    private BSesionManager session;

    /**
     * Creates a new instance of BEscamas
     */
    public BEscamas() {
        this.reinit();
        objeto = null;
    }

    private int tipo;
    private int sub;

    @PostConstruct
    private void reinit() {
        this.objeto = new CEscama();
        this.seleccion = new CEscama();
        this.listado = new ArrayList<>();
        this.Visualizacion();
    }

    private void Visualizacion() {
        try {
            this.listado = FEscama.obtener_Escama_Persona(session.getEmpleado().getUsuario_id());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Error" + e.getMessage()));
        }
    }

    public void Insercion() {
        try {
            objeto.setEscama_tipo(FTipo.obtener_Id(tipo));
            objeto.setEscama_usuario(session.getEmpleado());
            objeto.setEscama_subtipo(FModelo.obtener_Id(sub));
            if (objeto.getEscama_peso() > 0) {
                if (FEscama.insertar(objeto)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito. Datos Ingresados ",
                            "Exito. Datos Ingresados "));
                    DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
                    this.objeto = new CEscama();
                    reinit();
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
                    "Error " + e.getMessage(),
                    "Error " + e.getMessage()));

        }
    }

    public void Actualizacion() {
        try {
            seleccion.setEscama_subtipo(FModelo.obtener_Id(sub));
            seleccion.setEscama_tipo(FTipo.obtener_Id(tipo));
            seleccion.setEscama_usuario(session.getEmpleado());
            if (seleccion.getEscama_peso() > 0) {
                if (FEscama.update(seleccion)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito. Datos Actualizados",
                            "Exito. Datos Actualizados"));
                    DefaultRequestContext.getCurrentInstance().execute("PF('wglEditar').hide()");
                    reinit();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error. Datos no Actualizados",
                            "Errot. Datos no Actualizados"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Peso, deber ser  mayor a cero",
                        "Peso, deber ser mayor a cero"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error" + e.getMessage(),
                    "Errot" + e.getMessage()));
        }
    }

    public void Eliminacion() {
        try {
            if (FEscama.eliminar(seleccion.getEscama_id())) {
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

    public CEscama getObjeto() {
        return objeto;
    }

    public void setObjeto(CEscama objeto) {
        this.objeto = objeto;
    }

    public CEscama getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CEscama seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CEscama> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CEscama> listado) {
        this.listado = listado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public BSesionManager getSession() {
        return session;
    }

    public void setSession(BSesionManager session) {
        this.session = session;
    }
}
