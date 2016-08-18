/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.Beans;

import cersa.negocio.Clases.CUsuario;
import cersa.negocio.Funciones.FUsuario;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class BIniciarseccion {

    private String cedula;
    private String contrasenia;
    private Integer rol;
    
    CUsuario persona;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;
    /**
     * Creates a new instance of BIniciarseccion
     */
    public BIniciarseccion() {
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
    }
    
    public String IniciarSeccion() throws Exception
    {        
        if(FUsuario.login(cedula,contrasenia)!=null)
        {         
            persona = FUsuario.login(cedula, contrasenia);
            if(Objects.equals(persona.getUsuario_rol().getRol_id(), rol))
            {
                httpServletRequest.getSession().setAttribute("sessionUsuario", persona);
                facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
                faceContext.addMessage(null, facesMessage);
                return "home";
            }  
            return "index";
        }
        else
        {
            facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario u/0 contraseña Incorrecta", null);
            faceContext.addMessage(null, facesMessage);
            return "index";
        }
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
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
