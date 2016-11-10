/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 1195733
 */
public class main_frase {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
   
        int num;
        System.out.println("Classe Frase");
        while(true){                 
        
            System.out.println("Introdueix una nova frase");
            Scanner lector = new Scanner(System.in); 
            Frase f = new Frase();
            String option = lector.nextLine();
            f.set_frasestring(option);
            
            System.out.println("Escriu un numero del 1 al 5.");
            System.out.println("1. Afegeix una paraula a la frase");
            System.out.println("2. Funcio set i imprimir");
            System.out.println("3. Dividir la frase en paraules i imprimir llista");
            System.out.println("4. Retornem la longitud de la frase");
            System.out.println("5. Funcio get i imprimir en llista");
            
            String opt = lector.nextLine();
            
            num = Integer.parseInt(opt);
            
            switch(num)
            {
                case 1: Paraula p = new Paraula();
                        System.out.println("Introdueix una paraula");
                        String opt2 = lector.nextLine();
                        p.set_p(opt2);
                        f.afegir(p);
                        f.imprimir(); break;
                        
                case 2: f.set_frasestring(option);
                        f.imprimir(); break;
                        
                case 3: f.dividir();
                        f.imprimir_llista_paraules(); break;
                        
                case 4: f.dividir();
                        int len = f.longitud();
                        System.out.println(len); break;
                        
                case 5: List<Paraula> ls;
                        ls = new ArrayList();
                        f.dividir();
                        ls = f.get_lp();
                        for(Paraula pa : ls) {
                            pa.imprimir();
                        }break;                   
                
                default: System.out.println("Error: Els numeros han de ser entre 1 i 5."); break;

            }
        }
        
        
        
        
        
    }
}