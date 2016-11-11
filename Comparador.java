package fitxers;

import static fitxers.Vector.clonar;
import static fitxers.Vector.restar;

public class Comparador {
    
    private Vector vec;
    private boolean vector_assignat;
    
    //  Crea una instancia de Comparador
    public Comparador() {
        vector_assignat = false;
    }
    
    public Vector get_vec() {
        System.out.println("OK");
        return vec;
    }
    
    //  Assigna un vector boolea a "vec"
    //  ("doc" ha de correspondre a un vector valid, i "espai" ha de ser valid)
    public void assignar_vector_boolea(Espai_vectorial espai, String doc) {
        vec = espai.vector_boolea(doc);
        vector_assignat = true;
    }
    
    //  Idem a l'anterior, pero en enters
    public void assignar_vector_enter(Espai_vectorial espai, String doc) {
        vec = espai.vector_enter(doc);
        vector_assignat = true;
    }
    
    //  Retorna la distancia entre el vector implicit "vec" i v2 (normalitzant-los)
    //  (Els dos vectors han de ser BOOLEANS)
    public double distancia_boolea(Vector v2) {
        return this.distancia(v2, 1);
    }
    
    //  Retorna el cosinus entre el vector implicit "vec" i v2
    //  (Els dos vectors han de ser BOOLEANS)
    public double cosinus_boolea(Vector v2) {
        return this.distancia(v2, 2);
    }
    
    //  Retorna la distancia entre el vector implicit "vec" i v2
    //  Abans de calcular la distancia, divideix cada vector per la mida (en paraules que no siguin stop-words) del document al qual corresponen
    //  (Els dos vectors han de ser ENTERS)
    public double distancia_enter(Vector v2) {
        return this.distancia(v2, 3);
    }
    
    //  Retorna la distancia / cosinus entre "vec" i v2, en un dels 3 modes
    // ("vec" i "v2" han de ser els dos booleans o els dos enters, i el mode ha de ser coherent amb el tipus de vectors que siguin)
    //  1 -> BOOLEA NORMALITZAT DISTANCIA
    //  2 -> BOOLEA COSINUS
    //  3 -> ENTER/MIDA DISTANCIA
    private double distancia(Vector v2, int mode) {
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
        }
    }
    
}