/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MARC
 */
public class Main_Text {
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
   
        int num;
        System.out.println("Classe Text");
        while(true){                 
        
            System.out.println("Introdueix un nou text");
            Scanner lector = new Scanner(System.in); 
            Text t = new Text();
            String option = lector.nextLine();
            t.set_textstring(option);
            
            System.out.println("Escriu un numero del 1 al 5.");
            System.out.println("1. Afegir una frase al text");
            System.out.println("2. Funcio set i imprimir");
            System.out.println("3. Dividir i imprimir les frases del text");
            System.out.println("4. Mida del text sense stop words");
            System.out.println("5. Funcio get i imprimir una llista");
            
            String opt = lector.nextLine();
            
            num = Integer.parseInt(opt);
            
            switch(num)
            {
                case 1: Frase f = new Frase();
                        System.out.println("Introdueix una frase");
                        String opt2 = lector.nextLine();
                        f.set_frasestring(opt2);
                        t.afegir(f);
                        t.imprimir(); break;
                        
                case 2: t.set_textstring(option);
                        t.imprimir(); break;
                        
                case 3: t.dividir();
                        t.imprimir_llista_frases(); break;
                        
                case 4: t.set_textstring(option);
                        int len;
                        len = t.count();
                        System.out.println(len); break;
                        
                case 5: List<Frase> ls;
                        ls = new ArrayList();
                        t.dividir();
                        ls = t.get_lf();
                        for(Frase fr : ls) {
                            fr.imprimir();
                        }break;                 
                default: System.out.println("Error: Els numeros han de ser entre 1 i 5"); break;
            }
        }       
    }    
}
