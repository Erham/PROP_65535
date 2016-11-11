package fitxers;

import static fitxers.Fitxer.path;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException {
    try {
  
    Scanner in = new Scanner(System.in);
    String o1;
        
    System.out.println("Benvingut al driver per provar les seguents classes:\n"
    + "Fitxer - Classe que s'encarrega de la gestio dels fitxers en disc.\n"
    + "Arxiu - Subclasse de Fitxer. Fa la gestio dels fitxers que corresponen a un document en concret.\n"
    + "Registre - Subclasse de Fitxer. Guarda les dades dels atributs de tots els documents\n"
    + "Espai_vectorial - Subclasse de Fitxer. Guarda les dades de la frequencia d'aparicio de cada una de les paraules en el conjunt de documents\n"
    + "Comparador - Serveix per comparar dos documents (en forma de vector) i saber-ne la proximitat\n"
    + "(La classe Vector es prova de forma implicita a traves de Espai_vectorial i Comparador)");
    
    int num = 0;
    
    //  === === WHILE GENERAL === === 
    // ========================================
    while (num < 5) {
        
        System.out.println("Escull la classe a provar.\n"
        + "Introdueix un dels enters seguents per escollir la classe corresponent o tancar el driver:");
    
        System.out.println(""
            + "1. Fitxer\n"
            + "2. Arxiu\n"
            + "3. Registre\n"
            + "4. Espai_vectorial\n"
            + "5. Comparador (de vectors)\n"
            + "6+. Tancar el driver");
        
        o1 = in.nextLine();
        num = Integer.parseInt(o1);
        
        if (num == 1) {
            System.out.println("1. Classe Fitxer");
        // ========================================
            // CLASSE FITXER
            
            int num1 = 0;
            String o;
            
            while (num1 < 6) {
                
                System.out.println("Escull: (introdueix un d'aquests enters positius)");
                System.out.println(""
                        + "1. Crear fitxer txt\n"
                        + "2. Llegir un fitxer\n"
                        + "3. Modificar el contingut d'un fitxer\n"
                        + "4. Buidar el contingut d'un fitxer\n"
                        + "5. Eliminar un fitxer\n"
                        + "6+. Tancar");
            
                o = in.nextLine();
                num1 = Integer.parseInt(o);
                Fitxer f = new Fitxer();
            
                if (num1 == 1) {
                    System.out.println("1.introdueix el nom:");
                    o = in.nextLine();
                    File a = new File(path(),o + ".txt");
                    f.crear(a);
                    if (f.creat()) {System.out.println("El Fitxer es present al sistema de fitxers");}
                    if (a.exists()) System.out.println("creat\n");
                    else System.out.println("no s'ha pogut crear\n");
                }
                else if (num1 == 2) {
                    System.out.println("2.introdueix el nom:");
                    o = in.nextLine();
                    File a = new File(path(),o + ".txt");
                    if (a.exists()) {
                    f.presencia = true;
                    f.set_nom(o);
                    f.llegir();
                    System.out.println("Contingut:\n"
                        + f.get_text());
                    }
                    else System.out.println("el fitxer no existeix\n");
                }
                else if (num1 == 3) {                        
                    System.out.println("3.introdueix el nom:");
                            o = in.nextLine();
                            File a = new File(path(),o + ".txt");
                            if (a.exists()) {
                                f.set_nom(o);
                                System.out.println("introdueix el contingut:");
                                o = in.nextLine();
                                f.set_text(o);
                                if (f.modificat()) {System.out.println("El Fitxer s'ha modificat");}
                                f.guardar();
                                System.out.println("Contingut canviat\n");
                            }
                            else System.out.println("el fitxer no existeix\n");
                }
                else if (num1 == 4) {
                    System.out.println("4.introdueix el nom:");
                            o = in.nextLine();
                            File a = new File(path(),o + ".txt");
                            if (a.exists()) {
                                f.set_nom(o);
                                f.buidar_text();
                                f.guardar();
                                System.out.println("Contingut buidat (esborrat)\n");
                            }
                            else System.out.println("el fitxer no existeix\n");
                }
                else if (num1 == 5) {
                    System.out.println("5.introdueix el nom:");
                            o = in.nextLine();
                                f.set_nom(o);
                                f.eliminar();
                                System.out.println("");
                }
                else num1 = 99;
            }
        }
            
        
            // FI CLASSE FITXER
        // ========================================
        else if (num == 2) {
            System.out.println("2. Classe Arxiu");
        // ========================================
            // CLASSE ARXIU
            
            int num1 = 0;
            String o;
            
            while (num1 < 4) {
                System.out.println("Escull: (introdueix un d'aquests enters positius)");
                System.out.println(""
                        + "1. Crear una instancia d'Arxiu i assignar els atributs\n"
                        + "2. Crear un Arxiu amb els atributs introduits. Guardar-lo al disc\n"
                        + "3. Llegir el contingut d'un Arxiu (donat un identificador) creat al apartat 2 (Ha d'estar creat abans per escollir aquesta opcio)\n"
                        + "4+. Tancar");
                
                o = in.nextLine();
                num1 = Integer.parseInt(o);
                
                if (num1 == 1) {
                
                    Arxiu a = new Arxiu();
                    
                System.out.println("1. Introdueix l'identificador (Ha de ser un enter positiu");
                o = in.nextLine();
                a.set_id(Integer.parseInt(o));
                
                System.out.println("Introdueix el titol (en format String)");
                a.set_titol(in.nextLine());
                
                System.out.println("Introdueix l'autor (en format String)");
                a.set_autor(in.nextLine());
                
                System.out.println("Introdueix el tema (en format String)");
                a.set_tema(in.nextLine());
                
                System.out.println("Introdueix el contingut (en format String)");
                a.set_text(in.nextLine());
                
                System.out.println("Arxiu instanciat amb els seguents atributs:");
                System.out.println("id: " + Integer.toString(a.get_id()));
                System.out.println("titol: " + a.get_titol());
                System.out.println("autor: " + a.get_autor());
                System.out.println("tema: " + a.get_tema());
                System.out.println("text: " + a.get_text());
                System.out.println("\n");
                
                }
                else if (num1 == 2) {
                
                    System.out.println("1. Introdueix l'identificador (Ha de ser un enter positiu. No hi pot haver cap fitxer .txt que tingui per nom aquest identificador");
                o = in.nextLine();
                int id = Integer.parseInt(o);
                
                System.out.println("Introdueix el titol (en format String)");
                String titol = in.nextLine();
                
                System.out.println("Introdueix l'autor (en format String)");
                String autor = in.nextLine();
                
                System.out.println("Introdueix el tema (en format String)");
                String tema = in.nextLine();
                
                System.out.println("Introdueix el contingut: (en format String. Sera el que es guardara al disc i es podra consultar al apartat 3)");
                String text = in.nextLine();
                
                Arxiu a  = new Arxiu(id,titol,autor,tema);
                
                a.set_text(text);
                a.guardar();
                System.out.println("Arxiu creat (i contingut guardat al disc) amb els seguents atributs:");
                System.out.println("id: " + Integer.toString(a.get_id()));
                System.out.println("titol: " + a.get_titol());
                System.out.println("autor: " + a.get_autor());
                System.out.println("tema: " + a.get_tema());
                System.out.println("text: " + a.get_text());
                    
                       
                }
                else if(num1 == 3) {
                    
                    
                    System.out.println("3. Introdueix l'identificador de l'arxiu que has creat:");
                o = in.nextLine();
                int id = Integer.parseInt(o);
                Arxiu a = new Arxiu(id);
                System.out.println("Contingut:");
                System.out.println(a.get_text());
                
                    
                }
                else num1 = 99;
                
            }
            
            
            // FI CLASSE ARXIU
        // ========================================
        }
        else if (num == 3) {
            System.out.println("2. Classe Registre");
        // ========================================
            // CLASSE REGISTRE
            
            int num1 = 0;
            String o;
            
            while (num1 < 3) {
                
                System.out.println("Escull: (introdueix un d'aquests enters positius)");
                System.out.println(""
                        + "1. Crear (si cal) i instanciar el Registre\n"
                        + "2. Afegir una entrada d'un arxiu al Registre (cal que estigui instanciat)\n"
                        + "3+. Tancar");
                
                o = in.nextLine();
                num1 = Integer.parseInt(o);
                
                
               if (num1 == 1) {
                   
                   Registre a = new Registre();
                   System.out.println("Registre instanciat i creat si no era present");
                
                }
                else if (num1 == 2) {
                    Registre a = new Registre();
                    
                    a.llegir();
                    
                    Arxiu nou = new Arxiu();
                    System.out.println("Introdueix els atributs de l'arxiu:");
                    System.out.println("Identificador (enter positiu)");
                    nou.set_id(Integer.parseInt(in.nextLine()));
                    System.out.println("titol (format string)");
                    nou.set_titol(in.nextLine());
                    System.out.println("autor (format string)");
                    nou.set_autor(in.nextLine());
                    System.out.println("tema (format string)");
                    nou.set_tema(in.nextLine());
                    
                    a.afegir_arxiu(nou);
                    
                    a.guardar();
                    
                    System.out.println("Registre ha quedat així:");
                    System.out.println(a.get_text());
                    
                    
                    
                }
                else num1 = 99;
                
                
            }
                
            // FI CLASSE REGISTRE
        // ========================================
        }
        else if (num == 4) {
            System.out.println("4. Classe Espai_vectorial");
        // ========================================
            // CLASSE ESPAI_VECTORIAL
            
            int num1 = 0;
            String o;
            
            Espai_vectorial b = new Espai_vectorial();
            
            
            while (num1 < 6) {
                
                System.out.println("Escull: (introdueix un d'aquests enters positius)");
                System.out.println(""
                    + "1. Assignar la dimensio a Espai_vectorial\n"
                    + "2. Omplir Espai_vectorial amb un conjunt de paraules per defecte (cal que estigui instanciat) i guardar-ho al disc\n"
                    + "3. Afegir/Treure paraules d'un document a Espai_vectorial\n"
                    + "4. Buidar Espai_vectorial de paraules (extreure'n totes les paraules)\n"
                    + "5. Calcular el vector que correspon al conjunt de paraules d'un document. "
                    + "Permet escollir el mode, boolea (si una determinada paraula apareix o no) "
                    + "o enter(quants cops apareix una determinada paraula) pel calcul de tots els seus elements)\n"
                    + "6+. Tancar");
                
                o = in.nextLine();
                num1 = Integer.parseInt(o);
                
                
                System.out.println("Espai_vectorial ha estat instanciat i creat si encara no ho estava");
                
                
                if (num1 == 1) {
                    
                    b.set_dimensio(20);
                    System.out.println("La dimensio de Espai_vectorial s'ha assignat amb valor " + b.get_dimensio());
                    
                }
                else if (num1 == 2) {
                    
                    
                    b.esborrar_map();
                    
                    // mes o menys aleatori, per veure els calculs dels vectors
                    b.set_text("hola 15 adeu 63 dia 23 nit 786 sol 43 peu 653 sou 23 adria 6 "
                            + "sergio 7 marc 2 pou 75 gos 4 gat 7 home 86 dona 35 duje 2 cop 8 "
                            + "galet 799 farina 32 oreo 46 tambor 9 pau 64 rata 97 sang 9 blanc 5 "
                            + "tanc 2 manc 7 estanc 86 segell 97 arbre 468 branca 537 foc 23 gel 46");
                    
                    b.omplir_map();
                    
                    System.out.println("L'espai vectorial s'ha omplert amb un conjunt de paraules de prova");
                    System.out.println("L'espai vectorial ha quedat aixi: (en format del map<paraula,frequencia d'aparicio>)");
                    b.imprimir_map();
                    b.omplir_list();
                    System.out.println("La llista de frequencia de paraules ordenada ha quedat aixi:");
                    b.imprimir_list();
                    b.guardar();
                    System.out.println("Els canvis en l'Espai_vectorial s'han guardat al disc");
                
                }
                else if (num1 == 3) {
                    
                    
                    System.out.println("Vols afegir o extreure ? (escriu a/x per escollir)");
                    
                    o = in.nextLine();
                    
                    if (o.equals("a")) {
                        
                        System.out.println("Introdueix les paraules del document a afegir:\n"
                                + "Format: [paraula1 paraula2 ... paraula-n]");
                        
                        o = in.nextLine();
                        
                        b.afegir_al_espai(o);
                        
                        System.out.println("afegides");
                        b.omplir_list();
                        System.out.println("La llista ha quedat aixi:");
                        b.imprimir_list();
                        b.guardar();
                        System.out.println("canvis guardats al disc");
                        
                        
                    }
                    else if ((o.equals("x"))) {
                        
                        System.out.println("Introdueix les paraules del document a extreure:\n"
                                + "Format: [paraula1 paraula2 ... paraula-n]");
                        
                        o = in.nextLine();
                        
                        b.extreure_del_espai(o);
                        
                        System.out.println("extretes");
                        b.omplir_list();
                        System.out.println("La llista ha quedat aixi:");
                        b.imprimir_list();
                        b.guardar();
                        System.out.println("canvis guardats al disc");
                        
                    }
                    else {
                        System.out.println("comanda erronia");
                    }
                    
                    
                
                }
                else if (num1 == 4) {
                
                    b.esborrar_map();
                    System.out.println("paraules del espai vectorial esborrades");
                    System.out.println("L'espai ha quedat aixi:");
                    b.imprimir_map();
                    
                }
                else if (num1 == 5) {
                    
                    
                    
                    b.esborrar_map();
                    
                    // mes o menys aleatori, per veure els calculs dels vectors
                    b.set_text("hola 15 adeu 63 dia 23 nit 786 sol 43 peu 653 sou 23 adria 6 "
                            + "sergio 7 marc 2 pou 75 gos 4 gat 7 home 86 dona 35 duje 2 cop 8 "
                            + "galet 799 farina 32 oreo 46 tambor 9 pau 64 rata 97 sang 9 blanc 5 "
                            + "tanc 2 manc 7 estanc 86 segell 97 arbre 468 branca 537 foc 23 gel 46");
                    
                    b.omplir_map();
                    b.omplir_list();
                    
                    
                    
                    System.out.println("Introdueix les paraules del vector: (p1 p2 p3 ... p-n)");
                    System.out.println("les paraules rellevants son:\n"
                            + "hola adeu dia nit sol peu sou adria\n"
                            + "sergio marc pou gos gat home dona duje cop\n"
                            + "galet farina oreo tambor pau rata sang blanc\n"
                            + "tanc manc estanc segell arbre branca foc gel");
                    
                    b.imprimir_list();
                    
                    o = in.nextLine();
                    
                    System.out.println("Escull el mode boolea o enter (Escriu b/e per escollir)");
                    String opc = in.nextLine();
                    
                    Vector vec;
                    
                    if (opc.equals("b")) {
                        
                        // System.out.println(o);
                        
                        vec = b.vector_boolea(o);
                        
                        System.out.println("El vector es:\n"
                                + Arrays.toString(vec.get_elements()));
                        
                    }
                    else if (opc.equals("e")) {
                        
                        vec = b.vector_enter(o);
                        System.out.println("El vector es:\n"
                                + Arrays.toString(vec.get_elements()));
                        
                    }
                    else {
                        System.out.println("Comanda erronia");
                    }
                    
                    
                }
                else {
                num1 = 99;
                b.eliminar();
                }
                
            }
            
            // FI CLASSE ESPAI_VECTORIAL
        // ========================================
        }
        else if (num == 5) {
            System.out.println("5. Classe Comparador");

            
            int num1 = 0;
            String o;
            
            Espai_vectorial b = new Espai_vectorial();
            
            b.esborrar_map();
            
            b.set_dimensio(20);
            
            b.set_text("hola 15 adeu 63 dia 23 nit 786 sol 43 peu 653 sou 23 adria 6 "
                            + "sergio 7 marc 2 pou 75 gos 4 gat 7 home 86 dona 35 duje 2 cop 8 "
                            + "galet 799 farina 32 oreo 46 tambor 9 pau 64 rata 97 sang 9 blanc 5 "
                            + "tanc 2 manc 7 estanc 86 segell 97 arbre 468 branca 537 foc 23 gel 46");
            
            b.omplir_map();
            b.omplir_list();
            
            
            
            
            
            
            Comparador comp = new Comparador();
            
            while (num1 < 4) {
                
                System.out.println("Escull: (introdueix un d'aquests enters positius)");
                System.out.println(""
                        + "1. Assignar un vector boolea per la comparacio\n"
                        + "2. Assignar un vector enter per la comparacio\n"
                        + "3. Calcular la distancia/cosinus entre el vector assignat (en els apartats 1 o 2) i un altre que s'entra aqui dins\n"
                        + "4+. Tancar");
                
                o = in.nextLine();
                num1 = Integer.parseInt(o);
                
                if (num1 == 1) {
                    
                    System.out.println("assignar vector boolea");
                    System.out.println("Introdueix les paraules del vector [p1 p2 p3 ... p-n]");

                    System.out.println("les paraules mes rellevants son:");
                    b.imprimir_list();
                    
                    o = in.nextLine();
                    
                    comp.assignar_vector_boolea(b, o);
                    
                    System.out.println("vector assignat:\n"
                            + Arrays.toString(comp.get_vec().get_elements()));
                    
                    
                }
                else if (num1 == 2) {
                    
                    System.out.println("assignar vector enter");
                    System.out.println("Introdueix les paraules del vector [p1 p2 p3 ... p-n]");

                    System.out.println("les paraules mes rellevants son:");
                    b.imprimir_list();
                    
                    o = in.nextLine();
                    
                    comp.assignar_vector_enter(b, o);
                    
                    System.out.println("vector assignat:\n"
                            + Arrays.toString(comp.get_vec().get_elements()));
                    
                    
                    
                }
                else if (num1 == 3) {
                    System.out.println("Calcular distancia/cosinus\n"
                            + "Escull opcio (escriu un numero natural del 1 al 3 per escollir)\n"
                            + "(La opcio ha de ser coherent amb el tipus de vector que hi hagi assignat al comparador (boolea o enter))\n"
                            + "(Cal que hi hagi un vector assignat al comparador, del tipus amb el qual vols fer un dels 3 calculs)"
                            + "1. Distancia vectorial entre vectors booleans normalitzats\n"
                            + "2. Cosinus entre dos vectors booleans\n"
                            + "3. Distancia vectorial entre vectors enters dividint cada una de les seves components per la mida en paraules (que no siguin stop words) del document al que pertoquen");
                    
                    
                    
                    o = in.nextLine();
                int opcio = Integer.parseInt(o);
                
                if (opcio == 1) {
                    
                    
                    System.out.println("Introdueix les paraules del vector boolea [p1 p2 p3 ... p-n]");

                    System.out.println("les paraules mes rellevants son:");
                    b.imprimir_list();
                    
                    o = in.nextLine();
                    
                    double d = comp.distancia_boolea(b.vector_boolea(o));
                    
                    System.out.println("la distancia es:\n"
                            + d);
                    
                    
                    
                }
                else if (opcio == 2) {
                    
                    System.out.println("Introdueix les paraules del vector boolea [p1 p2 p3 ... p-n]");

                    System.out.println("les paraules mes rellevants son:");
                    b.imprimir_list();
                    
                    o = in.nextLine();
                    
                    double d = comp.cosinus_boolea(b.vector_boolea(o));
                    
                    System.out.println("el cosinus es:\n"
                            + d);
                    
                }
                else if (opcio == 3) {
                    
                    System.out.println("Introdueix les paraules del vector enter [p1 p2 p3 ... p-n]");

                    System.out.println("les paraules mes rellevants son:");
                    b.imprimir_list();
                    
                    o = in.nextLine();
                    
                    double d = comp.distancia_enter(b.vector_enter(o));
                    
                    System.out.println("la distancia es:\n"
                            + d);
                    
                }
                else {
                    
                    System.out.println("numero incorrecte");
                    
                }
                    
                    
                    
                }
                else num1 = 99;
            }
            
            
            
            
            
            
            
        }
        else num = 99;
        
    }
    // === ===  FI DEL WHILE GENERAL === ===
    // ========================================
    
    
    }
        catch(Throwable e) {
            System.out.println("S'ha produït un error");
        }
    }
}

