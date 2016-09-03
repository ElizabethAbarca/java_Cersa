/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CPreforma;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FPreforma {
    
public static boolean insertar(CPreforma objeto) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_preforma(?,?,?)";
            lstP.add(new Parametro(1, objeto.getPreforma_subtipo().getSubproducto_id()));
            lstP.add(new Parametro(2, objeto.getPreforma_pesoingreso()));
            lstP.add(new Parametro(3, objeto.getPreforma_usuario().getUsuario_id()));       
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
public static boolean eliminar(int identificador) throws Exception {
        boolean bandera = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_delete_preforma(?)";
            lstP.add(new Parametro(1, identificador));
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
public static boolean update(CPreforma seleccion) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_preforma(?,?,?,?)";
            lstP.add(new Parametro(1, seleccion.getPreforma_id()));
            lstP.add(new Parametro(2, seleccion.getPreforma_subtipo().getSubproducto_id())); 
            lstP.add(new Parametro(3, seleccion.getPreforma_pesoingreso()));
            lstP.add(new Parametro(4, seleccion.getPreforma_usuario().getUsuario_id()));       
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
public static ArrayList<CPreforma> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CPreforma> lst = new ArrayList<CPreforma>();
        CPreforma objeto = null;
        try {
            while (rs.next()) {
                objeto = new CPreforma(rs.getInt(0), 
                        FSubproducto.obtener_Id(rs.getInt(1)),
                        rs.getDouble(2),
                        rs.getDate(3),
                        rs.getTime(4),
                        FUsuario.obtener_Id(rs.getInt(5)));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
public static ArrayList<CPreforma> obtenerTodas() throws Exception {
        ArrayList<CPreforma> lst = new ArrayList<CPreforma>();
        try {
            String sql = "select * from basedatos_cersa.f_select_preforma()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
public static ArrayList<CPreforma> obtener_Preforma_Persona(int codigo) throws Exception {
        ArrayList<CPreforma> lst = new ArrayList<CPreforma>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.tpreforma where tpreforma.tpreforma_usuario=?;";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
public static CPreforma obtener_Id(int codigo) throws Exception {
        CPreforma obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_preforma_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CPreforma();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
    
}
