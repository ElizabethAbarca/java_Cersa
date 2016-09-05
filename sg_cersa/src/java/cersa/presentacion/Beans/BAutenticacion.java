/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FUsuario;
import cersa.negocio.Clases.CRegistro;
import cersa.negocio.Funciones.FRegistro;
import cersa.negocio.Funciones.FTurno;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
public class BAutenticacion {

    private String clave;
    private String message, cedula;
    
    CRegistro nuevoregistro;
    /**
     * Creates a new instance of BAutenticacion
     */
    @ManagedProperty(value = "#{bSesionManager}")
    private BSesionManager session;
    
    public BAutenticacion() {
        HttpSession sesion = Util.getSession();
        sesion.setMaxInactiveInterval(5000);
    }
    public String loginProject() throws Exception {

        boolean result = FUsuario.identificarse(cedula, clave);
        if (result) {
            // get Http Session and store username
            session.setEmpleado(FUsuario.autenticar(cedula, clave));
            nuevoregistro = new CRegistro(FUsuario.obtener_Id(session.getEmpleado().getUsuario_id()),FTurno.obtener_Id(1));
            FRegistro.insertar(nuevoregistro);
            if(session.getEmpleado().getUsuario_rol().getRol_id()==2)
            {
            HttpSession sesion = Util.getSession();
            sesion.setAttribute("username", cedula); 
            return "Supervisor";
            } 
            if(session.getEmpleado().getUsuario_rol().getRol_id()==1)
            {
            HttpSession sesion = Util.getSession();
            sesion.setAttribute("username", cedula); 
            return "General";
            }
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Usuario Invalido!",
                    "Por favor, verfique que los datos ingresados son correctos!!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            return "login";
        }
        return null;
    }
 
    public String logout() throws Exception {
      nuevoregistro = new CRegistro(FUsuario.obtener_Id(session.getEmpleado().getUsuario_id()),FTurno.obtener_Id(2));
      FRegistro.insertar(nuevoregistro);
      HttpSession sesion = Util.getSession();
      sesion.invalidate();
      this.session=null;
      return "login";
   }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public BSesionManager getSession() {
        return session;
    }

    public void setSession(BSesionManager session) {
        this.session = session;
    }
    
    
}
