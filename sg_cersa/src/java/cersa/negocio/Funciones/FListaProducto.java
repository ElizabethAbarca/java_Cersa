/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import cersa.negocio.Clases.CListaProducto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author RitaElizabeth
 */
public class FListaProducto {
    
    public static ArrayList<CListaProducto> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CListaProducto> lst = new ArrayList<CListaProducto>();
        CListaProducto objeto = null;
        try {
            while (rs.next()) {
                objeto = new CListaProducto(rs.getInt(0),
                        rs.getDate(1),
                        rs.getDouble(2));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<CListaProducto> lista_Producto_Fecha() throws Exception {

        ArrayList<CListaProducto> lst = new ArrayList<CListaProducto>();
        try {
            String sql = "SELECT row_number() OVER(ORDER BY  tproducto_fecha DESC), tproducto_fecha, SUM(ttula_peso) as total_dia FROM basedatos_cersa.tproducto_terminado, basedatos_cersa.ttula \n"
                    + "WHERE tproducto_id=ttula_producto\n"
                    + "GROUP BY  tproducto_fecha";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
}
