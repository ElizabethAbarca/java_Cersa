/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Clases;

/**
 *
 * @author HP
 */
public class CTurno {
    private Integer turno_id;
    private String turno_Descripcion;

    public CTurno() {
    }

    public CTurno(Integer turno_id, String turno_Descripcion) {
        this.turno_id = turno_id;
        this.turno_Descripcion = turno_Descripcion;
    }

    public Integer getTurno_id() {
        return turno_id;
    }

    public void setTurno_id(Integer turno_id) {
        this.turno_id = turno_id;
    }

    public String getTurno_Descripcion() {
        return turno_Descripcion;
    }

    public void setTurno_Descripcion(String turno_Descripcion) {
        this.turno_Descripcion = turno_Descripcion;
    }

    
}
