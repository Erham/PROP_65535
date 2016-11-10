package fitxers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arxiu extends Fitxer {
    
    //private String nom;     // nom de fitxer .txt (id)
    
    private String titol;
    private String autor;
    private String tema;
    
    // private String text;
    
    // private boolean presencia;      // fals en crear la instància
    // private boolean modificacio;    // fals en inici, cert si canvies algo de l'arxiu
    
    //====================================//
    
    public Arxiu() {        // Creadora per defecte
        
        this.nom = new String();    // en crear el fitxer creem els new's encara que potser no cal
        
        // this.nom = "42";
        
        this.titol = new String();
        
        // this.titol = "titol";
                
        this.autor = new String();
        
        // this.autor = "autor";
        
        this.tema = new String();
        this.tema = "t_null";   // el tema per defecte
        
        this.text = new String();
        
        this.presencia = false;
        this.modificacio = true;
    }
    
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    //====================================//
    // GETTERS & SETTERS
    
    public String get_titol() {
        // return (String)this.titol.clone(); 
        return this.titol;
}
    
    public String get_autor() {
        // return (String)this.autor.clone();
        return this.autor;
}
    
    public String get_tema() {
        // return (String)this.tema.clone();
        return this.tema;
}  
    
    public int get_id() {
        int aux = Integer.parseInt(this.nom);
        return aux;
    }
    
//==================================================//
    
    public void set_titol(String t) {        
        // this.titol = (String)t.clone();
        this.titol = t;
        // voldria que assignes a titol un duplicat de t
    }
    
    public void set_autor(String a) {
        // this.autor = (String)a.clone();
        this.autor = a;
    }
    
    public void set_tema(String t) {
        // this.tema = (String)t.clone();
        this.tema = t;
    }
    
    public void set_id(int id) {
        String aux = Integer.toString(id);
        this.nom = aux;
    }
    
    
//==================================================//
//==================================================//
    
    
    // PRE: L'arxiu té el nom (l'id) ja assignat i el tema (o t_null per defecte)
    //      No existeix cap arxiu amb aquest id a la col·lecció de documents
    public void crear() throws IOException {
        File a = new File(path(),this.nom + ".txt");       
        BufferedWriter bufw;
        
        if (a.exists()) {
            System.out.println("l'arxiu JA existeix");
        } else {
            bufw = new BufferedWriter(new FileWriter(a)); // .txt creat
            bufw.close();
            this.presencia = true;
            this.modificacio = true;
        }
    }
    // POST: el .txt ha estat creat a la carpeta del projecte (BUIT)
    
    // PRE: El .txt en concret ja està creat i l'arxiu té el nom
    public void guardar_text() throws IOException {
        if (this.modificacio) {
            BufferedWriter bw = obrir_fitxer_w();
            if (bw != null) {
                bw.write(this.text);
                bw.close();
            this.modificacio = false;
            }
        }
    }
    // POST: Esborra el contingut del .txt
    //       Escriu el text al .txt de l'arxiu implícit, i tanca el .txt.
    
    // PRE: El .txt en concret ja està creat i l'arxiu té el nom
    public void llegir_text() throws IOException {
        BufferedReader br = obrir_fitxer_r();
        String l;
        if (br != null) {
            while ((l = br.readLine()) != null) {
                this.text = (this.text).concat(l); // anem enganxant al text cada linia 1 a 1 
            }
            br.close();
        }
    }
    // POST:Llegeix el contingut del .txt i li assigna al text de l'arxiu
    //      tanca el .txt;
    
    
    
    //  AQUÍ S'HA DE CANVIAR, LA LECTURA D'ATRIBUTS S'HA DE FER PARTINT DEL
    //  CONJUNT DE DOCUMENTS
    public void llegir_atributs(int ident) throws IOException {
        String id = Integer.toString(ident);
        Registre reg = new Registre();
        reg.llegir_registre();
        String a = reg.get_atributs(id);    // a String a tenim la tira amb els atributs
    }
}

    
    /* 
    esborrar,
    buidar_text
    guardar atributs - va al main
    */