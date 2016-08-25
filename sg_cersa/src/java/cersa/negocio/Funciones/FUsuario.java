/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CUsuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FUsuario {  
    
public static CUsuario login(String cedula, String clave) throws Exception  {
        
        CUsuario lst= null;
        ArrayList<Parametro> lstP = new ArrayList<>();
        try {
            String sql = "select * from basedatos_cersa.tusuario where iusuario_cedula=? and tusuario_clave=?;";
            lstP.add(new Parametro(1,cedula));
            lstP.add(new Parametro(2,clave));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = new CUsuario();
            lst = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    } 
     
public static boolean insertar(CUsuario objeto) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_usuario(?,?,?,?,?,?,?,?,?,?)";
            lstP.add(new Parametro(1, objeto.getUsuario_cedula()));  
            lstP.add(new Parametro(2, objeto.getUsuario_nombre()));
            lstP.add(new Parametro(3, objeto.getUsuario_apellido()));
            lstP.add(new Parametro(4, objeto.getUsuario_direccion()));
            lstP.add(new Parametro(5, objeto.getUsuario_email()));
            lstP.add(new Parametro(6, objeto.getUsuario_celular()));
            lstP.add(new Parametro(7, objeto.getUsuario_telefono()));  
            lstP.add(new Parametro(8, objeto.getUsuario_clave()));              
            lstP.add(new Parametro(9, objeto.getUsuario_estado()));
            lstP.add(new Parametro(10, objeto.getUsuario_rol().getRol_id()));      
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
            String sql = "select * from basedatos_cersa.f_delete_usuario(?)";
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
public static boolean update(CUsuario seleccion) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_update_usuario(?,?,?,?,?,?,?,?,?,?,?)";            
            lstP.add(new Parametro(1, seleccion.getUsuario_id()));              
            lstP.add(new Parametro(2, seleccion.getUsuario_cedula()));  
            lstP.add(new Parametro(3, seleccion.getUsuario_nombre()));
            lstP.add(new Parametro(4, seleccion.getUsuario_apellido()));
            lstP.add(new Parametro(5, seleccion.getUsuario_direccion()));
            lstP.add(new Parametro(6, seleccion.getUsuario_email()));
            lstP.add(new Parametro(7, seleccion.getUsuario_celular()));
            lstP.add(new Parametro(8, seleccion.getUsuario_telefono()));  
            lstP.add(new Parametro(9, seleccion.getUsuario_clave()));              
            lstP.add(new Parametro(10, seleccion.getUsuario_estado()));
            lstP.add(new Parametro(11, seleccion.getUsuario_rol().getRol_id()));  
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
   
public static ArrayList<CUsuario> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CUsuario> lst = new ArrayList<CUsuario>();
        CUsuario objeto = null;
        try {
            while (rs.next()) {
                objeto = new CUsuario(rs.getInt(0), 
                        rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getInt(9),
                FRol.obtener_Id(rs.getInt(10)));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
public static ArrayList<CUsuario> obtenerTodas() throws Exception {
        ArrayList<CUsuario> lst = new ArrayList<CUsuario>();
        try {
            String sql = "select * from basedatos_cersa.f_select_usuario()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

public static CUsuario obtener_Id(int codigo) throws Exception {
        CUsuario obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_usuario_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CUsuario();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
}
