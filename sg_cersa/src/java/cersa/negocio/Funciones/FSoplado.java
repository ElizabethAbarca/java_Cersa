/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CSoplado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FSoplado {
    
public static boolean insertar(CSoplado objeto) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_soplado(?,?,?,?)";
            lstP.add(new Parametro(1, objeto.getSoplado_usuario().getUsuario_id()));  
            lstP.add(new Parametro(2, objeto.getSoplado_fecha()));
            lstP.add(new Parametro(3, objeto.getSoplado_turno().getTurno_id()));
            lstP.add(new Parametro(4, objeto.getSoplado_peso()));       
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
            String sql = "select * from basedatos_cersa.f_delete_soplado(?)";
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
public static boolean update(CSoplado seleccion) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_soplado(?,?,?,?,?)";
            lstP.add(new Parametro(1, seleccion.getSoplado_id()));
            lstP.add(new Parametro(2, seleccion.getSoplado_usuario().getUsuario_id()));  
            lstP.add(new Parametro(3, seleccion.getSoplado_fecha()));
            lstP.add(new Parametro(4, seleccion.getSoplado_turno().getTurno_id()));
            lstP.add(new Parametro(5, seleccion.getSoplado_peso()));         
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
public static ArrayList<CSoplado> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CSoplado> lst = new ArrayList<CSoplado>();
        CSoplado objeto = null;
        try {
            while (rs.next()) {
                objeto = new CSoplado(rs.getInt(0), 
                        FUsuario.obtener_Id(rs.getInt(1)),
                        rs.getDate(2),
                        FTurno.obtener_Id(rs.getInt(4)),
                        rs.getDouble(3));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
public static ArrayList<CSoplado> obtenerTodas() throws Exception {
        ArrayList<CSoplado> lst = new ArrayList<CSoplado>();
        try {
            String sql = "select * from basedatos_cersa.f_select_soplado()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

public static CSoplado obtener_Id(int codigo) throws Exception {
        CSoplado obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_soplado_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CSoplado();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
}