/*

            System.out.println("");
            System.out.println("3.    Registre        ->  Subc. de Fitxer. Guarda dades dels atributs de tots els documents");
            System.out.println("4.    Espai_vectorial ->  Subc. de Fitxer. Guarda dades de la frequencia d'aparicio de les paraules en el conjunt dels documents");
            System.out.println("Quina Classe vols provar? (introdueix enter del 1 al 4)");


if (num1 == 1) {
                
}
else if (num1 == 2) {
                
}
else if (num1 == 3) {
                
}
else if (num1 == 4) {
                
}
else if (num1 == 5) {
                
}
else num1 = 99;
}


string.indexOf(char c) -> comença per defecte a string[0]
string.indexOf(char c, int index) -> comença a string[index] INCLÒS (!)

System.out.println("OK");


Vector v = new Vector();
        System.out.println(Arrays.toString(v.get_elements()));



package fitxers;

import static fitxers.Fitxer.path;
import java.io.File;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
    try {

                + " Fitxer  ->  Classe que s'encarrega de la gestio dels fitxers en disc");
        Scanner in = new Scanner(System.in);
        String o;
        
        Fitxer f = new Fitxer();

                int num = 0;
        
            
    }
    catch(Throwable e) {
        System.out.println("S'ha produït un error");
    }
    }
}




*/