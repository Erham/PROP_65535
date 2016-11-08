/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergiokov20
 */
public class Frase {
    private List<Paraula> l_par; //Lista de paraules de la frase.
    private String frasestring; //Contingut de la frase en String.
    
    public Frase() {
        l_par = new ArrayList();
        frasestring = "";
    }
    
    public Frase(Paraula p) {
        l_par = new ArrayList();
        l_par.add(p);
        frasestring = frasestring.concat(p.get_p());
    }
    
    public List<Paraula> get_lp() {
        return l_par;
    }
    
    public String get_frasestring() {
        return frasestring;
    }
    
    public void set_lpar(List<Paraula> l_par) {
        this.l_par = l_par;
    }
    
    public void set_frasestring(String frasestring) {
        this.frasestring = frasestring;
    }
    
    public void imprimir() {
        System.out.println(frasestring);
    }
    
    public void imprimir_llista_paraules() {
        for(Paraula p : l_par) {
            p.imprimir();
        }
    }
    
    public int longitud() {     
        return frasestring.length();
    }
    
    public int nombre_paraules() {
        return l_par.size();
    }
    
    public void afegir(Paraula p) {
        l_par.add(p);
        frasestring += " ";
        frasestring = frasestring.concat(p.get_p());
    }
    
    
    //----------------- UPDATE 25/10/2016 ---------------------------------
    
    public void dividir() { //Divideix la frase en paraules.
        String[] partes = frasestring.split("[[ ]*|[,]*|[.]*|[:]*|[?!]*|[-]*|[!]*|[?]*|[+]*]+");
        for(String ss : partes) {
            Paraula par = new Paraula();
            par.set_p(ss);
            l_par.add(par);
        }      
    }
    
    public void FraseToString() { //Procés anterior a la inversa. (Frases -> String)
        for(int i = 0; i < l_par.size(); ++i) {
            frasestring += l_par.get(i).toString();
        }
    }
}
