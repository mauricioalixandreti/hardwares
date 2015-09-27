/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.jsf;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author maxpc
 */
public class Teste {
    
    public static void main (String args []){
        System.out.println("adfa");
        GregorianCalendar gr = (GregorianCalendar) GregorianCalendar.getInstance();
        gr.setGregorianChange(new Date ());
        Date d  = new Date ();
        d.getTime();
        String cpf = "08380471490";
        System.out.println(cpf.substring(0,3)+gr.getTimeInMillis()+cpf.substring(7));
//        
//        
//        String valor ="1.111,00";
//        String aux = valor.replace(".","").replace(",", ".");
//       
//        
//        Double d = Double.parseDouble(aux);
//        System.out.println(d);
        
        
      
       
        
        
        
        
    }
    
}
