/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CUsuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP
 */
@ManagedBean
@SessionScoped
public class BCerrarseccion {

    private CUsuario persona;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    /**
     * Creates a new instance of BCerrarseccion
     */
    public BCerrarseccion() {
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        if(httpServletRequest.getSession().getAttribute("session Usuario")!=null)
        {
            persona=(CUsuario) httpServletRequest.getSession().getAttribute("session Usuario");
        }
    }
     
    public String cerrarSession()
    {
        httpServletRequest.getSession().removeAttribute("session Usuario");
        facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Session cerrada correctamente", null);
        faceContext.addMessage(null, facesMessage);
        return "index";
    }

    public CUsuario getPersona() {
        return persona;
    }

    public void setPersona(CUsuario persona) {
        this.persona = persona;
    }

    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }
    
}
