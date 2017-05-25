/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.DEscama;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ELIZABETH-PC
 */
public class FDatosEscama {

    public static ArrayList<DEscama> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<DEscama> lst = new ArrayList<DEscama>();
        DEscama objeto = null;
        try {
            while (rs.next()) {
                objeto = new DEscama(FTipo.obtener_Id(rs.getInt(0)),
                        FModelo.obtener_Id(rs.getInt(1)),
                        rs.getDate(2),
                        FUsuario.obtener_Id(rs.getInt(3)),
                        rs.getDouble(4));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<DEscama> obtenerSuma_Escama_Persona(int codigo, int tipo, int sub) throws Exception {
        ArrayList<DEscama> lst = new ArrayList<DEscama>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "SELECT tescama_tipo,tescama_subtipo,tescama_fecha,tescama_usuario,SUM(tescama_peso) as tescama_peso FROM basedatos_cersa.tescama \n"
                    + "  WHERE tescama.tescama_usuario=? and tescama.tescama_tipo=? and tescama.tescama_subtipo=? \n"
                    + "  GROUP BY tescama_tipo,tescama_subtipo,tescama_fecha,tescama_usuario\n"
                    + "  ORDER BY tescama_fecha ASC;";
            lstP.add(new Parametro(1, codigo));
            lstP.add(new Parametro(2, tipo));
            lstP.add(new Parametro(3, sub));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static ArrayList<DEscama> obtenerDatos_Escama() throws Exception {
        ArrayList<DEscama> lst = new ArrayList<DEscama>();
        try {
            String sql = "SELECT tescama_tipo,tescama_subtipo,tescama_fecha,tescama_usuario,SUM(tescama_peso) as tescama_peso FROM basedatos_cersa.tescama \n"
                    + " GROUP BY tescama_tipo,tescama_subtipo,tescama_fecha,tescama_usuario\n"
                    + " ORDER BY tescama_fecha ASC;";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static ArrayList<Double> suma_Escama_general() throws Exception {
         ArrayList<Double> obj = new ArrayList<Double>();
         double lst;
        try {
            String sql = "SELECT tescama_tipo,tescama_subtipo,tescama_usuario,SUM(tescama_peso) as tescama_peso FROM basedatos_cersa.tescama \n"
                    + "GROUP BY tescama_tipo,tescama_subtipo,tescama_usuario";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                lst =Double.parseDouble(rs.getString("tescama_peso"));
                obj.add(lst);
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
}
