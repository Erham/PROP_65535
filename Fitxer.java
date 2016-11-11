package fitxers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fitxer {

    protected String nom;           // nom del fitxer .txt (id)
    protected String text;

    protected boolean presencia;    // fals al principi, cert si el Fitxer ja té el seu fitxer .txt corresponent creat
    protected boolean modificacio;  // fals al principi, cert quan modifiques "text"
    
    
    public Fitxer() {
        
        nom = new String();
        text = new String();
        
    }
    
    //  Retorna el path de la carpeta del projecte
    public static String path() {
        
        // System.out.println(System.getProperty("user.dir") + "/arxius");
        
        return System.getProperty("user.dir") + "/arxius"; 
    }
    
    //  Retorna el BufferedWriter del Fitxer (null en cas que no existeixi el .txt)
    //  (Fitxer ha de tenir "nom" assignat)
    //  (En cas que no existeixi ho comunica, ho IMPRIMEIX)
    protected BufferedWriter obrir_fitxer_w() throws IOException {
        File aux = new File(path(),this.nom + ".txt");
        
        BufferedWriter buffer = null;
        if (aux.exists()) {
            buffer = new BufferedWriter(new FileWriter(aux));
            return buffer;
        }
        else {
            System.out.println("El fitxer NO EXISTEIX");
        }
        return buffer;
    }
    
    //  Ídem a obrir_fitxer_w(), però per lectura
    private BufferedReader obrir_fitxer_r() throws IOException {
        File aux = new File(path(),this.nom + ".txt");
        
        BufferedReader buffer = null;
        if (aux.exists()) {
            buffer = new BufferedReader(new FileReader(aux));
            return buffer;
        }
        else {
            System.out.println("El fitxer NO EXISTEIX");
        }
        return buffer;
    } 

    
    public String get_text() {
        return this.text;
    }

    public void set_text(String t) {
        this.text = t;
        this.modificacio = true;
    }

    public boolean creat() {
        return this.presencia;
    }
    
    public boolean modificat() {
        return this.modificacio; 
    }
    
    public void set_nom(String t) {
        nom = t;
    }
      
    //  Llegeix el contingut del .txt (.txt ha d'estar creat i Fitxer ha de tenir "nom" assignat)
    //  Assigna el contingut del .txt a "text"
    public void llegir() throws IOException {
        if (this.presencia) {
            text = new String();    // esborra el contingut de "text"
            BufferedReader br = obrir_fitxer_r();
            String l;
            if (br != null) {
                while ((l = br.readLine()) != null) {
                    this.text = (this.text).concat(l); // anem enganxant al text cada linia 1 a 1
                    this.text = (this.text).concat("\n");
                }
            br.close();
            }
        }
    }
    
    //  Substitueix el contingut del .txt pel de "text" (el .txt ha d'estar creat i Fitxer ha de tenir "nom" assignat)
    public void guardar() throws IOException {
        if (this.modificacio) {
            BufferedWriter bw = obrir_fitxer_w();
            if (bw != null) {
                bw.write(this.text);
                bw.close();
                this.modificacio = false;
            }
        }
    }
    
    //  Esborra el contingut de "text"
    public void buidar_text() {
        text = new String();
        modificacio = true;
    }
    
    //  Crea un fitxer .txt de nom = "nom".txt
    //  No existeix cap fitxer .txt que es digui "nom".txt
    protected void crear(File a) throws IOException {
        
        BufferedWriter bufw;
        bufw = new BufferedWriter(new FileWriter(a)); // .txt creat
        bufw.close();
        presencia = true;
        modificacio = false;
        
    }
    
    //  Elimina el fitxer .txt de Fitxer
    //  El fitxer "nom".txt JA HA D'ESTAR CREAT ABANS, si no imprimeix un missatge informant-ho
    public void eliminar() {
        File a = new File(path(),nom + ".txt");
        if (a.exists()) {
            if (a.delete()) {
                System.out.println("El Fitxer " + nom + ".txt ha estat eliminat");
            }
            else {
                System.out.println("El Fitxer " + nom + ".txt no ha pogut ser eliminat");
            }
        }
        else {
            System.out.println("No existeix cap fitxer " + nom + ".txt");
        }
    }
    
}