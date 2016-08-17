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
public class CPaca {
  private Integer paca_id;
  CTipo paca_tipo;
  private double paca_peso;
  private java.sql.Date paca_fecha;
  CUsuario paca_responsable;

    public CPaca() {
    }

    public Integer getPaca_id() {
        return paca_id;
    }

    public void setPaca_id(Integer paca_id) {
        this.paca_id = paca_id;
    }

    public CTipo getPaca_tipo() {
        return paca_tipo;
    }

    public void setPaca_tipo(CTipo paca_tipo) {
        this.paca_tipo = paca_tipo;
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

    public CUsuario getPaca_responsable() {
        return paca_responsable;
    }

    public void setPaca_responsable(CUsuario paca_responsable) {
        this.paca_responsable = paca_responsable;
    }

    public CPaca(Integer paca_id, CTipo paca_tipo, double paca_peso, Date paca_fecha, CUsuario paca_responsable) {
        this.paca_id = paca_id;
        this.paca_tipo = paca_tipo;
        this.paca_peso = paca_peso;
        this.paca_fecha = paca_fecha;
        this.paca_responsable = paca_responsable;
    }

}
