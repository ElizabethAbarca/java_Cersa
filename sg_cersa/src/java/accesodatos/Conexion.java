/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodatos;

import java.sql.*;

/**
 *
 * @author Dev
 */
public class Conexion {

    ///establecimiento de atributos para la cadena de conexion
    private String driver;
    private String url;
    public String user;
    private String pass;
    private Connection con;
    private PreparedStatement prStm;
    private ResultSet rs;

    ///constructor de la conexion
    public Conexion() throws Exception {
        Global global = new Global();
        this.driver = global.getDRIVER();
        this.url = global.getURL();
        this.user = global.getUSER();
        this.pass = global.getPASS();
        try {
            Class.forName(this.driver);
            con = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            throw e;
        }
    }

    public String getDriver() {
        return this.driver;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String pass) {
        this.setPass(pass);
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * @return the prStm
     */
    public PreparedStatement getPrStm() {
        return prStm;
    }

    /**
     * @param prStm the prStm to set
     */
    public void setPrStm(PreparedStatement prStm) {
        this.prStm = prStm;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
