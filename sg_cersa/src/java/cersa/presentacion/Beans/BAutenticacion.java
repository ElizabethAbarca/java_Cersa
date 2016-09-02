/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Funciones.FUsuario;
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

    private static final long serialVersionUID = 1L;
    private String clave;
    private String cedula;
    /**
     * Creates a new instance of BAutenticacion
     */
    @ManagedProperty(value = "#{bSesionManager}")
    private BSesionManager session;
    
    public BAutenticacion() {
        HttpSession misession = Util.getSession();
        misession.setMaxInactiveInterval(5000);
    }
    public String loginProject() throws Exception {

        boolean result = FUsuario.identificarse(cedula, clave);
        if (result) {
            // get Http Session and store username
            session.setEmpleado(FUsuario.autenticar(cedula, clave));
            HttpSession misession = Util.getSession();
            misession.setAttribute("username", cedula); 
            return "home";
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
    }
 
    public String logout() {
      HttpSession misession = Util.getSession();
      misession.invalidate();
      this.session=null;
      return "login";
   }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
