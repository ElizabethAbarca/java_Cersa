/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FEscama;
import cersa.negocio.Clases.CEscama;
import cersa.negocio.Funciones.FSubproducto;
import cersa.negocio.Funciones.FTipo;
import cersa.negocio.Funciones.FTurno;
import cersa.negocio.Funciones.FUsuario;
import java.util.ArrayList;
import java.util.Date;
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
public class BEscama {    
    
    private int tipo;
    private int sub;
    private int usuario;
    private int tur;
    
    private java.util.Date fecha;
    
    private CEscama objeto;
    private CEscama seleccion;
    private ArrayList<CEscama> listado;
    
    /**
     * Creates a new instance of BEscama
     */
    public BEscama() {
         this.reinit();
    }
    
    private void reinit() {
        this.objeto = new CEscama();
        this.seleccion = new CEscama();
        this.listado = new ArrayList<>();
        this.Visualizacion();        
    }
    private void Visualizacion() {
        try {
            this.listado = FEscama.obtenerTodas();
        } catch (Exception e) {
           FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }
    
    public void Insercion()
    {
        try {
            objeto.setEscama_fecha(new java.sql.Date(fecha.getTime()));
            objeto.setEscama_subtipo(FSubproducto.obtener_Id(sub));
            objeto.setEscama_tipo(FTipo.obtener_Id(tipo));
            objeto.setEscama_turno(FTurno.obtener_Id(tur));
            objeto.setEscama_usuario(FUsuario.obtener_Id(usuario));
            if(FEscama.insertar(objeto))
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
            if (FEscama.update(seleccion)) {
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
            if (FEscama.eliminar(seleccion.getEscama_id())) {
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

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getTur() {
        return tur;
    }

    public void setTur(int tur) {
        this.tur = tur;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
    
}
