/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CUsuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author HP
 */
@ManagedBean
@SessionScoped
public class BSesionManager implements Serializable{

    private CUsuario empleado;
    /**
     * Creates a new instance of BSesionManager
     */
    public BSesionManager() {
        this.empleado= new CUsuario();
    }

    public CUsuario getEmpleado() {
        return empleado;
    }

    public void setEmpleado(CUsuario empleado) {
        this.empleado = empleado;
    }
    
}
