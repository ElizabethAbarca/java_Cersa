/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodatos;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author DIEGO_PALACIOS
 */
public class AccesoDatos {

    public static boolean ejecutaComando(String comando, ArrayList<Parametro> parametros) throws Exception {
        boolean respuesta = false;

        PreparedStatement prts = null;
        Connection con = null;
        try {
            Global global = new Global();

//registro el driver
            Class.forName(global.getDRIVER());
            try {

                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());

//preparo el comando 
                prts = con.prepareStatement(comando);

//mando mis parametos a la funcion 
                for (Parametro parm : parametros) {

                    prts.setObject(parm.getPosicion(), parm.getValor());

                }
                //ejecutando la sentencia

                int i = prts.executeUpdate();

                if (i > 0) {
                    respuesta = true;
                }

                prts.close();

                prts = null;

            } catch (SQLException exCon) {
                throw exCon;
            } finally {

                try {

                    if (con != null) {
                        //verifico si la conexion no nesta cerrada

                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }

                } catch (Exception ex) {
                    throw ex;
                }
            }

        } catch (ClassNotFoundException e) {
            throw e;
        }

        return respuesta;
    }
    //</editor-fold>

    //////funciones genericas que envian como parametro el nombre de la funcio(procedimiento almacenado) y NO recibe una  lista de paarametros
    public static ConjuntoResultado ejecutaQuery(String query) throws Exception {
        ResultSet rs = null;
        PreparedStatement pst = null;
        ConjuntoResultado conj = new ConjuntoResultado();
        Connection con = null;
        try {
            Global global = new Global();
            Class.forName(global.getDRIVER());
            try {
                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                conj.Fill(rs);
                rs.close();
                pst.close();
                rs = null;
                pst = null;
            } catch (SQLException exConec) {
                throw exConec;
            } finally {
                try {
                    if (con != null) {
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (ClassNotFoundException exCarga) {
            throw exCarga;
        }
        return conj;
    }

//////funciones genericas que envian como parametro el nombre de la funcio(procedimiento almacenado) y de ser el caso recibe una  lista de paarametros
    public static ConjuntoResultado ejecutaQuery(String query, ArrayList<Parametro> parametros) throws Exception {

        ResultSet rs = null;
        PreparedStatement ptrs = null;
        ConjuntoResultado conj = new ConjuntoResultado();
        Connection con = null;
        try {
            Global global = new Global();
            Class.forName(global.getDRIVER());
            try {
                String url = global.getURL();
                con = DriverManager.getConnection(url, global.getUSER(), global.getPASS());
                ptrs = con.prepareStatement(query);
                for (Parametro parametro : parametros) {
                    ptrs.setObject(parametro.getPosicion(), parametro.getValor());
                }
                rs = ptrs.executeQuery();
                conj.Fill(rs);
                rs.close();
                ptrs.close();
                rs = null;
                ptrs = null;
            } catch (SQLException exConec) {
                throw exConec;
            } finally {
                try {
                    if (con != null) {
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (ClassNotFoundException exCarga) {
            throw exCarga;
        }
        return conj;
    }
}
