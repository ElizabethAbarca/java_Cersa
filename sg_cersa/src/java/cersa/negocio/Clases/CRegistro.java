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
public class CRegistro {
  private Integer registro_id;
  CUsuario registro_usuario;
  private java.sql.Date registro_fecha;
  CTurno registro_turno;
  private java.sql.Time registro_hora;

    public CRegistro() {
    }

    public CRegistro(CUsuario registro_usuario, CTurno registro_turno) {
        this.registro_usuario = registro_usuario;
        this.registro_turno = registro_turno;
    }
    public CRegistro(Integer registro_id, CUsuario registro_usuario, Date registro_fecha, CTurno registro_turno, Time registro_hora) {
        this.registro_id = registro_id;
        this.registro_usuario = registro_usuario;
        this.registro_fecha = registro_fecha;
        this.registro_turno = registro_turno;
        this.registro_hora = registro_hora;
    }

    public Integer getRegistro_id() {
        return registro_id;
    }

    public void setRegistro_id(Integer registro_id) {
        this.registro_id = registro_id;
    }

    public CUsuario getRegistro_usuario() {
        return registro_usuario;
    }

    public void setRegistro_usuario(CUsuario registro_usuario) {
        this.registro_usuario = registro_usuario;
    }

    public Date getRegistro_fecha() {
        return registro_fecha;
    }

    public void setRegistro_fecha(Date registro_fecha) {
        this.registro_fecha = registro_fecha;
    }

    public CTurno getRegistro_turno() {
        return registro_turno;
    }

    public void setRegistro_turno(CTurno registro_turno) {
        this.registro_turno = registro_turno;
    }

    public Time getRegistro_hora() {
        return registro_hora;
    }

    public void setRegistro_hora(Time registro_hora) {
        this.registro_hora = registro_hora;
    }
  
}
