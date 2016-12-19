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
import fitxers.Vector;
import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<Integer> llista_ids = new ArrayList<>(); llista_ids.clear();
        Iterator it = (cjt).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer,Document> element = (Map.Entry<Integer,Document>) it.next();
            Integer id = element.getKey();
            llista_ids.add(id);
        }
        return llista_ids;
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
                    texto += cjt.get(i).get_titol().get_frasestring() + "\n";
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
            texto += ss + "\n";
        }
        
        return texto;
    }
    
    public String cerca_Document(String titol, String autor) {
        String texto = "";
        for(int i = 0; i <= max_id; ++i) {
            if(cjt.get(i) != null) {
                if(cjt.get(i).get_titol().get_frasestring().equalsIgnoreCase(titol)){
                    if(cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(autor)) {
                        texto += cjt.get(i).get_contingut().get_textstring() + "\n";
                    }
                }
            }
        }
        return texto;
    }
    
    public void cerca_Semblants(Document d, int k) throws IOException {
        System.out.println("De l'1 al 3, quin mètode vols?");
        System.out.println("1. Distància vectorial entre dos documents (Espai vectorial de booleans)");
        System.out.println("2. Cosinus entre els vectors de dos documents (Espai vectorial de booleans))");
        System.out.println("3. Distància vectorial entre dos documents (Espai vectorial d'enters)");
        
        Scanner lector = new Scanner(System.in);
        int num = lector.nextInt();
        switch (num) {
            case 1:
                Comparador c = new Comparador();
                Espai_vectorial ev = new Espai_vectorial();
                String s = d.get_contingut().netejear();
                
                c.assignar_vector_boolea(ev, s);
                Map<String, Double> suu = new TreeMap();
                for(int i = 0; i < max_id; ++i) {
                    double dist;
                    Vector vd = new Vector();
                    if(cjt.get(i) != null) {
                        String str = cjt.get(i).get_contingut().netejear();
                        vd.omplir_vector(str);
                        
                        dist = c.distancia_boolea(vd);
                        String s2 = String.valueOf(i);
                        suu.put(s2, dist);
                    }
                }
                int temp = 0;
                for(Map.Entry<String, Double> entry : suu.entrySet()) {
                    if(temp < k) {
                        cjt.get(Integer.parseInt(entry.getKey()));
                        ++temp;
                    }
                }
                break;
            case 2:
                Comparador ce = new Comparador();
                Espai_vectorial evec = new Espai_vectorial();
                String si = d.get_contingut().netejear();
                
                ce.assignar_vector_boolea(evec, si);
                Map<String, Double> suuu = new TreeMap();
                for(int i = 0; i < max_id; ++i) {
                    double dist;
                    Vector vd = new Vector();
                    if(cjt.get(i) != null) {
                        String str = cjt.get(i).get_contingut().netejear();
                        vd.omplir_vector(str);
                        
                        dist = ce.cosinus_boolea(vd);
                        String s2 = String.valueOf(i);
                        suuu.put(s2, dist);
                    }
                }
                int tempo = 0;
                for(Map.Entry<String, Double> entry : suuu.entrySet()) {
                    if(tempo < k) {
                        cjt.get(Integer.parseInt(entry.getKey()));
                        ++tempo;
                    }
                }
                
                break;
            case 3:
                
                Comparador comp = new Comparador();
                Espai_vectorial evee = new Espai_vectorial();
                String str2 = d.get_contingut().netejear();
                
                comp.assignar_vector_boolea(evee, str2);
                Map<String, Double> suuuu = new TreeMap();
                for(int i = 0; i < max_id; ++i) {
                    double dist;
                    Vector vd = new Vector();
                    if(cjt.get(i) != null) {
                        String str = cjt.get(i).get_contingut().netejear();
                        vd.omplir_vector(str);
                        
                        dist = comp.distancia_enter(vd);
                        String s2 = String.valueOf(i);
                        suuuu.put(s2, dist);
                    }
                }
                int tem = 0;
                for(Map.Entry<String, Double> entry : suuuu.entrySet()) {
                    if(tem < k) {
                        cjt.get(Integer.parseInt(entry.getKey()));
                        ++tem;
                    }
                }
                break;
            default: hay_error = 4;
                break;
        }
        
        hay_error = 0;
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
   
    public void omplir_cjt(String s)
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
                }
                else {
                    suuu += s.charAt(i);
                }                 
            }
            ++i;
        }
        cjt.put(ident, d);
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
