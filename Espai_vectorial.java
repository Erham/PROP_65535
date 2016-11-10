package fitxers;

import static fitxers.Fitxer.path;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Espai_vectorial extends Fitxer {
    /*
    String nom = "espai_vectorial.txt";
    String text;
    boolean presencia;
    boolean modificacio;
    */
    
    private /* final */ HashMap<String,Integer> paraules;           // map amb paraula - numero de cops que apareix
    private /* final */ ArrayList<Map.Entry<String,Integer>> llista; // llista amb numero aparicions - paraula
    private int dimensio;
    
    public Espai_vectorial() throws IOException {
        
        this.nom = new String();
        this.text = new String();
        
        this.nom = "espai_vectorial";
        this.presencia = false;
        this.modificacio = false;
        
        File a = new File(path(),this.nom + ".txt");       
        BufferedWriter bufw;
        
        if (!a.exists()) {
            bufw = new BufferedWriter(new FileWriter(a)); // registre creat
            bufw.close();
        }
        
        if (a.exists()) { this.presencia = true; }
        ///// AQUESTA FUNCIÓ POT SER ELIMINABLE PER INCLOURE ALGO EN FITXER QUE HO FACI ????? DEL PAL PER NO REPETIR CODI!!!!!!!!!!!!
        ///// AQUEST FUNCIÓ ÉS IGUAL QUE LA DE REGISTRE!!!!!!!
        
        
        paraules = new HashMap<>();
        llista = new ArrayList<>();
        
        this.dimensio = 4; /* ESCOLLIR LA DIMENSIÓ */
        
    }
    
    public int get_dimensio() {
        return this.dimensio;
    }
    
    public void set_dimensio(int dim) {
        this.dimensio = dim;
    }
    
    public void llegir() {
        
    }
    
    public void guardar() {
        
    }
    
    //  Omple el map "paraules" agafant tots els paraula_frequencia del String "text"
    //  Si "text" és buit ho comunica
    public void omplir_map() {
        int size = (this.text).length();  
        if (size > 0) {        // executar només si el String és > 0, si no, vol dir que a l'espai no hi havia paraules (encara)
            (this.paraules).clear();
            String t = this.text;
            int start = 0;
            int end = 0;
            String paraula;
            Integer freq;
            while (end >= 0) {  // iterar mentre no haguem arribat al final del String
                end = t.indexOf(' ', start);    // posicio del 1r espai;
                paraula = t.substring(start, end);  // paraula = text[start // end - 1]
                start = end + 1;
                end = t.indexOf(' ', start);    // posicio del 2n espai
                if (end >= 0) {
                    freq = Integer.parseInt(t.substring(start,end));    // frequencia = text[start // end - 1]
                    start = end + 1;
                }
                else {
                    freq = Integer.parseInt(t.substring(start,size));   // si l'end era = -1 -> agafem l'últim número amb size
                }
                // ara ja tenim la paraula amb la seva frequencia (paraula , freq)
                (this.paraules).putIfAbsent(paraula,freq);  // afegim la (paraula,freq) al map
            }
        }
        else {
            System.out.println("- A L'ESPAI NO HI HA PARAULES !!! -");
        }
    }
    
    //  Imprimeix el contingut del map "paraules" [clau=valor,...]
    public void imprimir_map() {
        System.out.println(paraules.entrySet());
    }
    
    //  Afegeix al map "paraules" totes les paraules del String "doc" -- doc = "paraula1 paraula2 ... paraula_n"
    //  Les que JA HI FOSSIN, incrementa la seva frequencia tants cops com apareguin
    public void afegir_al_espai(String doc) {
        this.modificar_map(doc,true);
    }
    
    //  Extreu del map "paraules" totes les paraules del String "doc"
    //  Elimina les que quedin amb freqüència 0
    public void extreure_del_espai(String doc) {
        this.modificar_map(doc,false);
    }
    
    //  Fa la funció afegir/extreure del map segons mode:
    //  mode = true  -> afegir
    //  mode = false -> extreure
    private void modificar_map(String doc, boolean mode) {
        int size = doc.length();  
        
        if (size > 0) { // executar només si "doc" no és buit
            int start = 0;
            int end = 0;
            String paraula;
            while (end >= 0) {
                end = doc.indexOf(' ', start);  // posició del següent espai
                if (end >=0) {
                    paraula = doc.substring(start, end);    // paraula = doc[start // end - 1]
                    start = end + 1;
                }
                else {
                    paraula = doc.substring(start, size);   // paraula = doc[última paraula]
                }
                // ja tenim la paraula a "paraula"
                // mode = cert -> afegir  /  mode = fals -> extreure
                
                if (mode) {
                // afegir
                    Integer aux = (this.paraules).putIfAbsent(paraula,1);   // si "paraula" no era al map, la introduïm, i apareix 1 cop
                    if (aux != null) {  // si és null, vol dir que la paraula no era al map, i "aux" serà el valor per la clau "paraula"
                        (this.paraules).replace(paraula,++aux);     // substitueix l'entrada de "paraula" per una nova amb frequencia + 1
                    }
                // end afegir
                }
                else {
                // extreure
                
                    Integer freq = (this.paraules).get(paraula);    // obtenim la frequencia, sempre ha de donar 1 almenys
                    if (freq != null) { // sempre
                            (this.paraules).replace(paraula,--freq);    // -- a la frequencia d'aparicio de la paraula
                            (this.paraules).remove(paraula,0);          // L'esborrem si la frequencia era 0
                    }
                    else {
                        System.out.println("ERROR 37");
                        // TANCAR AQUESTA "SESSIÓ" DEL MAP, ÉS A DIR, ELIMINAR L'OBJECTE, NO GUARDAR-LO AL DISC!!!
                    }
                // end extreure
                }
            }
        }
        else {
            System.out.println("- EL DOCUMENT PASSAT ÉS BUIT !!! -");
        }
    }
    
    //  Buida "llista". Omple "llista" amb els valors de "paraules"
    //  Ordena "llista" de més gran a més petit segons la frequencia de cada paraula
    public void omplir_list() {
        (this.llista).clear();
        Iterator it = (this.paraules).entrySet().iterator();
        Map.Entry<String,Integer> element;
        
        while (it.hasNext()) {
            element = (Map.Entry<String,Integer>) it.next();   // obtenim una entrada del map
            (this.llista).add(element);     // afegim l'entrada a la llista
        }
        
        Comparator<Map.Entry<String,Integer>> comp = Map.Entry.<String,Integer>comparingByValue().reversed();  // comparador pels valors en ordre gran...petit
        Collections.sort((this.llista), comp);
    }
    
    public void imprimir_list() {
        System.out.println(this.llista);
    }
    
    //  Retorna el Vector corresponent al document en String "doc"
    //  mode = cert -> ret en boolea  /  mode = fals -> ret en enters
    private Vector calcular_vector(String doc, boolean mode) {
        int size = doc.length();
        
        if (size > 0) { // executar només si "doc" no és buit
            Vector vec = new Vector();
            int start = 0;
            int end = 0;
            int mida = 0;
            String paraula;
            while (end >= 0) {
                end = doc.indexOf(' ', start);  // posició del següent espai
                if (end >=0) {
                    paraula = doc.substring(start, end);    // paraula = doc[start // end - 1]
                    start = end + 1;
                }
                else {
                    paraula = doc.substring(start, size);   // paraula = doc[última paraula]
                }
                // ja tenim la paraula a "paraula"
                // mode = cert -> boolea  /  mode = fals -> enter
                mida = mida + 1;
                
                for (int i = 0; i < this.dimensio; i++) {
                    if (paraula.equals(llista.get(i).getKey())) {
                        
                        if (mode) { // BOOLEANS
                            vec.modificar_element(i, 1.0);
                        }
                        else {      // ENTERS
                            vec.modificar_element(i, vec.get_element_i(i) + 1.0);
                        }
                        break;
                    }
                }
            }
            vec.set_mida(mida);
            return vec;
        }
        else {
            System.out.println("El vector era buit!");
            return null;
        }
        
    }
    
    //  Retorna el Vector corresponent al document en String "doc" en BOOLEÀ
    public Vector vector_boolea(String doc) {
        return this.calcular_vector(doc, true);
    }
    
    //  Retorna el Vector corresponent al document en String "doc" en ENTER
    public Vector vector_enter(String doc) {
        return this.calcular_vector(doc, false);
    }
    
    
}

/*
    boolean contains(Object o)
    int indexOf(Object o)
    E get(int index)

getValue()

        */

/*

- llegir espai vectorial
- guardar espai vectorial
- * actualitzar afegint document espai vectorial, segons un string (l'string a rebre ha de ser una tira de paraules PERFECTA, paraula1_paraula2_..._paraula-n i NET)
- * actualitzar traient de l'espai vectorial (ídem)
- * donat un text string NET, calcular el seu vector (sistema 1, sistema 2 i sistema 3, segons el nombre de paraules a considerar [canviable? segons la dimensió] )
     el vector s'emet com una llista d'enters


FORMAT en .txt , que correspon al text:
paraula numero paraula numero paraula numero

*/