/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.presentacion.charts;

import cersa.negocio.Clases.CUsuario;
import cersa.negocio.Clases.DEscama;
import cersa.negocio.Funciones.FDatosEscama;
import cersa.negocio.Funciones.FUsuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MatrizEscama {

    private List<String> rowNames = new ArrayList<String>();
    private List<String> colNames = new ArrayList<String>();
    private ArrayList<ArrayList<ArrayList<String>>> data3D = new ArrayList<ArrayList<ArrayList<String>>>();

    ArrayList<CUsuario> listaFilaUsuario;

    public MatrizEscama() {
        try {
            listaFilaUsuario = FUsuario.obtener_Rol();
            for (CUsuario fila : listaFilaUsuario) {
                rowNames.add(fila.getUsuario_nombre() + " " + fila.getUsuario_apellido());
            }

            colNames.add("SUMA(Kg)");
            colNames.add("PROMEDIO(Kg)");
            colNames.add("TONELADAS");

            // Setup 3 dimensional structure
            for (int i = 0; i < rowNames.size(); i++) {
                data3D.add(new ArrayList<ArrayList<String>>());
                for (int j = 0; j < colNames.size(); j++) {
                    data3D.get(i).add(new ArrayList<String>());
                }
            }
            ArrayList<CUsuario> listaUsuario = FUsuario.obtener_Rol();
            ArrayList<DEscama> listaEscama = null;
            int m =0;
            for (CUsuario usuario : listaUsuario) {
                int j=0;
                double suma=0;
                double promedio =0;
                listaEscama = FDatosEscama.obtenerSuma_Escama_Persona(usuario.getUsuario_id());
                for (DEscama escama : listaEscama) {
                   suma = suma + escama.getEpeso();
                }
                promedio = suma/listaEscama.size();
                data3D.get(m).get(0).add(String.valueOf(suma));                
                data3D.get(m).get(1).add(String.valueOf(promedio));                          
                data3D.get(m).get(2).add(String.valueOf(promedio/1000));
                m++;
            }
            
            
            
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }

    }

    public List<String> getRowNames() {
        return rowNames;
    }

    public void setRowNames(List<String> rowNames) {
        this.rowNames = rowNames;
    }

    public List<String> getColNames() {
        return colNames;
    }

    public void setColNames(List<String> colNames) {
        this.colNames = colNames;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getData3D() {
        return data3D;
    }

    public void setData3D(ArrayList<ArrayList<ArrayList<String>>> data3D) {
        this.data3D = data3D;
    }

    public ArrayList<CUsuario> getListaFilaUsuario() {
        return listaFilaUsuario;
    }

    public void setListaFilaUsuario(ArrayList<CUsuario> listaFilaUsuario) {
        this.listaFilaUsuario = listaFilaUsuario;
    }

}
