/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
/**
 *
 * @author sergiokov20
 */
public class Cjt_Documents {
    private Map<Integer, Document> cjt = new HashMap();
    //private Map<Pair<Frase, Frase>> mapadeadria;
    
    public Cjt_Documents() {
        
    }
    
    public Cjt_Documents(Document d) {
        cjt.put(0, d);
    }
    
    public Map<Integer, Document> get_cjt() {
        return cjt;
    }
    
    public void set_cjt(Map<Integer, Document> cjt) {
        this.cjt = cjt;
    }
    
    public int tamany() {
        return cjt.size();
    }
    
    public void afegir(Document d) {
        if(cjt.isEmpty()) {
            cjt.put(0, d);
            System.out.println("Operació realitzada amb èxit."); 
        }
        else {
            boolean error = false;
            int i = 0;
            while(i < cjt.size() && !error) {
                boolean comp1 = (cjt.get(i).get_titol().get_frasestring().equals(d.get_titol().get_frasestring()));
                boolean comp2 = (cjt.get(i).get_autor().get_frasestring().equals(d.get_autor().get_frasestring()));
                
                if(comp1 && comp2) {
                    System.out.println("ERROR: El document ja existeix.");
                    error = true;
                }
                ++i;
            }
            if(!error) {
                int nou = cjt.size()-1;
                cjt.put(nou, d);
                System.out.println("Operació realitzada amb èxit.");
            }
        }
    }
    
    public void esborrar(int key) {
        if(!cjt.isEmpty()) {
            if(cjt.get(key) == null) System.out.println("ERROR: Document no trobat.");
            else {
                cjt.remove(key);
                System.out.println("Operació realitzada amb èxit.");
            }
        }
        else System.out.println("ERROR: No hi ha cap Document.");
    }

    public void esborrarDoc(String tit, String aut) {
        if(!cjt.isEmpty()) {
            boolean trobat = false;
            int i = 0;
            while(!trobat && i < cjt.size()) {
                if(cjt.get(i).get_titol().get_frasestring().equalsIgnoreCase(tit)) {
                    if(cjt.get(i).get_autor().get_frasestring().equalsIgnoreCase(aut)) {
                        cjt.remove(i);
                        trobat = true;
                        System.out.println("Operació realitzada amb èxit.");
                    }
                }
                ++i;
            }
            if(!trobat) System.out.println("ERROR: Document no trobat.");
        }
        else System.out.println("ERROR: No hi ha cap Document.");
    }
    
    //public void cercar();
    
    public boolean existeix(Document d) {
        boolean existeix = false;
        int i = 0;
        while(i < cjt.size() && !existeix) {
            boolean comp1 = (cjt.get(i).get_titol().get_frasestring().equals(d.get_titol().get_frasestring()));
            boolean comp2 = (cjt.get(i).get_autor().get_frasestring().equals(d.get_autor().get_frasestring()));
                 
            if(comp1 && comp2) {
                existeix = true;
            }
            ++i;
        }
        
        return existeix;
    }
}
