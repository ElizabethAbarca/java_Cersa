/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Clases;

/**
 *
 * @author RitaElizabeth
 */
public class CModelo {
    private Integer modelo_id;
    private String modelo_Nombre;

    public CModelo() {
    }

    public CModelo(Integer modelo_id, String modelo_Nombre) {
        this.modelo_id = modelo_id;
        this.modelo_Nombre = modelo_Nombre;
    }

    public Integer getModelo_id() {
        return modelo_id;
    }

    public void setModelo_id(Integer modelo_id) {
        this.modelo_id = modelo_id;
    }

    public String getModelo_Nombre() {
        return modelo_Nombre;
    }

    public void setModelo_Nombre(String modelo_Nombre) {
        this.modelo_Nombre = modelo_Nombre;
    }
}
