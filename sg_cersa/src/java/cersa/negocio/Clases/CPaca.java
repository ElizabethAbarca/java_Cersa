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
public class CPaca {
  private Integer paca_id;
  CSubproducto paca_subtipo;
  private double paca_peso;
  private java.sql.Date paca_fecha;
  private java.sql.Time paca_hora;
  CUsuario paca_responsable;

    public CPaca() {
    }

    public CPaca(Integer paca_id, CSubproducto paca_subtipo, double paca_peso, Date paca_fecha, Time paca_hora, CUsuario paca_responsable) {
        this.paca_id = paca_id;
        this.paca_subtipo = paca_subtipo;
        this.paca_peso = paca_peso;
        this.paca_fecha = paca_fecha;
        this.paca_hora = paca_hora;
        this.paca_responsable = paca_responsable;
    }

    public Integer getPaca_id() {
        return paca_id;
    }

    public void setPaca_id(Integer paca_id) {
        this.paca_id = paca_id;
    }

    public CSubproducto getPaca_subtipo() {
        return paca_subtipo;
    }

    public void setPaca_subtipo(CSubproducto paca_subtipo) {
        this.paca_subtipo = paca_subtipo;
    }

    public double getPaca_peso() {
        return paca_peso;
    }

    public void setPaca_peso(double paca_peso) {
        this.paca_peso = paca_peso;
    }

    public Date getPaca_fecha() {
        return paca_fecha;
    }

    public void setPaca_fecha(Date paca_fecha) {
        this.paca_fecha = paca_fecha;
    }

    public Time getPaca_hora() {
        return paca_hora;
    }

    public void setPaca_hora(Time paca_hora) {
        this.paca_hora = paca_hora;
    }

    public CUsuario getPaca_responsable() {
        return paca_responsable;
    }

    public void setPaca_responsable(CUsuario paca_responsable) {
        this.paca_responsable = paca_responsable;
    }

}
