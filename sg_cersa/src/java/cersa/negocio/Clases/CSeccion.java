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
public class CSeccion {
    private Integer seccion_id;
  private String seccion_descripcion;

    public CSeccion() {
    }

    public CSeccion(Integer seccion_id, String seccion_descripcion) {
        this.seccion_id = seccion_id;
        this.seccion_descripcion = seccion_descripcion;
    }

    public Integer getSeccion_id() {
        return seccion_id;
    }

    public void setSeccion_id(Integer seccion_id) {
        this.seccion_id = seccion_id;
    }

    public String getSeccion_descripcion() {
        return seccion_descripcion;
    }

    public void setSeccion_descripcion(String seccion_descripcion) {
        this.seccion_descripcion = seccion_descripcion;
    }
  
}
