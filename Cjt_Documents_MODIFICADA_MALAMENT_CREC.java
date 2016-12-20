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
import fitxers.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
/**
 *
 * @author Sergio, Marc i Adrià
 */
public class Cjt_Documents_MODIFICADA_MALAMENT_CREC {
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
    
    public Cjt_Documents_MODIFICADA_MALAMENT_CREC() {
        hay_error = 0;
    }
    
    public Cjt_Documents_MODIFICADA_MALAMENT_CREC(Document d) {
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
    
    public ArrayList<Integer> cerca_Semblants(int id, int k, int answer) throws IOException {
        
        
        
        
        
        
        System.out.println("inici");
        
        
        
        
        
        Document d = cjt.get(id);
        ArrayList<Integer> docs = new ArrayList<>(); docs.clear();
        int num = answer;
        switch (num) {
            case 1:
                Comparador c = new Comparador();
                Espai_vectorial ev = new Espai_vectorial();
                ev.set_dimensio(2500);
                ev.omplir_list();
                
                
                
            Fitxer ftx = new Fitxer(Integer.toString((int)id));     // li donem el nom i el creem
            ftx.llegir();     // f ara conte el text del document
            String t = ftx.get_text();
            
            t = t.replace(',',' ');
            Text text = new Text();
            text.set_textstring(t);
            
            text.dividir();
            text.crear_divisions();
                
                
                
                String s = text.netejear();
                
                c.assignar_vector_boolea(ev, s);
                Map<Integer, Double> suu = new TreeMap(Map.Entry.<Integer,Double>comparingByValue().reversed());
                
                System.out.println(max_id);
                
                for(int i = 0; i < max_id; i++) {
                    double dist;
                    Vector vd = new Vector();
                    if(cjt.get(i) != null) {
                        
                        
                        
                        System.out.println(max_id);
                        
                        
                        
                        
                        Fitxer ftxa11 = new Fitxer(Integer.toString((int)i));     // li donem el nom i el creem
            ftxa11.llegir();     // f ara conte el text del document
            String ta11 = ftxa11.get_text();
            
            System.out.println(ta11);
            
            ta11 = ta11.replace(',',' ');
            Text texta11 = new Text();
            texta11.set_textstring(ta11);
            
            texta11.dividir();
            texta11.crear_divisions();
                        
                        
                        
                        
                        
                        
                        
                        
                        String str = texta11.netejear();
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        vd = ev.vector_boolea(str);
                       
                        dist = c.distancia_boolea(vd);
                        
                        
                        Map.Entry<Integer,Double> m = new Map.Entry<Integer,Double>();
                        suu.put(i, dist);
                    }
                }
                int temp = 0;
                for(Map.Entry<Integer, Double> entry : suu.entrySet()) {
                    if(temp < k) {
                        docs.add(cjt.get(entry.getKey()).get_id());
                        
                        ++temp;
                    }
                }
                break;
            case 2:
                Comparador ce = new Comparador();
                Espai_vectorial evec = new Espai_vectorial();
                evec.set_dimensio(2500);
                evec.omplir_list();
                
                
            Fitxer ftxa = new Fitxer(Integer.toString((int)id));     // li donem el nom i el creem
            ftxa.llegir();     // f ara conte el text del document
            String ta = ftxa.get_text();
            
            ta = ta.replace(',',' ');
            Text texta = new Text();
            texta.set_textstring(ta);
            
            texta.dividir();
            texta.crear_divisions();
                
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
                
                
                String str = texta.netejear();
                
                
                
                
                ce.assignar_vector_boolea(evec, str);
                Map<Integer, Double> suuu = new TreeMap(Map.Entry.<Integer,Double>comparingByValue().reversed());
                for(int i = 0; i < max_id; ++i) {
                    double dist;
                    Vector vd = new Vector();
                    if(cjt.get(i) != null) {
                        
                        
                        
                        Fitxer q = new Fitxer(Integer.toString((int)i));     // li donem el nom i el creem
            q.llegir();     // f ara conte el text del document
            String w = q.get_text();
            
            w = w.replace(',',' ');
            Text e = new Text();
            e.set_textstring(w);
            
            e.dividir();
            e.crear_divisions();
                        
                        
                        
                        
                        String stra = e.netejear();
                        
                        vd = evec.vector_boolea(stra);;
                        
                        dist = ce.cosinus_boolea(vd);

                        suuu.put(i, dist);
                    }
                }
                int tempo = 0;
                for(Map.Entry<Integer, Double> entry : suuu.entrySet()) {
                    if(tempo < k) {
                        docs.add(cjt.get(entry.getKey()).get_id());
                        ++tempo;
                    }
                }
                
                break;
            case 3:
                
                Comparador comp = new Comparador();
                Espai_vectorial evee = new Espai_vectorial();
                evee.set_dimensio(2500);
                evee.omplir_list();
                
                
                Fitxer ftxa2 = new Fitxer(Integer.toString((int)id));     // li donem el nom i el creem
            ftxa2.llegir();     // f ara conte el text del document
            String ta2 = ftxa2.get_text();
            
            ta2 = ta2.replace(',',' ');
            Text texta2 = new Text();
            texta2.set_textstring(ta2);
            
            texta2.dividir();
            texta2.crear_divisions();
                
                
                

                
                
                
                
                
                String str2 = texta2.netejear();
                
                comp.assignar_vector_boolea(evee, str2);
                Map<Integer, Double> suuuu = new TreeMap(Map.Entry.<Integer,Double>comparingByValue().reversed());
                for(int i = 0; i < max_id; ++i) {
                    double dist;
                    Vector vd = new Vector();
                    if(cjt.get(i) != null) {
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        Fitxer FF = new Fitxer(Integer.toString((int)i));     // li donem el nom i el creem
            FF.llegir();     // f ara conte el text del document
            String tring = FF.get_text();
            
            tring = tring.replace(',',' ');
            Text txt = new Text();
            txt.set_textstring(tring);
            
            txt.dividir();
            txt.crear_divisions();
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        String s3 = cjt.get(i).get_contingut().netejear();
                        vd = evee.vector_enter(s3);
                        
                        dist = comp.distancia_enter(vd);

                        suuuu.put(i, dist);
                    }
                }
                int tem = 0;
                for(Map.Entry<Integer, Double> entry : suuuu.entrySet()) {
                    if(tem < k) {
                        docs.add(cjt.get(entry.getKey()).get_id());
                        ++tem;
                    }
                }
                break;
            default: hay_error = 4;
                break;
        }
        
        hay_error = 0;
        
        return docs;
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
}