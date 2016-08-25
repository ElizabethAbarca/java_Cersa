/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FPaca;
import cersa.negocio.Clases.CPaca;
import cersa.negocio.Funciones.FTipo;
import cersa.negocio.Funciones.FUsuario;
import java.util.ArrayList;
import java.sql.Date;
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
public class BPaca {

    private CPaca objeto;
    private CPaca seleccion;
    private ArrayList<CPaca> listado;
    
    
    private int tipo;
    private int usuario;
    private java.util.Date fecha;
    /**
     * Creates a new instance of BPaca
     */
    public BPaca() {
        this.reinit();
    }
    
    private void reinit() {
        this.objeto = new CPaca();
        this.seleccion = new CPaca();
        this.listado = new ArrayList<>();
        this.Visualizacion();        
        //this.objDependenciaSel = this.lstDependencias.get(0);
    }

    public BPaca(CPaca objeto, CPaca seleccion, ArrayList<CPaca> listado, int tipo, int usuario, java.util.Date fecha) {
        this.objeto = objeto;
        this.seleccion = seleccion;
        this.listado = listado;
        this.tipo = tipo;
        this.usuario = usuario;
        this.fecha = fecha;
    }
   
    private void Visualizacion() {
        try {
            this.listado = FPaca.obtenerTodas();
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    
    public void Insercion()
    {
        try {    
            objeto.setPaca_tipo(FTipo.obtener_Id(tipo));
            objeto.setPaca_responsable(FUsuario.obtener_Id(usuario));
            objeto.setPaca_fecha(new java.sql.Date(fecha.getTime()));
            if(FPaca.insertar(objeto))
            {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Datos Ingresados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
                DefaultRequestContext.getCurrentInstance().execute("PF('wglInsertar').hide()");
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
            if (FPaca.update(seleccion)) {
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
            if (FPaca.eliminar(seleccion.getPaca_id())) {
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

    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    
    public CPaca getObjeto() {
        return objeto;
    }

    public void setObjeto(CPaca objeto) {
        this.objeto = objeto;
    }

    public CPaca getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CPaca seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CPaca> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CPaca> listado) {
        this.listado = listado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
    
}
