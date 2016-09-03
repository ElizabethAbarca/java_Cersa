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
public class CPreforma {
  private Integer preforma_id;
  CSubproducto preforma_subtipo;
  private double preforma_pesoingreso;
  private java.sql.Date preforma_fecha;  
  private java.sql.Time preforma_hora;
  CUsuario preforma_usuario;  

    public CPreforma() {
    }

    public CPreforma(Integer preforma_id, CSubproducto preforma_subtipo, double preforma_pesoingreso, Date preforma_fecha, Time preforma_hora, CUsuario preforma_usuario) {
        this.preforma_id = preforma_id;
        this.preforma_subtipo = preforma_subtipo;
        this.preforma_pesoingreso = preforma_pesoingreso;
        this.preforma_fecha = preforma_fecha;
        this.preforma_hora = preforma_hora;
        this.preforma_usuario = preforma_usuario;
    }

    public Integer getPreforma_id() {
        return preforma_id;
    }

    public void setPreforma_id(Integer preforma_id) {
        this.preforma_id = preforma_id;
    }

    public CSubproducto getPreforma_subtipo() {
        return preforma_subtipo;
    }

    public void setPreforma_subtipo(CSubproducto preforma_subtipo) {
        this.preforma_subtipo = preforma_subtipo;
    }

    public double getPreforma_pesoingreso() {
        return preforma_pesoingreso;
    }

    public void setPreforma_pesoingreso(double preforma_pesoingreso) {
        this.preforma_pesoingreso = preforma_pesoingreso;
    }

    public Date getPreforma_fecha() {
        return preforma_fecha;
    }

    public void setPreforma_fecha(Date preforma_fecha) {
        this.preforma_fecha = preforma_fecha;
    }

    public Time getPreforma_hora() {
        return preforma_hora;
    }

    public void setPreforma_hora(Time preforma_hora) {
        this.preforma_hora = preforma_hora;
    }

    public CUsuario getPreforma_usuario() {
        return preforma_usuario;
    }

    public void setPreforma_usuario(CUsuario preforma_usuario) {
        this.preforma_usuario = preforma_usuario;
    }

}
