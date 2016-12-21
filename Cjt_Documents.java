/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import fitxers.Comparador;
import fitxers.Espai_vectorial;
import fitxers.Fitxer;
import fitxers.Registre;
import fitxers.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
/**
 *
 * @author Sergio, Marc i Adrià
 */
public class Cjt_Documents {
    private Map<Integer, Document> cjt = new HashMap();
    private int max_id; //ID màxim enregistrat fins ara
    private int hay_error;
    
    /*VALORS POSSIBLES DE "hay_error":
    -- OK --
    0) Operació realitzada amb èxit.
    -- ERRORS --
    1) Document ja existeix.
    2) Document no existeix.
    3) El conjunt és buit.
    4) Comanda incorrecta.
    */
    
    
    public ArrayList<Integer> get_all_ids() {
        ArrayList<Integer> al = new ArrayList();
        int i = 0;
        while(i <= max_id) {
            if(cjt.get(i) != null) {
                al.add(i);
            }
            ++i;
        }
        return al;
    }
    
    private void recalcular_maxid() {
        int temp = max_id;
        for(int i = 0; i <= temp; ++i) {
            if(cjt.get(i) != null) max_id = i;
        }
    }
    
    private int trobar_id_disponible() {
        int id = 0;
        boolean stop = false;
        int i = 0;
        while(!stop && i < max_id+1) {
            if(cjt.get(i) == null) {
                id = i;
                stop = true;
            }
            ++i;
        }
        if(!stop) {
            id = cjt.size();
            max_id = id;
        }
        return id;
    }
    
    public Cjt_Documents() {
        hay_error = 0;
    }
    
    public Cjt_Documents(Document d) {
        cjt.put(0, d);
        max_id = 0;
    }
    
    public Map<Integer, Document> get_cjt() {
        return cjt;
    }
    
    public int get_maxid() {
        return max_id;
    }
    
    public void set_cjt(Map<Integer, Document> cjt) {
        this.cjt = cjt;
    }
    
    public void set_maxid(int max_id) {
        this.max_id = max_id;
    }
    
    public int tamany() {
        return cjt.size();
    }
    
    public void afegir(Document d) {
        if(cjt.isEmpty()) {
            cjt.put(0, d);
            max_id = 0;
            hay_error = 0; 
        }
        else {
            boolean error = false;
            int i = 0;
            while(i <= max_id && !error) {
                if(cjt.get(i) != null) {
                    boolean comp1 = (cjt.get(i).get_titol().get_frasestring().equalsIgnoreCase(d.get_titol().get_frasestring()));
                    boolean comp2 = (cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(d.get_autor().get_frasestring()));

                    if(comp1 && comp2) {
                        hay_error = 1;
                        error = true;
                    }
                }
                ++i;
            }
            if(!error) {
                int nou = trobar_id_disponible();
                d.set_id(nou);
                cjt.put(nou, d);
                hay_error = 0;
            }
        }
    }
    
    public void esborrar(int key) {
        if(!cjt.isEmpty()) {
            if(cjt.get(key) == null) hay_error = 2;
            else {
                cjt.remove(key);
                if(key == max_id) recalcular_maxid();
                hay_error = 0;
            }
        }
        else hay_error = 3;
    }
    
    public void esborrarDoc(String tit, String aut) {
        if(!cjt.isEmpty()) {
            boolean trobat = false;
            int i = 0;
            while(!trobat && i <= max_id) {
                if(cjt.get(i) != null) {
                    if(cjt.get(i).get_titol().get_frasestring().equalsIgnoreCase(tit)) {
                        if(cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(aut)) {
                            cjt.remove(i);
                            if(i == max_id) recalcular_maxid();
                            trobat = true;
                            hay_error = 0;
                        }
                    }
                }
                ++i;
            }
            if(!trobat) hay_error = 2;
        }
        else hay_error = 3;
    }
    
