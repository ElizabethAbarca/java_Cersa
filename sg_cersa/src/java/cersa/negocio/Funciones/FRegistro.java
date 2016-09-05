/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CRegistro;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FRegistro {
    
public static boolean insertar(CRegistro objeto) throws Exception {
        boolean bandera = false;
        try {
             ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_insert_registro(?,?)";
            lstP.add(new Parametro(1, objeto.getRegistro_usuario().getUsuario_id()));  
            lstP.add(new Parametro(2, objeto.getRegistro_turno().getTurno_id()));      
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
public static ArrayList<CRegistro> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CRegistro> lst = new ArrayList<CRegistro>();
        CRegistro objeto = null;
        try {
            while (rs.next()) {
                objeto = new CRegistro(rs.getInt(0), 
                        FUsuario.obtener_Id(rs.getInt(1)),
                        rs.getDate(2),
                        FTurno.obtener_Id(rs.getInt(3)),
                        rs.getTime(4));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
public static ArrayList<CRegistro> obtenerTodas() throws Exception {
        ArrayList<CRegistro> lst = new ArrayList<CRegistro>();
        try {
            String sql = "select * from basedatos_cersa.f_select_registro()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
}
