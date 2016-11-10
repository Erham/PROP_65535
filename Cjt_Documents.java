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
            System.out.println("Operació realitzada amb èxit."); 
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
                        if(i == max_id) recalcular_maxid();
                        trobat = true;
                        System.out.println("Operació realitzada amb èxit.");
                    }
                }
                ++i;
            }
            if(!trobat) System.out.println("ERROR: Document no trobat.");
        }
        else System.out.println("ERROR: El conjunt de documents és buit.");
    }
    
    //public void cercar();
    
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
}
