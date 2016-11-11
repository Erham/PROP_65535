package fitxers;

import java.io.File;
import java.io.IOException;

public class Arxiu extends Fitxer {
    
    //private String nom
    
    private String titol;
    private String autor;
    private String tema;
    
    // private String text;
    
    // private boolean presencia;
    // private boolean modificacio;
    
    
    //  Instancia un Arxiu
    //  Es la creacio per defecte, no s'hauria de fer servir, pero es util per fer proves
    public Arxiu() {
        
        this.ini();     // new - titol, autor, tema
        tema = "t_null";   // tema per defecte
        
        presencia = false;
        modificacio = true;
        // this.set_text("abcd");
    }
    
    //  Instancia un Arxiu amb identificador "id"
    //  Carrega el contingut del fitxer "id".txt a l'Arxiu instanciat
    //  Serveix per obtenir Arxius amb el "text" corresponent al seu fitxer .txt ja assignat per poder-lo consultar
    //  (El fitxer "id".txt JA HA D'ESTAR CREAT ABANS, si no l'Arxiu creat no és vàlid, i s'imprimeix un missatge)
    public Arxiu(int id) throws IOException {
        
        this.ini();     // new - titol, autor, tema
        nom = Integer.toString(id);
        File a = new File(path(),nom + ".txt");
        if (a.exists()) {
            presencia = true;
            modificacio = false;
            llegir();   // llegeix el .txt
        }
        else {
            System.out.println("No existeix el fitxer .txt amb aquest identificador");
        }
        
    }
    
    //  Intancia un Arxiu amb identificador "id", "titol" = "titol_1", "autor" = "autor_1" i "tema" = "tema_1"
    //  Crea un fitxer "id".txt a la carpeta on siguis (a la carpeta path())
    //  Serveix per obtenir Arxius amb tots els atributs assignats excepte el "text", i que ja tinguin el fitxer .txt creat
    //  (El fitxer "id".txt NO HA D'ESTAR CREAT ABANS, si ho estava, l'arxiu creat no és vàlid, i s'imprimeix un missatge)
    public Arxiu(int id, String titol_1, String autor_1, String tema_1) throws IOException {
        
        this.ini();     // new - titol, autor, tema
        nom = Integer.toString(id);
        File a = new File(path(),nom + ".txt");
        if (a.exists()) {
            System.out.println("Ja existeix un fitxer .txt amb aquest identificador");
        }
        else { // no existeix
            crear(a);
            titol = titol_1;
            autor = autor_1;
            tema = tema_1;
        }
        
    }
    
    //  Fa els new de titol, autor i tema
    private void ini() {
        titol = new String();
        autor = new String();
        tema = new String();
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
    
}
