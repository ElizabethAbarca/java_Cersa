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
public class CSoplado {
  private Integer soplado_id;
  CUsuario soplado_usuario;
  private java.sql.Date soplado_fecha;
  CTurno soplado_turno;
  private double soplado_peso;

    public CSoplado() {
    }

    public CSoplado(Integer soplado_id, CUsuario soplado_usuario, Date soplado_fecha, CTurno soplado_turno, double soplado_peso) {
        this.soplado_id = soplado_id;
        this.soplado_usuario = soplado_usuario;
        this.soplado_fecha = soplado_fecha;
        this.soplado_turno = soplado_turno;
        this.soplado_peso = soplado_peso;
    }

    public Integer getSoplado_id() {
        return soplado_id;
    }

    public void setSoplado_id(Integer soplado_id) {
        this.soplado_id = soplado_id;
    }

    public CUsuario getSoplado_usuario() {
        return soplado_usuario;
    }

    public void setSoplado_usuario(CUsuario soplado_usuario) {
        this.soplado_usuario = soplado_usuario;
    }

    public Date getSoplado_fecha() {
        return soplado_fecha;
    }

    public void setSoplado_fecha(Date soplado_fecha) {
        this.soplado_fecha = soplado_fecha;
    }

    public CTurno getSoplado_turno() {
        return soplado_turno;
    }

    public void setSoplado_turno(CTurno soplado_turno) {
        this.soplado_turno = soplado_turno;
    }

    public double getSoplado_peso() {
        return soplado_peso;
    }

    public void setSoplado_peso(double soplado_peso) {
        this.soplado_peso = soplado_peso;
    }

}
