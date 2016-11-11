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
import java.util.TreeMap;
/**
 *
 * @author Sergio, Marc i Adrià
 */
public class Cjt_Documents {
    private Map<Integer, Document> cjt = new HashMap();
    private int max_id; //ID màxim enregistrat fins ara
    
    //private Map<Pair<Frase, Frase>> mapadeadria;
    
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
            System.out.println("--Operació realitzada amb èxit--"); 
        }
        else {
            boolean error = false;
            int i = 0;
            while(i <= max_id && !error) {
                if(cjt.get(i) != null) {
                    boolean comp1 = (cjt.get(i).get_titol().get_frasestring().equals(d.get_titol().get_frasestring()));
                    boolean comp2 = (cjt.get(i).get_autor().get_frasestring().equals(d.get_autor().get_frasestring()));

                    if(comp1 && comp2) {
                        System.out.println("ERROR: El document ja existeix.");
                        error = true;
                    }
                }
                ++i;
            }
            if(!error) {
                int nou = trobar_id_disponible();
                cjt.put(nou, d);
                System.out.println("--Operació realitzada amb èxit--");
            }
        }
    }
    
    public void esborrar(int key) {
        if(!cjt.isEmpty()) {
            if(cjt.get(key) == null) System.out.println("ERROR: Document no trobat.");
            else {
                cjt.remove(key);
                System.out.println("--Operació realitzada amb èxit--");
            }
        }
        else System.out.println("ERROR: No hi ha cap Document.");
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
                            System.out.println("--Operació realitzada amb èxit--");
                        }
                    }
                }
                ++i;
            }
            if(!trobat) System.out.println("ERROR: Document no trobat.");
        }
        else System.out.println("ERROR: El conjunt de documents és buit.");
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
                
                System.out.println("--Operació realitzada amb èxit--");
            }
            else if(option.equalsIgnoreCase("contingut")) {
                System.out.println("Introdueix el nou contingut del document.");
                String co = lector.nextLine();
                
                cjt.get(id).set_contingut_String(co);
                System.out.println("--Operació realitzada amb èxit--");
            }
            else System.out.println("ERROR: Comanda incorrecta.");
        }
        else System.out.println("ERROR: El document no existeix.");
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
                
                System.out.println("--Operació realitzada amb èxit--");
            }
            
            else if(option.equalsIgnoreCase("contingut")) {
                System.out.println("Introdueix el nou contingut del document.");
                String co = lector.nextLine();
                
                cjt.get(id).set_contingut_String(co);
                System.out.println("--Operació realitzada amb èxit--");
            }
            else System.out.println("ERROR: Comanda incorrecta.");
        }
        else System.out.println("ERROR: El document no existeix.");
    }
    
    public void cerca_Autor(String autor) {
        System.out.println("Llista de documents escrits per " + autor + ':');
        for(int i = 0; i <= max_id; ++i) {
            if(cjt.get(i) != null) {
                if(cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(autor)) {
                    System.out.println(cjt.get(i).get_titol().get_frasestring());
                }
            }
        }
    }
    
    public void cerca_Prefix(String prefix) {
        Set<String> autors = new HashSet();
        System.out.println("Llista de autors que comencen per " + prefix + ':');
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
            System.out.println(ss);
        }
    }
    
    public void cerca_Document(String titol, String autor) {
        System.out.println("El contingut del document buscat és:");
        for(int i = 0; i <= max_id; ++i) {
            if(cjt.get(i) != null) {
                if(cjt.get(i).get_titol().get_frasestring().equalsIgnoreCase(titol)){
                    if(cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(autor)) {
                        System.out.println(cjt.get(i).get_contingut().get_textstring());
                    }
                }
            }
        }
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
            default: System.out.println("ERROR: Ha de ser un nº entre 1 i 3!");
                break;
        }
        
        System.out.println("--Operació realitzada amb èxit--");
    }
    
    public boolean existeix(Document d) {
        boolean existeix = false;
        int i = 0;
        while(i <= max_id && !existeix) {
            boolean comp1 = (cjt.get(i).get_titol().get_frasestring().equals(d.get_titol().get_frasestring()));
            boolean comp2 = (cjt.get(i).get_autor().get_frasestring().equals(d.get_autor().get_frasestring()));
                 
            if(comp1 && comp2) {
                existeix = true;
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
}