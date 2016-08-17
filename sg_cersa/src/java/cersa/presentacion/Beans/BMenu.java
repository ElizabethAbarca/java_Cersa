/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
public class BMenu {

    private String ruta; 
    /**
     * Creates a new instance of BeanMenu
     */
    public BMenu() {
    }
      
    
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public void fmrCliente()
    {
        this.ruta="faces/Formularios/formularioCliente.xhtml";
    }
    public void fmrEmpleado()
    {
        this.ruta="faces/Formularios/formularioEmpledo.xhtml";
    }
    public void fmrProducto()
    {
        this.ruta="faces/Formularios/formularioProducto.xhtml";
    }
    public void fmrTipo()
    {
        this.ruta="faces/Formularios/formularioTipo.xhtml";
    }
    public void fmrCategoria()
    {
        this.ruta="faces/Formularios/formularioCategoria.xhtml";
    }
    public void fmrLista()
    {
        this.ruta="faces/Formularios/reporteSolicitud.xhtml";
    }
    public void fmrFacturas()
    {
        this.ruta="faces/Formularios/formularioFactura.xhtml";
    }
    
    
}
