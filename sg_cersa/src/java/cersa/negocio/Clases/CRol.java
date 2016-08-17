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
public class CRol {
  private Integer rol_id;
  private String rol_nombre;

    public CRol() {
    }

    public CRol(Integer rol_id, String rol_nombre) {
        this.rol_id = rol_id;
        this.rol_nombre = rol_nombre;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

    
}
