package fitxers;

import static fitxers.Fitxer.path;
import java.io.File;
import java.io.IOException;

public class Registre extends Fitxer {
    
    // String nom;
    // String text;
    // boolean presencia;
    // boolean modificacio;
    
    
    //  Instancia un Registre
    //  Si "registre.txt" no estava creat, el crea. Si ja estava creat, llegeix el seu contingut i l'assigna a "text"
    public Registre() throws IOException {
        
        this.ini();
        File a = new File(path(),this.nom + ".txt");
        
        if (!a.exists()) {  // si "registre.txt" no estava creat:
            this.crear(a);
        }
        else {
            presencia = true;
            this.llegir();
        }
        
    }
    
    //  Assigna el "nom" que sempre es "registre" i assigna "modificacio"
    private void ini() {
        nom = "registre";
        modificacio = false;
    }
    
    //  Afegeix una nova entrada d'un Arxiu al Registre
    //  ("nou" ha de ser un Arxiu amb nom (que representa l'identificador), titol, autor i tema
    //  Si "nou" no és com s'ha especificat no es pot cridar la funcio. Es una precondicio.
    public void afegir_arxiu(Arxiu nou) {
        String to_add = new String();
        
        to_add = to_add + Integer.toString(nou.get_id()) + "  " + nou.get_titol() +
                 "  " + nou.get_autor() + "  " + nou.get_tema();
        /* ALERTA !!! !!! !!! if ((this.text).length() > 0)*/// this.text += "\n";  // afegim un salt de línia pel seguent document
        this.text = (this.text).concat(to_add); // afegim els parametres de l'arxiu al text
        this.modificacio = true;          // acabem de modificar-lo
    }
    
}