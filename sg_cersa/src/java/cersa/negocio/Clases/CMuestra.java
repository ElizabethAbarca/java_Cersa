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
public class CMuestra {
    private Integer muestra_id;
    private String muestra_Nombre;

    public CMuestra(Integer muestra_id, String muestra_Nombre) {
        this.muestra_id = muestra_id;
        this.muestra_Nombre = muestra_Nombre;
    }

    public CMuestra() {
    }

    public Integer getMuestra_id() {
        return muestra_id;
    }

    public void setMuestra_id(Integer muestra_id) {
        this.muestra_id = muestra_id;
    }

    public String getMuestra_Nombre() {
        return muestra_Nombre;
    }

    public void setMuestra_Nombre(String muestra_Nombre) {
        this.muestra_Nombre = muestra_Nombre;
    }
    
}
