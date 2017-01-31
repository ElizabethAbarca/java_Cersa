/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CTula;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author RitaElizabeth
 */
public class FTula {

    public static ArrayList<CTula> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CTula> lst = new ArrayList<CTula>();
        CTula objeto = null;
        try {
            while (rs.next()) {
                objeto = new CTula(rs.getInt(0),
                        rs.getDouble(1),
                        FProducto.obtener_Id(rs.getInt(2)),
                        FSeccion.obtener_Id(rs.getInt(3)));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<CTula> obtenerTodas() throws Exception {
        ArrayList<CTula> lst = new ArrayList<CTula>();
        try {
            String sql = "select * from basedatos_cersa.f_select_tula()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static CTula obtener_Id(int codigo) throws Exception {
        CTula obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_tula_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CTula();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static ArrayList<CTula> obtener_Tula_Producto(int codigo) throws Exception {
        ArrayList<CTula> lst = new ArrayList<CTula>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.ttula where ttula_producto=?;";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static boolean insertar(CTula objeto) throws Exception {
        boolean bandera = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_tula(?,?,?)";
            lstP.add(new Parametro(1, objeto.getTula_peso()));
            lstP.add(new Parametro(2, objeto.getTula_producto().getProducto_id()));
            lstP.add(new Parametro(3, objeto.getTula_seccion().getSeccion_id()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                bandera = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return bandera;
    }

    public static double suma_Tula_Producto(int codigo) throws Exception {
        double obj = 0;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select SUM(ttula_peso) as total from basedatos_cersa.ttula where ttula_producto=?;";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            if (rs.next()) {
                obj = Double.parseDouble(rs.getString("total"));
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static boolean modificar(CTula objeto) throws Exception {
        boolean bandera = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_tula(?,?,?,?)";
            lstP.add(new Parametro(1, objeto.getTula_id()));
            lstP.add(new Parametro(2, objeto.getTula_peso()));
            lstP.add(new Parametro(3, objeto.getTula_producto().getProducto_id()));
            lstP.add(new Parametro(4, objeto.getTula_seccion().getSeccion_id()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                bandera = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return bandera;
    }
}
