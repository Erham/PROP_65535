package fitxers;

import gestordocuments.Cjt_Documents;
import java.io.IOException;
import java.util.ArrayList;

public class main_EXP_BOOL {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException {
    try {
        Registre r = new Registre();
        String registre_docs = r.get_text();
        
        Cjt_Documents CONJ = new Cjt_Documents();
        CONJ.omplir_cjt(registre_docs);
        
        ArrayList<Integer> coleccio_ids = CONJ.get_all_ids();
        
        String expressio = "   ";   // AQUÍ INSERIR L'EXPRESSIÓ
        
        Exp_bool EXP = new Exp_bool(expressio);
        ArrayList<Integer> llista_documents_boolea = EXP.avaluar_exp(coleccio_ids);
    }
    catch(Throwable e) {
        System.out.println("S'ha produït un error");
    }
    }
}