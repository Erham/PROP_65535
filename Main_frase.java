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
public class Main_frase {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Classe Frase");
        System.out.println("Introdueix una frase");
        Scanner lector = new Scanner(System.in); 
        Frase f = new Frase();
        String option = lector.nextLine();
        
        System.out.println("Utilitzem el set per afegir a la frase l'string");
        f.set_frasestring(option);
        System.out.println("Mostrem la frase amb la funció imprimir");
        f.imprimir();
        System.out.println("Dividim la frase en paraules i la mostrem");
        f.dividir();
        f.imprimir_llista_paraules();
        
        List<Paraula> ls;
        ls = new ArrayList();
        ls = f.get_lp();
        //f.set_lpar(ls);
        System.out.println("Provem el get i el set de la llista i el mostrem");
        for(Paraula pa : ls) {
            pa.imprimir();
        }
        
        System.out.println("Convertim les paraules de la frase en string");
        Frase fr = new Frase();
        
        Paraula p = new Paraula();
        System.out.println("Introdueix una paraula");
        String opt = lector.nextLine();
        p.set_p(opt);
        //System.out.println("Creem una frase a partir d'una paraula");
        //Frase fr = new Frase();
        System.out.println("Afegim una paraula a la frase: ");
        f.afegir(p);
        f.imprimir();
        
        System.out.println("Retornem el nombre de paraules de la frase");
        int len = f.nombre_paraules();
        System.out.println(len);
    
        
        
        
    }
}