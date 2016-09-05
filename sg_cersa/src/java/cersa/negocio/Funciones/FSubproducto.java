/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CSubproducto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FSubproducto {
    
public static ArrayList<CSubproducto> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CSubproducto> lst = new ArrayList<CSubproducto>();
        CSubproducto objeto = null;
        try {
            while (rs.next()) {
                objeto = new CSubproducto(rs.getInt(0), 
                        rs.getString(1));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
public static ArrayList<CSubproducto> obtenerTodas() throws Exception {
        ArrayList<CSubproducto> lst = new ArrayList<CSubproducto>();
        try {
            String sql = "select * from basedatos_cersa.f_select_subproducto()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

public static CSubproducto obtener_Id(int codigo) throws Exception {
        CSubproducto obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_subproducto_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CSubproducto();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    } 
public static boolean insertar(CSubproducto objeto) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_subproducto(?)";
            lstP.add(new Parametro(1, objeto.getSubproducto_descripcion()));  
               
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
            String sql = "select * from basedatos_cersa.f_delete_subproducto(?)";
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
public static boolean update(CSubproducto seleccion) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_subproducto(?,?)";            
            lstP.add(new Parametro(1, seleccion.getSubproducto_id()));              
            lstP.add(new Parametro(2, seleccion.getSubproducto_descripcion()));

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
