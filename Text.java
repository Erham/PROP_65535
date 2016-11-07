/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author sergiokov20
 */
public class Text {
    private List<Frase> l_frase; //Llista de frases del text.
    private String textstring; //Contingut del text en String.
    
    public Text() {
        l_frase = new ArrayList();
    }
    
    public Text(Frase p) {
        l_frase = new ArrayList();
        l_frase.add(p);
        textstring = textstring.concat(p.get_frasestring());
    }
    public List<Frase> get_lf() {
        return l_frase;
    }
    
    public String get_textstring() {
        return textstring;
    }
    
    public void set_lf(List<Frase> lf) {
        l_frase = lf;
    }
    
    public void set_textstring(String fin) {
        textstring = fin;
    }    
    
    public void afegir(Frase p) {
        l_frase.add(p);
        textstring = textstring.concat(p.get_frasestring());
    }
    
    public void imprimir() {
        System.out.println(textstring);
    }
    
    public void imprimir_llista_frases() {
        for(Frase f : l_frase) {
            f.imprimir();
        }
    }
    
   /* public int contar_np() {
        for(int i = 0; i < l_frase.size(); ++i) {
            while(l_frase.get(i).charAt(l_frase.get(i).longitud()-1) != '.') {
                if(l_frase.get(i).charAt(l_frase.get(i).longitud()-1) != ' ') {
                    
                }
            }
        }
    }*/
    //------------------------ UPDATE 25/10/2016 -----------------
    
    public void dividir() { //Divideix el text en frases.
        boolean findefrase = false;
        String aux = "";
        for(int i = 0; i < textstring.length(); ++i) {
            if(findefrase == true) {
                findefrase = false;
                aux = "";
            }
            else {
                if(textstring.charAt(i) == '.' || textstring.charAt(i) == '!' || textstring.charAt(i) == '?') {
                    if(textstring.charAt(i) == '.') {
                        while(textstring.charAt(i) == '.' && i < textstring.length()) {
                            aux += textstring.charAt(i);
                            ++i;
                        }
                    }
                    findefrase = true;  
                } 
                aux += textstring.charAt(i);
                Frase auxf = new Frase();
                auxf.set_frasestring(aux);
                l_frase.add(auxf);
            }
        }
    }
    
    public void ConvertEnString() { //Procés anterior a la inversa. Frases -> String
        for(int i = 0; i < l_frase.size(); ++i) {
            textstring += l_frase.get(i).toString();
        }
    }
    
    public void crear_divisions() { //Requereix haver creat divisions de classe Document
        for(int i = 0; i < l_frase.size(); ++i) {
            l_frase.get(i).dividir();
        }
    }
    
    public int count() throws IOException {
    //Extreu les stop words i retorna la mida.
        String s = textstring;
        int mida = 1;        
        String[] partes = s.split("[[ ]*|[,]*|[.]*|[...]*|[:]*|[?!]*|[-]*|[!]*|[?]*|[+]*]+");
        for(String ss : partes) {
            Paraula aux = new Paraula();
            aux.set_p(ss);
            if(!aux.is_stop_word()) {
                ++mida;
            }
        }  
        
        return mida;
    }
    
}