    public void modificar(int id) {
        if(cjt.get(id) != null) {
            System.out.println("Vols modificar: Atributs o Contingut?");
            Scanner lector = new Scanner(System.in);
            String option = lector.nextLine();
            if(option.equalsIgnoreCase("atributs")) {
                System.out.println("Introdueix un nou títol, el seu autor i el seu tema.");
                String ti = lector.nextLine();
                String au = lector.nextLine();
                String te = lector.nextLine();

                cjt.get(id).set_titol_String(ti);                
                cjt.get(id).set_autor_String(au);                
                cjt.get(id).set_tema_String(te);
                
                hay_error = 0;
            }
            else if(option.equalsIgnoreCase("contingut")) {
                System.out.println("Introdueix el nou contingut del document.");
                String co = lector.nextLine();
                
                cjt.get(id).set_contingut_String(co);
                hay_error = 0;
            }
            else hay_error = 4;
        }
        else hay_error = 2;
    }
    
    public void modificarDoc(String tit, String aut) {
        boolean trobat = false;
        int i = 0;
        int id = 0;
        while(!trobat && i <= max_id) {
            if(cjt.get(i) != null) {
                if(cjt.get(i).get_titol().get_frasestring().equalsIgnoreCase(tit)) {
                    if(cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(aut)) {
                        trobat = true;
                        id = i;
                    }
                }
            }
            ++i;
        }
                        
        if(trobat) {                
            System.out.println("Vols modificar: Atributs o Contingut?");
            Scanner lector = new Scanner(System.in);
            String option = lector.nextLine();
            if(option.equalsIgnoreCase("atributs")) {
                System.out.println("Introdueix un nou títol, el seu autor i el seu tema.");
                String ti = lector.nextLine();
                String au = lector.nextLine();
                String te = lector.nextLine();

                cjt.get(id).set_titol_String(ti);                
                cjt.get(id).set_autor_String(au);                
                cjt.get(id).set_tema_String(te);
                
                hay_error = 0;
            }
            
            else if(option.equalsIgnoreCase("contingut")) {
                System.out.println("Introdueix el nou contingut del document.");
                String co = lector.nextLine();
                
                cjt.get(id).set_contingut_String(co);
                hay_error = 0;
            }
            else hay_error = 4;
        }
        else hay_error = 2;
    }
    
    public String cerca_Autor(String autor) {
        String texto = "";
        for(int i = 0; i <= max_id; ++i) {
            if(cjt.get(i) != null) {
                if(cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(autor)) {
                    texto += "- "+ cjt.get(i).get_titol().get_frasestring() + "\n";
                }
            }
        }
        return texto;
    }
    
    public String cerca_Prefix(String prefix) {
        String texto = "";
        
        Set<String> autors = new HashSet();
        for(int i = 0; i <= max_id; ++i) {
            if(cjt.get(i) != null) {
                if(cjt.get(i).get_autor().get_frasestring().toLowerCase().startsWith(prefix.toLowerCase())) {
                    if(autors.isEmpty()) autors.add(cjt.get(i).get_autor().get_frasestring());
                    else {
                        if(!autors.contains(cjt.get(i).get_autor().get_frasestring())) autors.add(cjt.get(i).get_autor().get_frasestring());
                    }
                }
            }
        }
        for(String ss : autors) {
            texto += "- " + ss + "\n";
        }
        
        return texto;
    }
    
    public String cerca_Document(String titol, String autor) {
        String texto = "";
        for(int i = 0; i <= max_id; ++i) {
            if(cjt.get(i) != null) {
                if(cjt.get(i).get_titol().get_frasestring().equalsIgnoreCase(titol)){
                    if(cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(autor)) {
                        texto += "- " + cjt.get(i).get_contingut().get_textstring() + "\n";
                    }
                }
            }
        }
        return texto;
    }
    
