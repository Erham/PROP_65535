/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
        
        System.out.println("Benvingut al Gestor de Documents. ('Sortir' per acabar)");
        System.out.println("Escull una ordre. (Afegir/Esborrar/Modificar/Imprimir/Consultar)");
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
                if(cjt.get_cjt().isEmpty()) System.out.println("ERROR: El conjunt de documents és buit.");
                else {
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
            }
            else if(option.equalsIgnoreCase("Modificar")) {
                if(cjt.get_cjt().isEmpty()) System.out.println("ERROR: El conjunt de documents és buit.");
                else {
                    System.out.println("Escull opció de modificació (ID/Nom).");
                    String opt = lector.nextLine();
                    if(opt.equalsIgnoreCase("ID")) {
                        System.out.println("Introdueix l'ID del document a modificar.");
                        int num = lector.nextInt();
                        cjt.modificar(num);
                    }
                    else if(opt.equalsIgnoreCase("Nom")) {
                        System.out.println("Introdueix el títol i l'autor del document a modificar.");
                        String tit = lector.nextLine();
                        String aut = lector.nextLine();
                        cjt.modificarDoc(tit, aut);
                    }
                    else System.out.println("ERROR: Comanda incorrecta.");
                }
            }
            else if(option.equalsIgnoreCase("Imprimir")) {
                System.out.println("Introdueix l'ID del document a imprimir.");
                int num = lector.nextInt();
                if(cjt.existeix_id(num)) {
                    Map<Integer, Document> c = cjt.get_cjt();
                    c.get(num).imprimir();
                }
                else System.out.println("ERROR: El document no existeix.");
            }
            else if(option.equalsIgnoreCase("Consultar")) {
                if(cjt.get_cjt().isEmpty()) System.out.println("ERROR: El conjunt de documents és buit.");
                else {    
                    System.out.println("Selecciona el tipus de consulta (per nº):");
                    System.out.println("1. Buscar títols d'un autor");
                    System.out.println("2. Buscar autors per prefix");
                    System.out.println("3. Buscar contingut per títol i autor");
                    System.out.println("4. Buscar K documents més semblants a D");

                    String num = lector.nextLine();
                    int opt = Integer.parseInt(num);

                    switch(opt) {
                        case 1:
                            System.out.println("Introdueix el nom de l'autor:");
                            String nombre = lector.nextLine();
                            cjt.cerca_Autor(nombre);
                            System.out.println("Operació realitzada amb èxit.");
                            break;
                        case 2:
                            System.out.println("Introdueix el prefix per buscar:");
                            String prefix = lector.nextLine();
                            cjt.cerca_Prefix(prefix);
                            System.out.println("Operació realitzada amb èxit.");
                            break;                        
                        case 3:
                            System.out.println("Introdueix el titol i l'autor del document a buscar:");
                            String tt = lector.nextLine();
                            String at = lector.nextLine();
                            cjt.cerca_Document(tt, at);
                            System.out.println("Operació realitzada amb èxit.");                            
                            break;
                        case 4:
                            System.out.println("Escull entre atributs o ID:");
                            String opc = lector.nextLine();
                            if(opc.equalsIgnoreCase("id")) {
                                System.out.println("Introdueix l'ID del document:");
                                int ide = lector.nextInt();
                                System.out.println("Quants documents semblants vols?");
                                int k = lector.nextInt();
                                if(cjt.get_cjt().get(ide) == null) {
                                    System.out.println("ERROR: El document no existeix.");
                                    break;
                                }
                                Document d = cjt.get_cjt().get(ide);
                                cjt.cerca_Semblants(d, k);
                                System.out.println("Operació realitzada correctament.");
                            }
                            else if(opc.equalsIgnoreCase("atributs")) {
                                System.out.println("Introdueix el títol i l'autor del document:");
                                String titulo = lector.nextLine();
                                String autoria = lector.nextLine();
                                Document doc = new Document();
                                for(Document d : cjt.get_cjt().values()) {
                                    if(d.get_titol().get_frasestring().equalsIgnoreCase(titulo)) {
                                        if(d.get_autor().get_frasestring().equalsIgnoreCase(autoria)) {
                                            doc = d;
                                        }
                                    }
                                }
                                
                                System.out.println("Quants documents semblants vols?");
                                int k = lector.nextInt();           
                                
                                cjt.cerca_Semblants(doc, k);
                                System.out.println("Operació realitzada correctament.");
                            }
                            else System.out.println("ERROR: Comanda incorrecta.");
                            break;
                        default: System.out.println("ERROR: Ha de ser un nº entre 1 i 4!");
                            break;
                    }
                }
            }
            option = lector.nextLine();
        }
   
    }
    
}
