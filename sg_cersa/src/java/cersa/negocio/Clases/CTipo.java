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
public class CTipo {
    private Integer tipo_id;
    private String tipo_Nombre;

    public CTipo() {
    }

    public CTipo(Integer tipo_id, String tipo_Nombre) {
        this.tipo_id = tipo_id;
        this.tipo_Nombre = tipo_Nombre;
    }

    public Integer getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(Integer tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getTipo_Nombre() {
        return tipo_Nombre;
    }

    public void setTipo_Nombre(String tipo_Nombre) {
        this.tipo_Nombre = tipo_Nombre;
    }

}
