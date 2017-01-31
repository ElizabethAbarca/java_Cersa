/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CSeccion;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author RitaElizabeth
 */
public class FSeccion {
    public static ArrayList<CSeccion> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CSeccion> lst = new ArrayList<CSeccion>();
        CSeccion objeto = null;
        try {
            while (rs.next()) {
                objeto = new CSeccion(rs.getInt(0),
                        rs.getString(1));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<CSeccion> obtenerTodas() throws Exception {
        ArrayList<CSeccion> lst = new ArrayList<CSeccion>();
        try {
            String sql = "select * from basedatos_cersa.f_select_seccion()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch ( SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static CSeccion obtener_Id(int codigo) throws Exception {
        CSeccion obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_seccion_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CSeccion();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static boolean insertar(CSeccion objeto) throws Exception {
        boolean bandera = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_seccion(?)";
            lstP.add(new Parametro(1, objeto.getSeccion_descripcion()));
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

    public static boolean modificar(CSeccion objeto) throws Exception {
        boolean bandera = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_seccion(?,?)";
            lstP.add(new Parametro(1, objeto.getSeccion_id()));
            lstP.add(new Parametro(2, objeto.getSeccion_descripcion()));
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
