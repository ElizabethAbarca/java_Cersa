/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Clases;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class CPreforma {
  private Integer preforma_id;
  CSubproducto preforma_subtipo;
  private double preforma_precio;
  private double preforma_pesoingreso;
  private java.sql.Date preforma_fecha;
  CTurno preforma_turno;
  CUsuario preforma_usuario;

    public CPreforma() {
    }

    public CPreforma(Integer preforma_id, CSubproducto preforma_subtipo, double preforma_precio, double preforma_pesoingreso, Date preforma_fecha, CTurno preforma_turno, CUsuario preforma_usuario) {
        this.preforma_id = preforma_id;
        this.preforma_subtipo = preforma_subtipo;
        this.preforma_precio = preforma_precio;
        this.preforma_pesoingreso = preforma_pesoingreso;
        this.preforma_fecha = preforma_fecha;
        this.preforma_turno = preforma_turno;
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

    public double getPreforma_precio() {
        return preforma_precio;
    }

    public void setPreforma_precio(double preforma_precio) {
        this.preforma_precio = preforma_precio;
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

    public CTurno getPreforma_turno() {
        return preforma_turno;
    }

    public void setPreforma_turno(CTurno preforma_turno) {
        this.preforma_turno = preforma_turno;
    }

    public CUsuario getPreforma_usuario() {
        return preforma_usuario;
    }

    public void setPreforma_usuario(CUsuario preforma_usuario) {
        this.preforma_usuario = preforma_usuario;
    }

  
}
