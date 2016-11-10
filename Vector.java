package fitxers;

public class Vector {
    
    private int dimensio;  // dimensió del vector , a llegir d'algun lloc ??
    private double element[];
    private int mida;   // mida del document
    
    public Vector() {
        this.dimensio = 4; // LLEGIR D'ALGUN LLOC!!!!!! FITXER??????? 10 PERQUÈ SÍ!!! PERÒ CAL VARIAR-HO!!!!
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
    
    public void modificar_element(int index, double valor) {
        element[index] = valor;
    }
    
    public double get_element_i(int index) {
        return element[index];
    }
    
    //  omple l'array "element[]" amb els termes del String "vec"
    //  "vec" no pot tenir mes termes que "dimensio"
    public void omplir_vector(String vec) {     // vec es un string de l'estil "1.11 2.22 3.33 4.44 5.55 ..." de "dimensio" termes
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
    //  THROW UNA EXCEPCIÓ PER SI EL STRING NO ÉS PROU LLARG!!!! ERROR DE DIMENSIÓ!!!!!
    
    // normalitza el vector perquè tingui mòdul = 1.0;
    public void normalitzar() {
        double mod = this.modul();  // agafem el mòdul
        for (int i = 0; i < this.dimensio; i++) {
            this.element[i] = this.element[i] / mod;
        }
    }
    
    //  retorna el mòdul del vector
    public double modul() {
        double mod2 = 0.0;
        for (int i = 0; i < this.dimensio; i++) {
            double t = this.element[i];
            mod2 += (t * t);
        }
        return Math.sqrt(mod2);
    }
    
    public void dividir() {
        for (int i = 0; i < this.dimensio; i++) {
            
            element[i] = element[i] / mida;
        }
    }
    
    static Vector restar(Vector v1, Vector v2) {
        Vector aux = new Vector();
        for (int i = 0; i < v1.dimensio; i++) {
            aux.element[i] = v1.element[i] - v2.element[i];
        }
        return aux;
    }
    
    //  Retorna una còpia del vector "v" (??? ??? ???)
    static Vector clonar(Vector v) {
        Vector aux = new Vector();
        
        for (int i = 0; i < v.dimensio; i++) {
            aux.element[i] = v.element[i];
        }
        aux.mida = v.mida;
        return aux;
    }
    
    // FALTA LA LECTURA DE LA DIMENSIÓ !!! !!! !!!
    
}

/*

Vector a = new Vector();
        System.out.println(Arrays.toString(a.element));

string.indexOf(char c) -> comença per defecte a string[0]
string.indexOf(char c, int index) -> comença a string[index] INCLÒS (!)

*/