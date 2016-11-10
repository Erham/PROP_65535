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
public class main_paraula {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Classe Paraula");
        System.out.println("Introdueix una paraula");
        Scanner lector = new Scanner(System.in);
        String option = lector.nextLine();
        
        Paraula p = new Paraula();
        p.set_p(option);
        String s = p.get_p();
        System.out.println("La funcio get_p dona al string s el valor de la Paraula p");
        System.out.println(s);
        System.out.println("La funcio imprimeix, mostra per pantalla la Paraula p");
        p.imprimir();
        
        System.out.println("Comprovarem si una paraula es una stop word o no");
        String opt = "";
        
        while(!opt.equalsIgnoreCase("Sortir"))
        {
            Paraula pa = new Paraula();
            System.out.println("Introdueix una nova paraula");
            opt = lector.nextLine();
            pa.set_p(opt);
            if(pa.is_stop_word()) System.out.println(pa.get_p() + " es una stop word");
            else System.out.println(pa.get_p() + " no es una stop word");
            
        
        }
        
    }
    
}
