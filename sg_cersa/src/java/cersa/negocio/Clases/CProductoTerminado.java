/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Clases;

import java.util.Date;

/**
 *
 * @author RitaElizabeth
 */
public class CProductoTerminado {
    private Integer producto_id;
    private String producto_nombre;
    private java.util.Date producto_fecha;
    private String producto_descripcion;
    CUsuario producto_responsable;

    public CProductoTerminado() {
    }

    public CProductoTerminado(Integer producto_id, String producto_nombre, Date producto_fecha, String producto_descripcion, CUsuario producto_responsable) {
        this.producto_id = producto_id;
        this.producto_nombre = producto_nombre;
        this.producto_fecha = producto_fecha;
        this.producto_descripcion = producto_descripcion;
        this.producto_responsable = producto_responsable;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public String getProducto_nombre() {
        return producto_nombre;
    }

    public void setProducto_nombre(String producto_nombre) {
        this.producto_nombre = producto_nombre;
    }

    public Date getProducto_fecha() {
        return producto_fecha;
    }

    public void setProducto_fecha(Date producto_fecha) {
        this.producto_fecha = producto_fecha;
    }

    public String getProducto_descripcion() {
        return producto_descripcion;
    }

    public void setProducto_descripcion(String producto_descripcion) {
        this.producto_descripcion = producto_descripcion;
    }

    public CUsuario getProducto_responsable() {
        return producto_responsable;
    }

    public void setProducto_responsable(CUsuario producto_responsable) {
        this.producto_responsable = producto_responsable;
    }
    
}