    // mode : 1 -> distancia boolea. 2 -> cosinus boolea. 3 -> distancia enters
    public ArrayList<Integer> cerca_Semblants(int id, int num_docs, int mode) throws IOException, Exception {
        
        ArrayList<Map.Entry<Integer, Double>> docs = new ArrayList<>(); docs.clear();
        ArrayList<Integer> docs_int = new ArrayList<>(); docs_int.clear();
        
        Comparator<Map.Entry<Integer,Double>> comparador_llista = Map.Entry.<Integer, Double>comparingByValue();
        
        Map<Integer, Double> distancies = new HashMap();
        
        //ArrayList<Map.Entry<Integer,Double>> distancies = new ArrayList<>();
        
        Comparador comp = new Comparador();
        Espai_vectorial e_vec = new Espai_vectorial();
        e_vec.set_dimensio(500);
        e_vec.omplir_map();
        e_vec.omplir_list();
        
        // donem nom al fitxer i creem l'objecte
        
        Fitxer fitxer = new Fitxer(Integer.toString((int)id));
        fitxer.llegir();
        String text = fitxer.get_text();
        
        Text t = new Text();
        t.set_textstring(text);
        t.dividir();
        t.crear_divisions();
        
        text = t.netejear();

        System.out.println("12");
        
        Vector vector;
        
        if (mode == 3) {
            vector = e_vec.vector_enter(text);
        }
        else vector = e_vec.vector_boolea(text);
        
        /* debug
        double[] elem = vector.get_elements();
        for (double num : elem) {
            System.out.println("- " + num + " -");
        }
        debug */
        
        
        if (mode != 3) comp.assignar_vector_boolea(e_vec, text);
        else comp.assignar_vector_enter(e_vec, text);
        
        for (int i = 0; i < max_id; i++) {
            if (i != id) {
                if(cjt.get(i) != null) {
                    Fitxer f1 = new Fitxer(Integer.toString((int)i));
                    f1.llegir();
                    String text1 = f1.get_text();
                    Text t1 = new Text();
                    t1.set_textstring(text1);
                    t1.dividir();
                    t1.crear_divisions();

                    text1 = t1.netejear();
                    Vector v1;
                    if (mode != 3) v1 = e_vec.vector_boolea(text1);
                    else v1 = e_vec.vector_enter(text1);

                    Double d1;
                        switch (mode) {
                            case 1: d1 = comp.distancia_boolea(v1); break;
                            case 2: d1 = comp.cosinus_boolea(v1);   break;
                            case 3: d1 = comp.distancia_enter(v1);  break;
                            default: hay_error = 4; throw new Exception();
                        }
                    Integer in = (Integer) i;
                    distancies.putIfAbsent(in, d1);
                } 
            }
            
            System.out.println(i);
            
        }
        
        
        for (Map.Entry<Integer,Double> entry : distancies.entrySet()) {
            docs.add(entry);
        }
        
        Collections.sort(docs,comparador_llista);
        
        int count = 0;
        
        for (Map.Entry<Integer, Double> entry : docs) {
            if (count < num_docs) {
                
                docs_int.add(entry.getKey());
                
                System.out.println(entry.getValue());
                
            }
            else break;
            count++;
        }
        
        return docs_int;
    }
            
    
    public boolean existeix(Document d) {
        boolean existeix = false;
        int i = 0;
        while(i <= max_id && !existeix) {
            if(cjt.get(i) != null) {
                boolean comp1 = (cjt.get(i).get_titol().get_frasestring().equalsIgnoreCase(d.get_titol().get_frasestring()));
                boolean comp2 = (cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(d.get_autor().get_frasestring()));

                if(comp1 && comp2) {
                    existeix = true;
                }
            }
            ++i;
        }
        
        return existeix;
    }
    
    public boolean existeix_id(int id) {
        boolean trobat = false;
        if(cjt.get(id) != null) trobat = true;
        return trobat;
    }
    
    public int assignar_id_fitxer() { //NO SÉ SI AIXÒ ESTÀ BÉ (= CRITERI QUE PER ID DOCUMENT)
        return trobar_id_disponible();
    }
   
