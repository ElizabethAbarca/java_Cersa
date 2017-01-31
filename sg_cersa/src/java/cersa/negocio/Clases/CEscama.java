/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Clases;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author HP
 */
public class CEscama {
  private Integer escama_id;
  CTipo escama_tipo;
  CModelo escama_subtipo;
  private java.sql.Date escama_fecha;
  private java.sql.Time escama_hora;
  CUsuario escama_usuario;
  private double escama_peso;
  private String escama_observacion;

    public CEscama() {
    }

    public CEscama(Integer escama_id, CTipo escama_tipo, CModelo escama_subtipo, Date escama_fecha, Time escama_hora, CUsuario escama_usuario, double escama_peso, String escama_observacion) {
        this.escama_id = escama_id;
        this.escama_tipo = escama_tipo;
        this.escama_subtipo = escama_subtipo;
        this.escama_fecha = escama_fecha;
        this.escama_hora = escama_hora;
        this.escama_usuario = escama_usuario;
        this.escama_peso = escama_peso;
        this.escama_observacion = escama_observacion;
    }

    
    public Integer getEscama_id() {
        return escama_id;
    }

    public void setEscama_id(Integer escama_id) {
        this.escama_id = escama_id;
    }

    public CTipo getEscama_tipo() {
        return escama_tipo;
    }

    public void setEscama_tipo(CTipo escama_tipo) {
        this.escama_tipo = escama_tipo;
    }

    public CModelo getEscama_subtipo() {
        return escama_subtipo;
    }

    public void setEscama_subtipo(CModelo escama_subtipo) {
        this.escama_subtipo = escama_subtipo;
    }

    public Date getEscama_fecha() {
        return escama_fecha;
    }

    public void setEscama_fecha(Date escama_fecha) {
        this.escama_fecha = escama_fecha;
    }

    public Time getEscama_hora() {
        return escama_hora;
    }

    public void setEscama_hora(Time escama_hora) {
        this.escama_hora = escama_hora;
    }

    public CUsuario getEscama_usuario() {
        return escama_usuario;
    }

    public void setEscama_usuario(CUsuario escama_usuario) {
        this.escama_usuario = escama_usuario;
    }

    public double getEscama_peso() {
        return escama_peso;
    }

    public void setEscama_peso(double escama_peso) {
        this.escama_peso = escama_peso;
    }

    public String getEscama_observacion() {
        return escama_observacion;
    }

    public void setEscama_observacion(String escama_observacion) {
        this.escama_observacion = escama_observacion;
    }
  
}
