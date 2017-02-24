/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.validacion;

import java.util.Calendar;

/**
 *
 * @author RitaElizabeth
 */
public class validacion {
     public static String actualDate() {
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String nombre = null;
        switch (mes) {
            case 1:
                nombre = "Enero";
                break;
            case 2:
                nombre = "Febrero";
                break;
            case 3:
                nombre = "Marzo";
                break;
            case 4:
                nombre = "Abril";
                break;
            case 5:
                nombre = "Mayo";
                break;
            case 6:
                nombre = "Junio";
                break;
            case 7:
                nombre = "Julio";
                break;
            case 8:
                nombre = "Agosto";
                break;
            case 9:
                nombre = "Septiembre";
                break;
            case 10:
                nombre = "Octubre";
                break;
            case 11:
                nombre = "Noviembre";
                break;
            case 12:
                nombre = "Diciembre";
                break;
        }

        return (nombre + "-" + dia + "-" + año);
    }
    
    public static String retirarGuion(String valor)
    {
        String resultado="";
        for(int i=0; i< valor.length();i++){
            if(valor.charAt(i)=='-')
                i++;
            resultado=resultado+valor.charAt(i);
        }
        return resultado;
    }

    public static Boolean validacionCedula(String ci) {
        //verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        String cedula= retirarGuion(ci);
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= 24))) {
            //addError("La cédula ingresada no es válida");
            System.out.println("Error: cedula ingresada mal");
            return false;
        }

        //verifica que el último dígito de la cédula sea válido
        int[] d = new int[10];
        //Asignamos el string a un array
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }

        int imp = 0;
        int par = 0;

        //sumamos los duplos de posición impar
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }

        //sumamos los digitos de posición par
        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }

        //Sumamos los dos resultados
        int suma = imp + par;

        //Restamos de la decena superior
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1)
                + "0") - suma;

        //Si es diez el décimo dígito es cero        
        d10 = (d10 == 10) ? 0 : d10;

        //si el décimo dígito calculado es igual al digitado la cédula es correcta
        if (d10 == d[9]) {
            return true;
        } else {
            //addError("La cédula ingresada no es válida");
            return false;
        }
    }
}
