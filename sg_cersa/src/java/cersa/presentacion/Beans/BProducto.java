/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CProductoTerminado;
import cersa.negocio.Clases.CTula;
import cersa.negocio.Funciones.FProducto;
import cersa.negocio.Funciones.FSeccion;
import cersa.negocio.Funciones.FTula;
import cersa.validacion.validacion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;


/**
 *
 * @author RitaElizabeth
 */
@ManagedBean
@ViewScoped
public class BProducto {

    /**
     * Creates a new instance of BProducto
     */
    private CProductoTerminado objeto;
    private CProductoTerminado seleccion;
    private ArrayList<CProductoTerminado> listado;
    

    @ManagedProperty(value = "#{bSesionManager}")
    private BSesionManager session;

    // para la tula
    private double peso_tula = 0;
    private List<CTula> listaTula;
    private CTula seleccionTula;
    String fecha = validacion.actualDate();
    int codigoTula = 0;
    int seccion;

    double sumaProducto = 0;
    CProductoTerminado nuevoPaquete = null;
    String nombre_ultimo = null;

    String descripcion_producto = null;

    public BProducto() {
        reinit();
    }
//
    @PostConstruct
    private void reinit() {
        this.objeto = new CProductoTerminado();
        this.seleccion = new CProductoTerminado();
        this.seleccionTula = new CTula();
        this.listado = new ArrayList<>();       
        this.listaTula = new ArrayList<>();
        this.Visualizacion();
    }
     // listar paquetes
    private void Visualizacion() {
        try {
            this.listado = FProducto.obtener_Producto_Persona(session.getEmpleado().getUsuario_id());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error"+e.getMessage(),
                        "Error"+e.getMessage()));
        }
    }

    // crer nombre lista
    public String ObtenerUltimaTula() throws Exception {
        int ultimo = FProducto.existePaquetes();
        if (ultimo == 0) {
            return (1 + "_TULA_" + fecha);
        } else {
            CProductoTerminado numero = FProducto.obtenerUltimo();
            return ((numero.getProducto_id() + 1) + "_TULA_" + fecha);
        }
    }
// nombrar una lista 

    public void Insercion() {
        try {
            nombre_ultimo = ObtenerUltimaTula();
            objeto.setProducto_nombre(nombre_ultimo);
            descripcion_producto = "(NG)Ninguno";
            objeto.setProducto_descripcion(descripcion_producto);
            objeto.setProducto_responsable(session.getEmpleado());
            if (FProducto.insertar(objeto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Nueva Lista",
                        "Exito. Nueva Lista"));
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEditar').hide()");
                nuevoPaquete = FProducto.obtenerUltimo();
                codigoTula = nuevoPaquete.getProducto_id();
                Visualizacion();
            } else {                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Acción no válida",
                        "Error. Acción no válida"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error"+e.getMessage(),
                        "Error"+e.getMessage()));
        }
    }