    public void omplir_cjt(String s) throws UnsupportedEncodingException, FileNotFoundException, IOException
    {
        int i = 0;
        int ident = 0;
        boolean idtrobat = false;
        boolean titrobat = false;
        boolean autrobat = false;
        boolean temtrobat = false;
        Document d = new Document();
        
        String suuu = "";
        
        while(i < s.length()) {
            if(!idtrobat) {
                if(s.charAt(i) == ' ' && s.charAt(i+1) == ' ') {
                    i += 2;
                    idtrobat = true;
                    ident = Integer.parseInt(suuu);
                    d.set_id(ident);
                    suuu = "";
                    
                }
                else {
                    suuu += s.charAt(i);
                }
            }
            if(idtrobat && !titrobat) {
                if(s.charAt(i) == ' ' && s.charAt(i+1) == ' ') {
                    i += 2;
                    titrobat = true;
                    d.set_titol_String(suuu);
                    suuu = "";
                    
                }
                else {
                    suuu += s.charAt(i);
                }                
            }
            if(idtrobat && titrobat && !autrobat) {
                if(s.charAt(i) == ' ' && s.charAt(i+1) == ' ') {
                    i += 2;
                    autrobat = true;
                    d.set_autor_String(suuu);
                    suuu = "";
                    
                }
                else {
                    suuu += s.charAt(i);
                }                 
            }
            if(idtrobat && titrobat && autrobat && !temtrobat) {
                if((i+1 != s.length() && s.charAt(i+1) == '\n') || i+1 == s.length()) {
                    temtrobat = true;
                    suuu += s.charAt(i);
                    d.set_tema_String(suuu); 
                    i+=1;
                }
                else {
                    suuu += s.charAt(i);
                }                 
            }
            
            if(idtrobat && titrobat && autrobat && temtrobat) {
                cjt.put(ident, d);
                if(ident > max_id) max_id = ident;
                suuu = "";
                idtrobat = false;
                titrobat = false;
                autrobat = false;
                temtrobat = false;
                d = new Document();
            }
            
            ++i;
        }
        
        //Obtenció dels conjunts:
        int j = 0;
        while(j <= max_id) {
            String filename = Fitxer.path();
            filename = filename.concat("/" + String.valueOf(j) + ".txt");
            File file = new File(filename);
            BufferedReader b = new BufferedReader(
                new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String s2 = "";
            String lector;
            while((lector = b.readLine()) != null) {
                s2 += lector;
            }
            cjt.get(j).set_contingut_String(s2);
            ++j;
        }
    }
    
    
    //------------------UPDATE 04/12/2016------------------------//

    /**
     *
     * @param lp
     * @param lpar
     * @return 
     * @throws java.io.IOException 
     */
    
      public int omplir_vector(List<Paraula> lp, List<Paraula> lpar) throws IOException
    {

        int cont = 0;
        
        for (int i=0; i < lp.size(); i++) {
            for (int j =0; j < lpar.size(); j++) {
            
                Paraula p1 = lpar.get(j);               
                Paraula p2 = lp.get(i);
                
                String s1 = p1.get_p();
                String s2 = p2.get_p();              
 
                if(s1.equalsIgnoreCase(s2) && !p2.is_stop_word() && !s1.equalsIgnoreCase(" ")) 
                {
                    cont++;
                }
            }  
        }

        return cont;
    }
    
    /**
     *
     * @param comp
     * @param mp
     * @param kdoc
     * @return
     */

    public HashMap<Integer, Integer> map_sort(Map<Integer, Integer> comp, HashMap<Integer, Integer> mp, int kdoc)
    {
      int max = 0;
      int i = -1;
      int z = 0;
      List<Integer> repetits;
      repetits = new ArrayList();
     
        while(z < kdoc){
            for(int j=0; j <= max_id; j++)
            {
                if(comp.get(j) != null) {
                    int val = comp.get(j);
                    if(val > max && repetits.isEmpty()) {
                        max = val;
                        i = j;
                    }
                    else if(val > max)
                    {
                        boolean trobat = false;
                        for(int imp=0; imp < repetits.size() && !trobat; imp++)
                        {
                            int rep = repetits.get(imp);
                            int l = j;
                            if(l == rep) trobat = true;
                        }
                        if(!trobat)
                        {
                            i = j;
                            max = val;
                        }
                    }
                }
            }

            if(i > -1)
            {
                mp.put(i, max);
                repetits.add(i);
            }
            max = 0;
            i = -1;
            z++;
        }
        return mp;
    }


      
      
    /**
     *
     * @param listpar
     * @param k
     * @return
     * @throws java.io.IOException
     */
    public HashMap<Integer, Integer> relevantes_query(List<Paraula> listpar, int k) throws IOException
    {
    
        HashMap<Integer, Integer>  docs;
        docs = new HashMap();
        
        int i = 0;
        Map<Integer ,Integer> comp;
        comp = new HashMap();      
              
        while(i <= max_id)
        {
            String texte;
            if(cjt.get(i) != null) {
                texte = cjt.get(i).get_contingut().get_textstring();
                
                List<Paraula> lp = new ArrayList();

                String[] partes = texte.split("[[ ]*|[,]*|[(]*|[)]*|[.]*|[:]*|[?!]*|[-]*|[!]*|[?]*|[+]*]+");
                for(String ss : partes) {
                    Paraula par = new Paraula();
                    //provas separar paraules
                    par.set_p(ss);
                    lp.add(par);              
                }

                int cont;
                cont = omplir_vector(lp, listpar);
                comp.put(i, cont);
            }
            i++; 
        }
        //omplim el vector amb les paraules segons si surten o no en el contingut           
        HashMap<Integer, Integer> mp;
        mp = new HashMap();
        docs = map_sort(comp, mp, k);
      
        return docs;
    }
    
    public int get_error() {
        return hay_error;
    }
    
    public String get_mensaje_error() {
        String valor = "";
        
        switch (hay_error) {
            case 0:
                valor = "Operació realitzada amb èxit.";
                break;
            case 1:
                valor = "El document ja existeix.";
                break;
            case 2:
                valor = "El document no existeix.";
                break;
            case 3:
                valor = "El conjunt és buit.";
                break;
            case 4:
                valor = "Comanda incorrecta.";
                break;
            default:
                break;
        }
        
        return valor;
    }
    
    public int get_id_doc(Document d) {
        int valor = -1;
        boolean trobat = false;
        int i = 0;
        
        while(i <= max_id && !trobat) {
            if(cjt.get(i).get_titol().get_frasestring().equals(d.get_titol().get_frasestring()) && cjt.get(i).get_autor().get_frasestring().equals(d.get_autor().get_frasestring())) {
                trobat = true;
                valor = i;
            }
            ++i;
        }
        
        return valor;
    }
    
    
    public ArrayList<Integer> obtenir_documents_semblants(int id, int num_docs, int mode) throws IOException, Exception {
        Registre r = new Registre();
        String registre_docs = r.get_text();
        
        Cjt_Documents CONJ = new Cjt_Documents();
        CONJ.omplir_cjt(registre_docs);

        return CONJ.cerca_Semblants(id, num_docs, mode);
        
        /*
        
        utilització:
        ===========
        id -> id del document a comparar amb tots els altres
        num_docs -> numero de documents a trobar
        mode ->  1 per distancia en booleans
                 2 per cosinus en booleans
                 3 per distancia amb enters
        
        el ArrayList<Integer> que retorna conté els identificadors dels num_docs documents més semblants al document passat
        
        crida:
        =====
        
        Registre r = new Registre();
        String registre_docs = r.get_text();
        
        Cjt_Documents CONJ = new Cjt_Documents();
        CONJ.omplir_cjt(registre_docs);
        
        ArrayList<Integer> array = CONJ.obtenir_documents_semblants(id,num_docs,mode);
        
        
        */
        
    }
}
