package fitxers;

import static fitxers.Vector.clonar;
import static fitxers.Vector.restar;
import java.util.Arrays;

public class Comparador {
    
    private Vector vec;
    private boolean vector_assignat;
    
    public Comparador() {
        vector_assignat = false;
    }
    
    public void assignar_vector_boolea(Espai_vectorial espai, String doc) {
        vec = espai.vector_boolea(doc);
        vector_assignat = true;
    }
    
    public void assignar_vector_enter(Espai_vectorial espai, String doc) {
        vec = espai.vector_enter(doc);
        vector_assignat = true;
    }
    
    //  Retorna la distancia / cosinus entre dos vectors, en un dels 3 modes
    //  1 -> BOOLEÀ NORMALITZAT DISTÀNCIA
    //  2 -> BOOLEÀ COSINUS
    //  3 -> ENTER/MIDA DISTÀNCIA
    public double distancia(Vector v2, int mode) {
        double d = 0.0;
        
        if (vector_assignat) {
            switch (mode) {
            // BOOLEÀ NORMALITZAT DISTÀNCIA
                case 1: {

                    Vector v1;
                    v1 = clonar(vec);
                    v1.normalitzar();
                    v2.normalitzar();
                    Vector v = restar(v1,v2);
                    d = v.modul();
                    break;

                }
            // BOOLEÀ COSINUS
                case 2: {

                    Vector v1;
                    v1 = clonar(vec);
                    double prod_esc = 0.0;
                    for (int i = 0; i < v1.get_dimensio(); i++) {
                        prod_esc += ( v1.get_element_i(i) * v2.get_element_i(i) );
                    }
                    // tenim el producte escalar a // "prod_esc"
                    d = prod_esc / ( v1.modul() * v2.modul() );
                    break;

                }
            // ENTER/MIDA DISTÀNCIA
                case 3: {

                    Vector v1;
                    v1 = clonar(vec);
                    v1.dividir();
                    v2.dividir();

                    Vector v = restar(v1,v2);
                    d = v.modul();
                    break;

                }
                default:
                    break;
            }
            return d;
        }
        else {
            System.out.println("El comparador no té vector assignat!!!");
            return 0.0;
            // THROW EXCEPCIÓ !!! !!! !!! !!! !!! !!!
            // ==================================================================================================================================================
        }
    }
    
}
