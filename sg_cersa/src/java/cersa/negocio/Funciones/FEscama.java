/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CEscama;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FEscama {
public static boolean insertar(CEscama objeto) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_escama(?,?,?,?,?)";
            lstP.add(new Parametro(1, objeto.getEscama_tipo().getTipo_id()));  
            lstP.add(new Parametro(2, objeto.getEscama_subtipo().getModelo_id()));
            lstP.add(new Parametro(3, objeto.getEscama_usuario().getUsuario_id()));
            lstP.add(new Parametro(4, objeto.getEscama_peso()));
            lstP.add(new Parametro(5, objeto.getEscama_observacion()));       
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
            String sql = "select * from basedatos_cersa.f_delete_escama(?)";
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
public static boolean update(CEscama seleccion) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_escama(?,?,?,?,?,?)";
            lstP.add(new Parametro(1, seleccion.getEscama_id()));
            lstP.add(new Parametro(2, seleccion.getEscama_tipo().getTipo_id()));  
            lstP.add(new Parametro(3, seleccion.getEscama_subtipo().getModelo_id()));
            lstP.add(new Parametro(4, seleccion.getEscama_usuario().getUsuario_id()));
            lstP.add(new Parametro(5, seleccion.getEscama_peso()));
            lstP.add(new Parametro(6, seleccion.getEscama_observacion()));          
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
public static ArrayList<CEscama> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CEscama> lst = new ArrayList<CEscama>();
        CEscama objeto = null;
        try {
            while (rs.next()) {
                objeto = new CEscama(rs.getInt(0), 
                        FTipo.obtener_Id(rs.getInt(1)),
                        FModelo.obtener_Id(rs.getInt(2)),
                        rs.getDate(3),
                        rs.getTime(4),
                        FUsuario.obtener_Id(rs.getInt(5)),
                        rs.getDouble(6),
                        rs.getString(7));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
public static ArrayList<CEscama> obtenerTodas() throws Exception {
        ArrayList<CEscama> lst = new ArrayList<CEscama>();
        try {
            String sql = "select * from basedatos_cersa.f_select_escama()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

public static ArrayList<CEscama> obtener_Escama_Persona(int codigo) throws Exception {
        ArrayList<CEscama> lst = new ArrayList<CEscama>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.tescama where tescama.tescama_usuario=?;";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }


public static CEscama obtener_Id(int codigo) throws Exception {
        CEscama obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_escama_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CEscama();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
}
