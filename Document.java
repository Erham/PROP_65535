/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Sergio
 */
public class Document {
    private Frase titol = new Frase();
    private Frase autor = new Frase();
    private Paraula tema = new Paraula();
    private Text contingut = new Text();
    private int id;
    
    private String correccion(String s) { //Elimina símbols extranys al text
        String definitivo = s;
        definitivo = definitivo.replaceAll("&quot;", "\"");
        definitivo = definitivo.replaceAll("&#039;", "'");
        definitivo = definitivo.replaceAll("&amp;nbsp;", " ");
        definitivo = definitivo.replaceAll("\\[\\[QUOTE_BEGIN\\]\\]", "");
        definitivo = definitivo.replaceAll("\\[\\[QUOTE_END\\]\\]", ". ");
        definitivo = definitivo.replaceAll("\\[\\[WR\\]\\]", "");
        definitivo = definitivo.replaceAll("\\[\\[D\\]\\]", "");
        definitivo = definitivo.replaceAll("\\[\\[TWITTER_BEGIN\\]\\]", "");
        definitivo = definitivo.replaceAll("\\[\\[TWITTER_END\\]\\]", "");
        definitivo = definitivo.replaceAll("&amp;amp;", "&");
        definitivo = definitivo.replaceAll("&amp;rsquo;", "'");
        definitivo = definitivo.replaceAll("&amp;ldquo;", "\"");
        definitivo = definitivo.replaceAll("&amp;rdquo;", "\"");
        definitivo = definitivo.replaceAll("&amp;middot;", "·");
        definitivo = definitivo.replaceAll("&amp;hellip;", "...");
        definitivo = definitivo.replaceAll("&amp;mdash;", "-");
        definitivo = definitivo.replaceAll("&amp;iuml;", "ï");
        definitivo = definitivo.replaceAll("&amp;uuml;", "ü");
        definitivo = definitivo.replaceAll("&amp;ccedil;", "ç");
        definitivo = definitivo.replaceAll("&amp;aacute;", "á");
        definitivo = definitivo.replaceAll("&amp;eacute;", "é");
        definitivo = definitivo.replaceAll("&amp;iacute;", "í");
        definitivo = definitivo.replaceAll("&amp;oacute;", "ó");
        definitivo = definitivo.replaceAll("&amp;uacute;", "ú");
        definitivo = definitivo.replaceAll("&amp;agrave;", "à");
        definitivo = definitivo.replaceAll("&amp;egrave;", "è");
        definitivo = definitivo.replaceAll("&amp;ograve;", "ò");
        definitivo = definitivo.replaceAll("&amp;", "&");
        
        return definitivo;
    }
    
    public Document() {
        
    }
    
    public Document(Frase titol, Frase autor, Paraula tema) {
        this.titol = titol;
        this.autor = autor;
        this.tema = tema;
    }
    
    public Frase get_titol() {
        return titol;
    }
    
    public Frase get_autor() {
        return autor;
    }
    
    public Paraula get_tema() {
        return tema;
    }
    
    public Text get_contingut() {
        return contingut;
    }
    
    public int get_id() {
        return id;
    }
    
    public void set_titol(Frase titol) {
        this.titol = titol;
    }
    
    public void set_autor(Frase autor) {
        this.autor = autor;
    }
    
    public void set_tema(Paraula tema) {
        this.tema = tema;
    }
    
    public void set_contingut(Text contingut) {
        this.contingut = contingut;
    }

    public void set_titol_String(String s) {
        titol.set_frasestring(s);
    }
    
    public void set_autor_String(String s) {
        autor.set_frasestring(s);
    }
    
    public void set_contingut_String(String s) {
        contingut.set_textstring(s);
    }
    
    public void set_tema_String(String s) {
        tema.set_p(s);
    }
    
    public void imprimir() {
    //Imprimir els atributs del document per pantalla.
        System.out.println("Titol: ");
        titol.imprimir();
        System.out.println("Autor: ");
        autor.imprimir();
        System.out.println("Tema: ");
        tema.imprimir();
        System.out.println("Contingut: ");
        contingut.imprimir();
    }
    
    //--------------------------------------------------------
    
    public void set_all_data(String f) throws IOException, FileNotFoundException {
        String filename = "Documentos/";
        filename = filename.concat(f);
        File file = new File(filename);
        BufferedReader b = new BufferedReader(
            new InputStreamReader(new FileInputStream(file),"UTF-8"));

        String aux; //Buscador de línea
        boolean titfound = false;
        boolean autfound = false;
        boolean temfound = false;
        boolean confound = false;
        boolean todofound = false;
        
        String tit = "    <title"; //Línea del título
        String aut = "    'autorNoticia"; //Línea del autor
        String tem = "    'seccionNoticia"; //Línea del tema
        String con = "            , \"articleBody"; //Línea del contenido
        while ((aux = b.readLine()) != null && todofound == false) {
            String[] pt = aux.split("[>]");
            String[] pa = aux.split("':'");
            String[] ptm = aux.split("':'");
            String[] pc = aux.split("\": \"");
            
            if(pt[0].equals(tit) && titfound == false) {
                String[] pt2 = aux.split("[<|>]");
                String definitivo = pt2[2];
                definitivo = correccion(definitivo);          
                titol.set_frasestring(definitivo);
                titol.dividir();
                titfound = true;
            }
            if(pa[0].equals(aut) && autfound == false) {
                String[] pa2 = aux.split("'autorNoticia':");
                String newautor = pa2[1].substring(1, pa2[1].length()-2);
                //La part següent és per posar majúscules en el nom dels autors.
                String[] majus = newautor.split(" ");
                String replaced = "";
                for(int i = 0; i < majus.length; ++i) {
                    String newmaj = majus[i].substring(0, 1).toUpperCase();
                    replaced += newmaj + majus[i].substring(1);
                    if(i+1 < majus.length) replaced += " ";
                }
                //Fi de la part de majúscules
                autor.set_frasestring(replaced);                
                autor.dividir();
                autfound = true;
            }
            if(ptm[0].equals(tem) && temfound == false) {
                String[] ptm2 = aux.split("'seccionNoticia':");
                String definitivo = ptm2[1].substring(1, ptm2[1].length()-2);
                definitivo = correccion(definitivo); 
                tema.set_p(definitivo);                
                temfound = true;
            }
            if(pc[0].equals(con) && confound == false) {
                String[] pc2 = aux.split("\"articleBody\": ");
                String definitivo = pc2[1].substring(1, pc2[1].length()-1);
                definitivo = correccion(definitivo);
                contingut.set_textstring(definitivo);
                contingut.dividir();
                contingut.crear_divisions();
                confound = true;
            }
            todofound = titfound & autfound & temfound & confound;
        }
    }
    
    public void crear_divisions() { //SÓLO PARA CUANDO NO SEA NECESARIO LEER UN ARCHIVO
        contingut.dividir();
        contingut.crear_divisions();
    }
}
