/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CPaca;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FPaca {
    public static boolean insertar(CPaca objeto) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_paca(?,?,?)";
            lstP.add(new Parametro(1, objeto.getPaca_subtipo().getSubproducto_id()));  
            lstP.add(new Parametro(2, objeto.getPaca_peso()));
            lstP.add(new Parametro(3, objeto.getPaca_responsable().getUsuario_id()));      
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
            String sql = "select * from basedatos_cersa.f_delete_paca(?)";
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
public static boolean update(CPaca seleccion) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_paca(?,?,?,?)";
            lstP.add(new Parametro(1, seleccion.getPaca_id()));
            lstP.add(new Parametro(2, seleccion.getPaca_subtipo().getSubproducto_id()));
            lstP.add(new Parametro(3, seleccion.getPaca_peso())); 
            lstP.add(new Parametro(4, seleccion.getPaca_responsable().getUsuario_id()));         
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
public static ArrayList<CPaca> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CPaca> lst = new ArrayList<CPaca>();
        CPaca objeto = null;
        try {
            while (rs.next()) {
                objeto = new CPaca(rs.getInt(0), 
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
public static ArrayList<CPaca> obtenerTodas() throws Exception {
        ArrayList<CPaca> lst = new ArrayList<CPaca>();
        try {
            String sql = "select * from basedatos_cersa.f_select_paca()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

public static ArrayList<CPaca> obtener_Paca_Persona(int codigo) throws Exception {
        ArrayList<CPaca> lst = new ArrayList<CPaca>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.tpaca where tpaca.tpaca_responsable=?;";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

public static CPaca obtener_Id(int codigo) throws Exception {
        CPaca obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_paca_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CPaca();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
}
