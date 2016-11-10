package fitxers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fitxer {

protected String nom;
protected String text;

protected boolean presencia;
protected boolean modificacio;

// PATH MOLARIA QUE RETORNES EL PATH DE ON SOM!!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // retorna el path de la carpeta del projecte
    static String path() {
        return "/home/adria/Documentos/proves"; 
    }
    // ======= MODIFICAR PER CANVIAR LA CARPETA!!! =======
    
    // PRE:El fitxer ja té el nom assignat
    BufferedWriter obrir_fitxer_w() throws IOException {
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
    // POST:Retorna el BufferedWriter del fitxer implícit, o el retorna null
    // en cas que no existeixi el fitxer (i ho imprimeix)
    
    BufferedReader obrir_fitxer_r() throws IOException {
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
    // Anàloga a obrir_fitxer_w , però aquesta l'obre per lectura. 



// Retorna la REFERÈNCIA, ALERTA en MODIFICAR-LO, que afectarà aquí dins
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
    
}