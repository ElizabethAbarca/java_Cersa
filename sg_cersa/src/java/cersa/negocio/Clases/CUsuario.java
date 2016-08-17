/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.negocio.Clases;

/**
 *
 * @author HP
 */
public class CUsuario {
  private Integer usuario_id;
  private String usuario_cedula;
  private String usuario_nombre;
  private String usuario_apellido;
  private String usuario_direccion;
  private String usuario_email;
  private String usuario_celular;
  private String usuario_telefono;
  private String usuario_clave;
  private Integer usuario_estado;
  CRol usuario_rol;

    public CUsuario() {
    }

    public CUsuario(Integer usuario_id, String usuario_cedula, String usuario_nombre, String usuario_apellido, String usuario_direccion, String usuario_email, String usuario_celular, String usuario_telefono, String usuario_clave, Integer usuario_estado, CRol usuario_rol) {
        this.usuario_id = usuario_id;
        this.usuario_cedula = usuario_cedula;
        this.usuario_nombre = usuario_nombre;
        this.usuario_apellido = usuario_apellido;
        this.usuario_direccion = usuario_direccion;
        this.usuario_email = usuario_email;
        this.usuario_celular = usuario_celular;
        this.usuario_telefono = usuario_telefono;
        this.usuario_clave = usuario_clave;
        this.usuario_estado = usuario_estado;
        this.usuario_rol = usuario_rol;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_cedula() {
        return usuario_cedula;
    }

    public void setUsuario_cedula(String usuario_cedula) {
        this.usuario_cedula = usuario_cedula;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public String getUsuario_apellido() {
        return usuario_apellido;
    }

    public void setUsuario_apellido(String usuario_apellido) {
        this.usuario_apellido = usuario_apellido;
    }

    public String getUsuario_direccion() {
        return usuario_direccion;
    }

    public void setUsuario_direccion(String usuario_direccion) {
        this.usuario_direccion = usuario_direccion;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getUsuario_celular() {
        return usuario_celular;
    }

    public void setUsuario_celular(String usuario_celular) {
        this.usuario_celular = usuario_celular;
    }

    public String getUsuario_telefono() {
        return usuario_telefono;
    }

    public void setUsuario_telefono(String usuario_telefono) {
        this.usuario_telefono = usuario_telefono;
    }

    public String getUsuario_clave() {
        return usuario_clave;
    }

    public void setUsuario_clave(String usuario_clave) {
        this.usuario_clave = usuario_clave;
    }

    public Integer getUsuario_estado() {
        return usuario_estado;
    }

    public void setUsuario_estado(Integer usuario_estado) {
        this.usuario_estado = usuario_estado;
    }

    public CRol getUsuario_rol() {
        return usuario_rol;
    }

    public void setUsuario_rol(CRol usuario_rol) {
        this.usuario_rol = usuario_rol;
    }
}
