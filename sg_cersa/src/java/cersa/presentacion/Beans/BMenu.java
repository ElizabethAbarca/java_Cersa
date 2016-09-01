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
    public void fmrEscama()
    {
        this.ruta="faces/Formularios/fromEscama.xhtml";
    }    
   public void fmrPaca()
    {
        this.ruta="faces/Formularios/fromPaca.xhtml";
    }
   public void fmrPreforma()
    {
        this.ruta="faces/Formularios/fromPreforma.xhtml";
    }
   public void fmrSoplado()
    {
        this.ruta="faces/Formularios/fromSoplado.xhtml";
    }
}
