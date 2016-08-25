/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import cersa.negocio.Clases.CTipo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FTipo {

public static ArrayList<CTipo> llenar(ConjuntoResultado rs) throws Exception {
        ArrayList<CTipo> lst = new ArrayList<CTipo>();
        CTipo objeto = null;
        try {
            while (rs.next()) {
                objeto = new CTipo(rs.getInt(0), 
                        rs.getString(1));
                lst.add(objeto);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
public static ArrayList<CTipo> obtenerTodas() throws Exception {
        ArrayList<CTipo> lst = new ArrayList<CTipo>();
        try {
            String sql = "select * from basedatos_cersa.f_select_tipo()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenar(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

public static CTipo obtener_Id(int codigo) throws Exception {
        CTipo obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from basedatos_cersa.f_select_tipo_id(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new CTipo();
            obj = llenar(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }    
}
