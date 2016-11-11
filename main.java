/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

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
        
        System.out.println("Driver de la col·lecció de Documents. ('Sortir' per acabar)");
        System.out.println("Escull una ordre. (Afegir/Esborrar/Modificar/Imprimir/Consultar/Altres)");
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
                            System.out.println("--Operació realitzada amb èxit--");                            
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
                                System.out.println("--Operació realitzada amb èxit--");
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
                            }
                            else System.out.println("ERROR: Comanda incorrecta.");
                            break;
                        default: System.out.println("ERROR: Ha de ser un nº entre 1 i 4!");
                            break;
                    }
                }
            }
            else if(option.equalsIgnoreCase("Altres")) {
                System.out.println("Funció per provar getters i setters.");
                System.out.println("Escull una opció de l'1 al 6:");
                System.out.println("1) get_cjt()");
                System.out.println("2) get_maxid()");
                System.out.println("3) set_cjt()");
                System.out.println("4) set_maxid()");
                System.out.println("5) Obtenir un conjunt a partir d'un registre");
                System.out.println("6) Obtenir el tamany del conjunt");
                
                int cop = lector.nextInt();
                switch(cop) {
                    case 1:
                        if(cjt.get_cjt().isEmpty()) System.out.println("Aquest és el conjunt demanat, però és buit!");
                        else System.out.println("Aquest és el conjunt demanat, té tamany " + cjt.get_cjt().size());
                        break;
                    case 2:
                        System.out.println("L'ID més gran al Conjunt de Documents és " + cjt.get_maxid());
                        break;
                    case 3:
                        System.out.println("Sobreescriure l'anterior conjunt?");
                        String respuesta = lector.nextLine();
                        Map<Integer, Document> cjt2 = new HashMap();
                        if(respuesta.equalsIgnoreCase("si")) cjt.set_cjt(cjt2);
                        System.out.println("--Operació realitzada amb èxit--");
                        break;
                    case 4:
                        System.out.println("Introdeix un nou ID.");
                        int nouid = lector.nextInt();
                        cjt.set_maxid(nouid);
                        System.out.println("--Operació realitzada amb èxit--");
                        break;
                    case 5:
                        System.out.println("Introdueix id, titol, autor i tema, SEGUITS I SEPARATS PER DOS ESPAIS.");
                        Cjt_Documents con = new Cjt_Documents();
                        Scanner lector2 = new Scanner(System.in);
                        String docu = lector2.nextLine();
                        con.omplir_cjt(docu);
                        Map<Integer, Document> m = con.get_cjt();
                        for(Map.Entry<Integer, Document> entry : m.entrySet()) {
                            System.out.println("ID: " + entry.getKey());
                            System.out.print("Títol: ");
                            System.out.print(entry.getValue().get_titol().get_frasestring() + '\n');
                            System.out.print("Autor: ");
                            System.out.print(entry.getValue().get_autor().get_frasestring() + '\n');
                            System.out.print("Tema: ");
                            System.out.print(entry.getValue().get_tema().get_p() + '\n');
                        }
                        break;
                    case 6:
                        System.out.println(cjt.tamany());
                        break;
                    default: System.out.println("ERROR: Ha de ser un nº entre 1 i 6!");
                }
            }
            
            System.out.println("Escull una ordre. (Afegir/Esborrar/Modificar/Imprimir/Consultar/Altres/Sortir)");
            option = lector.nextLine();
        }
   
    }
    
}
