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
public class CListaProducto {
    private int numero;
    private java.util.Date fecha;
    private double total_dia;  

    public CListaProducto() {
    }

    public CListaProducto(int numero, Date fecha, double total_dia) {
        this.numero = numero;
        this.fecha = fecha;
        this.total_dia = total_dia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal_dia() {
        return total_dia;
    }

    public void setTotal_dia(double total_dia) {
        this.total_dia = total_dia;
    }
}
