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
public class CSubproducto {
    private Integer subproducto_id;
    private String subproducto_descripcion;

    public CSubproducto() {
    }

    public CSubproducto(Integer subproducto_id, String subproducto_descripcion) {
        this.subproducto_id = subproducto_id;
        this.subproducto_descripcion = subproducto_descripcion;
    }

    public Integer getSubproducto_id() {
        return subproducto_id;
    }

    public void setSubproducto_id(Integer subproducto_id) {
        this.subproducto_id = subproducto_id;
    }

    public String getSubproducto_descripcion() {
        return subproducto_descripcion;
    }

    public void setSubproducto_descripcion(String subproducto_descripcion) {
        this.subproducto_descripcion = subproducto_descripcion;
    }

}
