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
        Cjt_Documents cjt = new Cjt_Documents();
        Scanner lector = new Scanner(System.in);
        String option = lector.nextLine();
        while(!option.equalsIgnoreCase("Sortir")) {
            if(option.equalsIgnoreCase("Afegir")) {
                Document d = new Document();
                System.out.println("Escriu el nom del fitxer a afegir.");
                String filename = lector.nextLine();
                
                d.set_all_data(filename);
                cjt.afegir(d);
            }
            else if(option.equalsIgnoreCase("Esborrar")) {
                System.out.println("Escull opció d'esborrat (ID/Nom).");
                String opt = lector.nextLine();
                if(opt.equalsIgnoreCase("ID")) {
                    System.out.println("Introdueix l'ID del document a esborrar.");
                    int num = lector.nextInt();
                    cjt.esborrar(num);
                }
                else if(opt.equalsIgnoreCase("Nom")) {
                    System.out.println("Introdueix el títol i l'autor del document a esborrar.");
                    String tit = lector.nextLine();
                    String aut = lector.nextLine();
                    cjt.esborrarDoc(tit, aut);
                }
                else System.out.println("ERROR: Comanda incorrecta.");
            }
            else if(option.equalsIgnoreCase("Consultar")) {
                
            }
            else System.out.println("ERROR: Comanda incorrecta.");
            option = lector.nextLine();
        }
   
    }
    
}
