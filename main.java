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
 * @author Adria, Marc i Sergio.
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Benvingut al Gestor de Documents.");
        System.out.println("Escull una ordre. (Afegir/Esborrar/Consultar)");
        Scanner lector = new Scanner(System.in);
        String option = lector.nextLine();
        while(!option.equalsIgnoreCase("Sortir")) {
            if(option.equalsIgnoreCase("Afegir")) {
                Document d = new Document();
                System.out.println("Escriu el nom del fitxer a afegir.");
                String filename = lector.nextLine();
                
                for(int i = 1; i < 331; i++) {
                    String name = String.valueOf(i) + ".html";
                    d.set_all_data(name);
                    d.imprimir();
                }
            }
            else if(option.equalsIgnoreCase("Esborrar")) {

            }
            option = lector.nextLine();
        }
        
    /*    String demo_autor = "Duje Cop";
        String demo_titol = "Comunio";
        String demo = "Comença el partit! Goooool de Sergio pel Racing. És fora de joc, Marc?";
        
        Document d = new Document();
        d.set_titol_String(demo_titol);
        d.set_autor_String(demo_autor);
        d.set_contingut_String(demo);
        
        d.imprimir();
        
        d.get_contingut().dividir();
        
        d.get_contingut().imprimir_llista_frases(); */
   
    }
    
}
