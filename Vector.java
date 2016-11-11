package fitxers;

public class Vector {
    
    private int dimensio;  // dimensio del vector
    private double element[];
    private int mida;   // mida del document
    
    //  Crea una instancia de Vector, amb tots els termes de "element[]" a 0.0
    public Vector() {
        this.dimensio = 20;
        mida = 0;
        element = new double[dimensio];
        
        for (int i = 0; i < this.dimensio; i++) {
            element[i] = 0.0;
        }
    }
    
    public void set_mida(int m) {
        mida = m;
    }
    
    public int get_dimensio() {
        return dimensio;
    }
    
    public double[] get_elements() {
        return this.element;
    }
    
    //  Modifica "element[index]" per donar-li com a valor "valor"
    //  ("index" ha d'estar entre 0 i "dimensio" - 1)
    public void modificar_element(int index, double valor) {
        element[index] = valor;
    }
    
    //  Retorna "element[index]" ("index" ha d'estar entre 0 i "dimensio" - 1)
    public double get_element_i(int index) {
        return element[index];
    }
    
    //  omple l'array "element[]" amb els termes del String "vec"
    //  "vec" no pot tenir mes ni menys termes que "dimensio"
    //  "vec" ha de ser de l'estil "1.11 2.22 3.33 4.44 5.55 ..." amb "dimensio" nombre de termes
    public void omplir_vector(String vec) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < this.dimensio; i++) {   // per cada posicio (dimensio):
            end = vec.indexOf(' ',start);   // trobem l'espai
            if (end < 0) {
                end = vec.length();
            }
            this.element[i] = Double.parseDouble(vec.substring(start,end));     //assignem el trosset de string (passat a double) a la posicio i
            start = end + 1;
        }
    }
    
    //  Normalitza el Vector perquè tingui modul = 1.0;
    //  "element[]" ha de ser ple de doubles, si no no es pot cridar a la funcio, es una precondicio
    public void normalitzar() {
        double mod = this.modul();  // agafem el modul
        if (mod != 0.0) {
            for (int i = 0; i < this.dimensio; i++) {
                this.element[i] = this.element[i] / mod;
            }
        }
    }
    
    //  Retorna el modul del Vector
    public double modul() {
        double mod2 = 0.0;
        for (int i = 0; i < this.dimensio; i++) {
            double t = this.element[i];
            mod2 += (t * t);
        }
        return Math.sqrt(mod2);
    }
    
    //  Divideix els termes de "element[]" per la "mida" del Document al qual pertany
    //  "mida" no pot ser 0;
    public void dividir() {
        for (int i = 0; i < this.dimensio; i++) {
            
            element[i] = element[i] / mida;
        }
    }
    
    //  Retorna la resta v1 - v2
    static Vector restar(Vector v1, Vector v2) {
        Vector aux = new Vector();
        for (int i = 0; i < v1.dimensio; i++) {
            aux.element[i] = v1.element[i] - v2.element[i];
        }
        return aux;
    }
    
    //  Retorna una copia del vector "v"
    static Vector clonar(Vector v) {
        Vector aux = new Vector();
        
        for (int i = 0; i < v.dimensio; i++) {
            aux.element[i] = v.element[i];
        }
        aux.mida = v.mida;
        return aux;
    }
    
}