//ingresar una tula

    public void IngresarDatosTula() {
        try {
            CTula nuevoTula = new CTula(null, this.peso_tula, this.nuevoPaquete, FSeccion.obtener_Id(seccion));
            if (FTula.insertar(nuevoTula)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Datos Ingresados",
                        "Exito. Datos Ingresados"));
                
                this.listaTula = FTula.obtener_Tula_Producto(this.codigoTula);
                this.sumaProducto = FProducto.SumaTula(this.codigoTula);
                peso_tula = 0;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Datos no Ingresados",
                        "Error. Datos no Ingresados"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error"+e.getMessage(),
                        "Error"+e.getMessage()));
        }
    }

    //editar un tula 
    public void EditarDatosTula() {
        try {
            seleccionTula.setTula_seccion(FSeccion.obtener_Id(seccion));
            if (FTula.modificar(seleccionTula)) {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Datos Actualizados",
                        "Exito. Datos Actualizados"));
                DefaultRequestContext.getCurrentInstance().execute("PF('wglEditar').hide()");
                this.sumaProducto = FProducto.SumaTula(this.codigoTula);                
            } else {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Datos no Actualizados",
                        "Error. Datos no Actualizados"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error"+e.getMessage(),
                        "Error"+ e.getMessage()));
        }
    }

    // editar un lista
    public void Actualizacion(Integer numeroPaquete) {
        try {
            this.nuevoPaquete = FProducto.obtener_Id(numeroPaquete);
            nombre_ultimo = this.nuevoPaquete.getProducto_nombre();
            if (nombre_ultimo != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Datos Obtenidos",
                        "Exito. Datos Obtenidos"));
                DefaultRequestContext.getCurrentInstance().execute("PF('wglLista').hide()");
                this.codigoTula = nuevoPaquete.getProducto_id();
                this.listaTula = FTula.obtener_Tula_Producto(this.codigoTula);
                this.peso_tula=0.1;
                this.seccion=1;
                descripcion_producto = nuevoPaquete.getProducto_descripcion();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error. Datos no Obtenidos",
                        "Error. Datos no Obtenidos"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error"+e.getMessage(),
                        "Error"+e.getMessage()));
        }
    }
   

    public void Registro(String descripcion) {
        try {                  
            if (FProducto.editar(descripcion, codigoTula)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito. Actualizados",
                        "Exito. Actualizados"));
                 this.peso_tula = 0;
                this.sumaProducto = 0;
                this.seccion = 0;
                this.codigoTula = 0;
                this.listaTula = new ArrayList<>();
                this.nombre_ultimo = null;
                this.descripcion_producto=null;
                DisabledBoton();
                Visualizacion();               
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Exito. no Actualizados",
                        "Exito. no Actualizados"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error"+e.getMessage(),
                        "Error"+e.getMessage()));
        }
    }

    // total de lista por codigo
    public Integer TotalTulas(int numero) {
        try {
            return FProducto.cuantasTulas(numero);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error"+e.getMessage(),
                        "Error"+e.getMessage()));
        }
        return 0;
    }

    // total lista suma
    public double SumaTulas(int codigo) {
        try {
            return FProducto.SumaTula(codigo);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error"+e.getMessage(),
                        "Error"+e.getMessage()));
        }
        return 0;
    }

    // habilitar y desabilitar botones
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void EnabledBoton() {
        this.enabled = true;
    }

    public void DisabledBoton() {
        this.enabled = false;
    }

    public CProductoTerminado getObjeto() {
        return objeto;
    }

    public void setObjeto(CProductoTerminado objeto) {
        this.objeto = objeto;
    }

    public CProductoTerminado getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CProductoTerminado seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CProductoTerminado> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CProductoTerminado> listado) {
        this.listado = listado;
    }

    public BSesionManager getSession() {
        return session;
    }

    public void setSession(BSesionManager session) {
        this.session = session;
    }

    public double getPeso_tula() {
        return peso_tula;
    }

    public void setPeso_tula(double peso_tula) {
        this.peso_tula = peso_tula;
    }

    public List<CTula> getListaTula() {
        return listaTula;
    }

    public void setListaTula(List<CTula> listaTula) {
        this.listaTula = listaTula;
    }

    public CTula getSeleccionTula() {
        return seleccionTula;
    }

    public void setSeleccionTula(CTula seleccionTula) {
        this.seleccionTula = seleccionTula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCodigoTula() {
        return codigoTula;
    }

    public void setCodigoTula(int codigoTula) {
        this.codigoTula = codigoTula;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public double getSumaProducto() {
        return sumaProducto;
    }

    public void setSumaProducto(double sumaProducto) {
        this.sumaProducto = sumaProducto;
    }

    public CProductoTerminado getNuevoPaquete() {
        return nuevoPaquete;
    }

    public void setNuevoPaquete(CProductoTerminado nuevoPaquete) {
        this.nuevoPaquete = nuevoPaquete;
    }

    public String getNombre_ultimo() {
        return nombre_ultimo;
    }

    public void setNombre_ultimo(String nombre_ultimo) {
        this.nombre_ultimo = nombre_ultimo;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }
    
}
