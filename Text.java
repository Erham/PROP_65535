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
        boolean b = false;
        String aux = "";
        int i = 0;
        if(textstring.length() > 0){
            while(i < textstring.length()) {
                if(findefrase == true) {
                    findefrase = false;
                    aux = "";
                    aux += textstring.charAt(i);
                }
                else {
                    if(textstring.charAt(i) == '.' || textstring.charAt(i) == '!' || textstring.charAt(i) == '?') {
                        if(textstring.charAt(i) == '.') {
                            while(textstring.charAt(i) == '.' && (i) < textstring.length()) {
                                ++i;
                                b = true;
                            }
                        }
                        Frase auxf = new Frase();
                        while(aux.startsWith(" "))
                        {
                            aux = aux.substring(1);
                        }
                        auxf.set_frasestring(aux);
                        l_frase.add(auxf);
                        findefrase = true;  
                    } 

                    aux += textstring.charAt(i);

                }
                if(b == false) ++i;
                else b = false;
            }
            int l = textstring.length()-1;
            if(textstring.charAt(l) != '.' && textstring.charAt(l) != '!' && textstring.charAt(l) != '?')
            {
            Frase ax = new Frase();
            if(aux.startsWith(" "))
                        {
                            aux = aux.substring(1);
                        }
            ax.set_frasestring(aux);
            l_frase.add(ax);
            }
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
        int mida;        
        mida = 0;
        if("".equals(s)) return mida;
        String[] partes = s.split("[[ ]*|[,]*|[.]*|[...]*|[:]*|[?!]*|[-]*|[!]*|[?]*|[+]*]+");
        for(String ss : partes) {
            Paraula aux = new Paraula();
            aux.set_p(ss);
            if(!aux.is_stop_word() && !"".equals(ss)) {
                ++mida;
            }
        }  
        
        return mida;
    }
    
}