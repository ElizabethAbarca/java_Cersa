/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CModelo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author RitaElizabeth
 */
public class FModelo {
    public static ArrayList<CModelo> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CModelo> lst = new ArrayList<CModelo>();
        CModelo objeto = null;
        try {
            while (rs.next()) {
                objeto = new CModelo(rs.getInt(0),
                        rs.getString(1));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<CModelo> obtenerTodas() throws Exception {
        ArrayList<CModelo> lst = new ArrayList<CModelo>();
        try {
            String sql = "select * from basedatos_cersa.f_select_modelo()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static CModelo obtener_Id(int codigo) throws Exception {
        CModelo obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_modelo_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CModelo();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static boolean insertar(CModelo objeto) throws Exception {
        boolean bandera = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_modelo(?)";
            lstP.add(new Parametro(1, objeto.getModelo_Nombre()));
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

    public static boolean modificar(CModelo objeto) throws Exception {
        boolean bandera = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_modelo(?,?)";
            lstP.add(new Parametro(1, objeto.getModelo_id()));
            lstP.add(new Parametro(2, objeto.getModelo_Nombre()));
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
