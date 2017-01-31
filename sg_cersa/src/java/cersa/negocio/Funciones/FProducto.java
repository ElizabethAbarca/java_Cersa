/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CProductoTerminado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author RitaElizabeth
 */
public class FProducto {
     public static ArrayList<CProductoTerminado> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CProductoTerminado> lst = new ArrayList<CProductoTerminado>();
        CProductoTerminado objeto = null;
        try {
            while (rs.next()) {
                objeto = new CProductoTerminado(rs.getInt(0),
                        rs.getString(1),
                        rs.getDate(2),
                        rs.getString(3),
                        FUsuario.obtener_Id(rs.getInt(4)));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<CProductoTerminado> obtenerTodas() throws Exception {
        ArrayList<CProductoTerminado> lst = new ArrayList<CProductoTerminado>();
        try {
            String sql = "select * from basedatos_cersa.f_select_producto();";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static CProductoTerminado obtener_Id(int codigo) throws Exception {
        CProductoTerminado obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_producto_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CProductoTerminado();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static CProductoTerminado obtenerUltimo() throws Exception {
        CProductoTerminado obj;
        try {
            String sql = "SELECT * FROM basedatos_cersa.tproducto_terminado ORDER BY tproducto_id DESC LIMIT 1;";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            obj = new CProductoTerminado();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static Integer existePaquetes() throws Exception {
        int obj = 0;
        try {
            String sql = "select count(*) as total from basedatos_cersa.tproducto_terminado;";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            if (rs.next()) {
                obj = Integer.parseInt(rs.getString("total"));
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static Integer cuantasTulas(int codigo) throws Exception {
        int obj = 0;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select count(*) as total from basedatos_cersa.ttula where ttula.ttula_producto=?;";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            if (rs.next()) {
                obj = Integer.parseInt(rs.getString("total"));
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static boolean insertar(CProductoTerminado objeto) throws Exception {
        boolean bandera = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_producto_terminado(?,?,?)";
            lstP.add(new Parametro(1, objeto.getProducto_nombre()));
            lstP.add(new Parametro(2, objeto.getProducto_descripcion()));
            lstP.add(new Parametro(3, objeto.getProducto_responsable().getUsuario_id()));
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

    public static Double SumaTula(int codigo) throws Exception {
        double obj = 0;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select SUM(ttula_peso) as suma from  basedatos_cersa.tusuario,basedatos_cersa.tproducto_terminado, basedatos_cersa.tseccion, basedatos_cersa.ttula where tproducto_responsable=tusuario_id and ttula_producto=tproducto_id and  ttula_seccion=tseccion_id  and ttula_producto=?;";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            if (rs.next()) {
                obj = Double.parseDouble(rs.getString("suma"));
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }

    public static boolean editar(String descripcion, int codigo) throws Exception {
        boolean respuesta = true;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "UPDATE basedatos_cersa.tproducto_terminado set tproducto_descripcion=? where tproducto_terminado.tproducto_id=?;";
            lstP.add(new Parametro(1, descripcion));
            lstP.add(new Parametro(2, codigo));
            boolean rs = AccesoDatos.ejecutaComando(sql, lstP);
            if (rs == true) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return respuesta;
    }

    public static ArrayList<CProductoTerminado> obtener_Producto_Persona(int codigo) throws Exception {
        ArrayList<CProductoTerminado> lst = new ArrayList<CProductoTerminado>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.tproducto_terminado where tproducto_terminado.tproducto_responsable=? order by tproducto_id desc;";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
}
