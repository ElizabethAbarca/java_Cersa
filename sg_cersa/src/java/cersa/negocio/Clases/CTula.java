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
public class CTula {
   private Integer tula_id;
    private double tula_peso;
    CProductoTerminado tula_producto;
    CSeccion tula_seccion;

    public CTula() {
    }

    public CTula(Integer tula_id, double tula_peso, CProductoTerminado tula_producto, CSeccion tula_seccion) {
        this.tula_id = tula_id;
        this.tula_peso = tula_peso;
        this.tula_producto = tula_producto;
        this.tula_seccion = tula_seccion;
    }

    public Integer getTula_id() {
        return tula_id;
    }

    public void setTula_id(Integer tula_id) {
        this.tula_id = tula_id;
    }

    public double getTula_peso() {
        return tula_peso;
    }

    public void setTula_peso(double tula_peso) {
        this.tula_peso = tula_peso;
    }

    public CProductoTerminado getTula_producto() {
        return tula_producto;
    }

    public void setTula_producto(CProductoTerminado tula_producto) {
        this.tula_producto = tula_producto;
    }

    public CSeccion getTula_seccion() {
        return tula_seccion;
    }

    public void setTula_seccion(CSeccion tula_seccion) {
        this.tula_seccion = tula_seccion;
    }
    
}
