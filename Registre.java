package fitxers;

import static fitxers.Fitxer.path;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Registre extends Fitxer {
    
    // String nom;
    // String text;

    // boolean presencia;
    // boolean modificacio;
    
    public Registre() throws IOException {
        nom = new String();
        this.text = new String();
        
        this.nom = "registre";
        
        this.presencia = false;
        this.modificacio = false;
        
        File a = new File(path(),this.nom + ".txt");       
        BufferedWriter bufw;
        
        if (!a.exists()) {
            bufw = new BufferedWriter(new FileWriter(a)); // registre creat
            bufw.close();
        }
        
        if (a.exists()) { this.presencia = true; }
    }
    // POST: En cas de no tenir creat el registre, el crea
    //       En principi s'ha d'executar una sola vegada en resituar el programa
    //       Sempre hauria de deixar presencia a true 
    
    
    public void guardar_registre() throws IOException {
        if (this.modificacio) {
            BufferedWriter bw = obrir_fitxer_w();
            if (bw != null) {
                bw.write(this.text + "\n");
                bw.close();
            this.modificacio = false;
            }
        }
    }
    
    public void llegir_registre() throws IOException {
        if (this.presencia) {
            BufferedReader br = obrir_fitxer_r();
            String l;
            
            if (br != null) {
                while ((l = br.readLine()) != null) {
                    this.text = (this.text).concat(l); // anem enganxant al text cada linia 1 a 1
                    this.text += "\n";
                }
            br.close();
            }
        }
    }
    
    
    // CONJUNT DE DOCUMENTS ??????????????!!!!!!!!!!!!!!!!!
    // PRE: L'objecte ja és ple
    public void afegir_arxiu(Arxiu nou) {
        String to_add = new String();
        
        to_add = to_add + Integer.toString(nou.get_id()) + " " + nou.get_titol() +
                 " " + nou.get_autor() + " " + nou.get_tema();
        // if ((this.text).length() > 0) this.text += "\n";  // afegim un salt de línia pel seguent document si el text no era buit (tenia mida >1)
        this.text = (this.text).concat(to_add); // afegim els parametres de l'arxiu al text
        this.modificacio = true;          // acabem de modificar-lo
    }
    
    
    // AIXÒ SEGURAMENT CORRESPON A LA CLASSE  -  Conjunt de Documents  -   Map millor
    // PRE: L'arxiu amb aquest id és al registre
    // DE L'ESTIL DE get_atributs_seguents()
    public String get_atributs(String id) {
        String id2;
        int start_index = 0;
        int end_index = (this.text).indexOf(' ');   // mirem on hi ha el primer espai. fins aquí hi haurà el 1r número
        id2 = (this.text).substring(start_index,end_index);
        while ( (!id2.equals(id)) && (start_index != -1) ) {     // mentre el numero que estem mirant no sigui el que busquem, seguim
            start_index = (this.text).indexOf('\n', end_index) + 1;     // ara start_index es al començament del seguent numero
            end_index = (this.text).indexOf(' ',start_index);           // ara end_index es al final del numero + 1
            id2 = (this.text).substring(start_index,end_index);
        }
        // en sortir del while, start_index es la posicio primera de la linia datributs amb l'id que volem
        if (start_index != -1) {
            end_index = (this.text).indexOf('\n',start_index);
            if (end_index == -1) {
                id2 = (this.text).substring(start_index, end_index);
            }
            else {
                id2 = (this.text).substring(start_index, end_index);
            }
        }
        else {id2 = null;}
        // ara tenim a id2 la tira d'atributs que volem passar
        
        return id2;
    }
    // POST:retorna un String amb els atributs
}


// eliminar arxiu:
/*
en afegir-lo, l'afegeixes simplement al final del registre, però
a l'hora d'eliminar-lo, convé que no sigui linial.
Les eliminacions es faran a sobre del map, que es convertirà en string
i es guardarà en no voler fer més canvis.
es guardarà al registre
*/