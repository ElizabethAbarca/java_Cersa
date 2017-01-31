/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CListaProducto;
import cersa.negocio.Funciones.FListaProducto;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author RitaElizabeth
 */
@ManagedBean
@ViewScoped
public class BListaProducto {

    private CListaProducto objeto;
    private CListaProducto seleccion;
    private ArrayList<CListaProducto> listado;

    /**
     * Creates a new instance of BListaProducto
     */
    public BListaProducto() {
        this.reinit();
    }

    @PostConstruct
    private void reinit() {
        this.objeto = new CListaProducto();
        this.seleccion = new CListaProducto();
        this.listado = new ArrayList<>();
        this.Visualizacion();

    }

    private void Visualizacion() {
        try {
            this.listado = FListaProducto.lista_Producto_Fecha();
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    public void DescargarExcel() throws Exception {

    }

    public CListaProducto getObjeto() {
        return objeto;
    }

    public void setObjeto(CListaProducto objeto) {
        this.objeto = objeto;
    }

    public CListaProducto getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(CListaProducto seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<CListaProducto> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CListaProducto> listado) {
        this.listado = listado;
    }

}
