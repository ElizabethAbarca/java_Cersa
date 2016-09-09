/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FSoplado;
import cersa.negocio.Clases.CSoplado;
import cersa.negocio.Funciones.FMuestra;
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
public class BSoplado {

    private CSoplado objeto;
    private CSoplado seleccion;
    private ArrayList<CSoplado> listado;
    
    
    @ManagedProperty(value = "#{bSesionManager}")
    private BSesionManager session;
    /**
     * Creates a new instance of BSoplado
     */
    public BSoplado() {        
        this.reinit();
    }
    
    private int muestra;
    @PostConstruct
    private void reinit() {
        this.objeto = new CSoplado();
        this.seleccion = new CSoplado();
        this.listado = new ArrayList<>();
        this.Visualizacion();        
    }

    private void Visualizacion() {
        try {
            this.listado = FSoplado.obtener_Soplado_Persona(session.getEmpleado().getUsuario_id());
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    
    public void Insercion()
    {
        try {    
            objeto.setSoplado_muestra(FMuestra.obtener_Id(muestra));
            objeto.setSoplado_usuario(FUsuario.obtener_Id(session.getEmpleado().getUsuario_id()));
            if(FSoplado.insertar(objeto))
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
                context.addMessage( "Exito",new FacesMessage(e.getMessage()));
        }
    }

    
    public void Actualizacion() {
        try {            
            seleccion.setSoplado_muestra(FMuestra.obtener_Id(muestra));
            seleccion.setSoplado_usuario(FUsuario.obtener_Id(session.getEmpleado().getUsuario_id()));
            if (FSoplado.update(seleccion)) {
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
                context.addMessage( "Exito",new FacesMessage(e.getMessage()));
        }
    }
    
    public void Eliminacion() {
        try {
            if (FSoplado.eliminar(seleccion.getSoplado_id())) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Eliminados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEliminar').hide()");
                this.reinit();
            }
            else
            {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No Eliminados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }
                
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage( "Exito",new FacesMessage(e.getMessage()));
        }        
      }

    public CSoplado getObjeto() {
        return objeto;
    }

    public void setObjeto(CSoplado objeto) {
        this.objeto = objeto;
    }

    public CSoplado getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CSoplado seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CSoplado> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CSoplado> listado) {
        this.listado = listado;
    }

    public BSesionManager getSession() {
        return session;
    }

    public void setSession(BSesionManager session) {
        this.session = session;
    }

    public int getMuestra() {
        return muestra;
    }

    public void setMuestra(int muestra) {
        this.muestra = muestra;
    }

}
