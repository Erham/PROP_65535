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
        }
        else {
            int nou = cjt.size()-1;
            cjt.put(nou, d);
        }
    }
    
    public void esborrar(int key) {
        if(!cjt.isEmpty()) {
            if(cjt.get(key) == null) System.out.println("ERROR: Document no trobat.");
            else cjt.remove(key);
        }
        else System.out.println("ERROR: No hi ha cap Document.");
    }
    
    //public void cercar();
    
    //public void comprobar_existencia();
}
