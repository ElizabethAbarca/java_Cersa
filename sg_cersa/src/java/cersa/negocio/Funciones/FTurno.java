/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CTurno;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FTurno {
public static ArrayList<CTurno> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CTurno> lst = new ArrayList<CTurno>();
        CTurno objeto = null;
        try {
            while (rs.next()) {
                objeto = new CTurno(rs.getInt(0), 
                        rs.getString(1));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
public static ArrayList<CTurno> obtenerTodas() throws Exception {
        ArrayList<CTurno> lst = new ArrayList<CTurno>();
        try {
            String sql = "select * from basedatos_cersa.f_select_turno()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

public static CTurno obtener_Id(int codigo) throws Exception {
        CTurno obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_turno_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CTurno();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
        
}
