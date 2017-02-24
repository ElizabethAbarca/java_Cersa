/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Clases;

import java.sql.Date;

/**
 *
 * @author ELIZABETH-PC
 */
public class DEscama {
    CTipo etipo;
    CModelo emodelo;
    private java.sql.Date efecga;
    CUsuario eusuario;
    private double epeso;

    public DEscama() {
    }

    public DEscama(CTipo etipo, CModelo emodelo, Date efecga, CUsuario eusuario, double epeso) {
        this.etipo = etipo;
        this.emodelo = emodelo;
        this.efecga = efecga;
        this.eusuario = eusuario;
        this.epeso = epeso;
    }

    public CTipo getEtipo() {
        return etipo;
    }

    public void setEtipo(CTipo etipo) {
        this.etipo = etipo;
    }

    public CModelo getEmodelo() {
        return emodelo;
    }

    public void setEmodelo(CModelo emodelo) {
        this.emodelo = emodelo;
    }

    public Date getEfecga() {
        return efecga;
    }

    public void setEfecga(Date efecga) {
        this.efecga = efecga;
    }

    public CUsuario getEusuario() {
        return eusuario;
    }

    public void setEusuario(CUsuario eusuario) {
        this.eusuario = eusuario;
    }

    public double getEpeso() {
        return epeso;
    }

    public void setEpeso(double epeso) {
        this.epeso = epeso;
    }
    
            
}
