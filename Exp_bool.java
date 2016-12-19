package fitxers;

import gestordocuments.Document;
import gestordocuments.Frase;
import gestordocuments.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// Expressio_booleana
public class Exp_bool {
    
    public String expressio;
    public ArrayList<Token> tokens;    // llista amb els tokens de l'expressio, en el mateix ordre d'aparicio
    public Node arbre;
    
    Exp_bool(String exp) {
        expressio = exp;
        tokens = new ArrayList<>();
        tokens.clear();
    }
    //  assigna "expressio"
    
    public ArrayList<Integer> documents_avaluar_expressio(ArrayList<Integer> coleccio) throws IOException {
        ArrayList<Integer> documents_trobats = new ArrayList<>();
        for (Integer id : coleccio) {   // per cada id (ergo document):
            Fitxer ftx = new Fitxer();
            ftx.set_nom(Integer.toString((int)id));   // li donem el nom
            ftx.llegir();     // f ara conte el text del document
            String t = ftx.get_text();
            Text text = new Text();     // ALERTA IMPORTS SABER PACKAGE
            text.set_textstring(t);
            text.dividir();
            // text ara te la llista de frases
            ArrayList<Frase> frases = new ArrayList<>();
            frases = (ArrayList<Frase>) text.get_lf();
            // frases te la llista de frases del text;
            
            boolean match = false;
            for (Frase f : frases) {    // iteracio per cada una de les frases del text
                
                
                // if f.satisfa    match = true + break
            }
            if (match) {
                documents_trobats.add(id);  // si satisfa lexpressio, l'afegim a la llista
            }
        }
        return documents_trobats;
    }
    // coleccio: llista amb tots els ids dels documents
    
    public boolean avaluar_expressio(Frase f) {
        
    }
    
    public boolean crear_arbre() throws Exception {
        Pair auxiliar = new Pair();
        auxiliar = crear_subarbre(0);
        arbre = auxiliar.tree;
        
        // debug
        System.out.println(auxiliar.index + 1);
        System.out.println(tokens.size());
        // debug
        
        return auxiliar.index + 1 == tokens.size();
    }
    
    private Pair crear_subarbre(int j) throws Exception {
        /**/ System.out.println("entro a crear_subarbre("+j+")"); /**/
        Stack<Node> ops = new Stack<>();
        Stack<Node> exps = new Stack<>();
        Pair ret = new Pair();
     // -------------------------
        int p_new;
        int p_old;
        int size = tokens.size();
        for (int i = j; i < size; i++) {
            /**/ System.out.println("entro al for (i = "+i+" )"); /**/
            Node act = new Node();
            Token token = tokens.get(i);
            act.set_token(token);       // ara act te el token actual
            /**/ System.out.println("token["+i+"]"); /**/
            if (token.es_expressio()) {     // EXPRESSIO
                /**/ System.out.println("token es expressio, push("+token.get_exp()+") a exps"); /**/
                exps.push(act);
            }   // FI EXPRESSIO
            else {  // OPERADOR
                /**/ System.out.println("token es operador, "+token.get_op()); /**/
                p_new = prioritat(act);
                /**/ System.out.println("p_new = "+p_new); /**/
                if (p_new == 2) {   // parentesi
                    /**/ System.out.println("token es un parentesi, push a ops"); /**/
                    ops.push(act);
                    Pair temp = new Pair();
                    temp = crear_subarbre(i + 1);
                    /**/ System.out.println("fi de crear_subarbre("+(i+1)+")"); /**/
                    i = temp.index;
                    /**/ System.out.println("i ara es "+i); /**/
                    if (ops.peek().get_token().negat()) {
                        temp.tree.negar();
                    }
                    exps.push(temp.tree);
                    /**/ System.out.println("push(temp.tree) a exps"); /**/
                    ops.pop();
                    /**/ System.out.println("token era un parentesi, pop de ops"); /**/
                }
                else if (ops.empty()) {
                    /**/ System.out.println("ops es buit, push a ops"); /**/
                    ops.push(act);
                }
                else {  // no es parentesi i ops no es buit
                    /**/ System.out.println("no es parentesi i ops no es buit"); /**/
                    p_old = prioritat(ops.peek());
                    /**/ System.out.println("p_old = "+p_old); /**/
                    if (p_new > p_old) {ops.push(act); /**/ System.out.println("p_new > p_old es cert, push a ops") /**/;}
                    else {  // hem trobat un op amb menys o = prioritat que l'actual
                        /**/ System.out.println("trobat un op amb menys o = prioritat que l'actual. p_new ="+p_new+"; p_old ="+p_old); /**/    
                        do {
                            /**/ System.out.println("inici iteracio do"); /**/
                            Node arbre2 = new Node();
                            arbre2 = ops.peek(); ops.pop();
                            
                            // ara afegir left & right a l'arbre2
                            Node esquerre = new Node();
                            esquerre = exps.peek(); exps.pop();
                            
                            Node dret = new Node(); 
                            
                            /**/ System.out.println("exps buida?: "+exps.empty()); /**/
                            if (!(exps.empty())) {
                                dret = exps.peek(); exps.pop();
                            }
                            else {
                                ret.index = i;
                                ret.tree = esquerre;
                                return ret;
                            }
                            
                            // ja tenim left i right com a nodes, ara enganxar-los
                            arbre2.create_left_child(esquerre);
                            arbre2.create_right_child(dret);
                            // l'arbre ja esta creat, ara cal afegir-lo a la pila exps
                            exps.push(arbre2);
                            if (!(ops.empty())) {
                                /**/ System.out.println("ops no es buit"); /**/
                                p_old = prioritat(ops.peek());
                            }
                            /**/ System.out.println("fi iteracio do"); /**/
                        } while ( !(ops.empty() | p_new > p_old) );
                        /**/ System.out.println("FI del do"); /**/
                        if (p_new == -1) {  // en acabar, segur que ops es buit, perque -1 es el minim
                            /**/ System.out.println("p_new = "+p_new+", era )"); /**/
                            ret.index = i;
                            ret.tree = exps.peek();
                            return ret;
                        }
                        ops.push(act);
                        /**/ System.out.println("push a ops"); /**/
                    }
                }
            }   // FI OPERADOR
            j = i;
        }
        /**/ System.out.println("fi del for, i ="+j); /**/
        ret.index = j;
        ret.tree = exps.peek();
        return ret;
    }
    
    public Node element_anterior(Stack<Node> pila) throws Exception {
        if (!pila.empty()) {
            Node n1 = pila.peek();
            pila.pop();
            Node n2 = pila.peek();
            pila.push(n1);
            return n2;
        }
        else error();
        return null;
    }
    
    public boolean verificar_not() throws Exception {
        boolean just_not = false;
        int i = 0;
        Stack<Integer> pila = new Stack<>();
        int mida = tokens.size();
        while (i < mida) {
            Token t = tokens.get(i);
            if (!t.es_expressio()) {    // ergo es operador
                char op = t.get_op();
                
                if (just_not) {
                    if (op != '(') return false;     // si es &, | o )
                    else {  // si es (
                        pila.push(i-1);
                        t.negar();
                    }
                    just_not = false;  
                }
                if (op == '!') just_not = true;
            }
            else {  // si es expressio
                if (just_not) {
                    pila.push(i-1);
                    t.negar();
                }
                just_not = false;
            }
            i++;
        }
        
        while (!pila.empty()) {
            int I = (int)pila.peek();
            tokens.remove((int)I);
            pila.pop();
        }
        
        return true;
    }
    
    public boolean verificar_correctesa() throws Exception {
        if (verificar_regles(0) == -42) return true;
        else return false;
    }
    
    public int verificar_regles(int i) throws Exception {
        
        boolean just_op = false;
        boolean just_exp = false;
        for (; i < tokens.size(); i++) {
            Token t = tokens.get(i);    // t -> token[i] de la llista
            
            /* debug
            System.out.print("proc token " + i + ": ");
            t.imprimir();
            System.out.println();
            debug*/
            
            if (t.es_operador()) {
                char op = t.get_op();
                    if (op == '(') {
                        if (just_exp) return -1;
                        i = verificar_regles(i+1);
                        if (i < 0) return i;
                        just_exp = true;
                        just_op = false;
                    }
                    else if (op == ')') {
                        if ( just_op | !just_exp ) return -1;
                        return i;
                    }
                    else {  // op es & o |
                        
                        if ( just_op | !just_exp ) return -1;
                        just_op = true;
                        just_exp = false;
                    }
                    if (just_op & just_exp) return -1;   
            }
            else {  // if (t.es_expressio())
                
                if (just_exp) {
                    return -1;
                }
                just_exp = true;
                just_op = false;  
            }
        }
        return -42;  // si el for acaba, vol dir que l'expressio es correcta
    }
    // calcula la correctesa de les regles eoe (expressio - operador - expressio)
    
    public boolean verificar_parentesis() {
        int par = 0;
        for (int i = 0; i < expressio.length(); i++) {
            if (expressio.charAt(i) == '(') par++;
            else if (expressio.charAt(i) == ')') par--;
            if (par < 0) return false;
        }
        return (par == 0);
    }
    // diu si l'expressio esta ben parentitzada
    
    public boolean redundancia_not() {
        boolean ret = false;
        boolean just_not = false;
        int just_i = 0;
        char c;
        for (int i = 0; i < expressio.length(); i++) {
            c = expressio.charAt(i);
            if (c != ' ') {
                if (c == '!') { 
                    if (just_not) {
                        ret = true;
                        expressio = expressio.substring(0,just_i) + expressio.substring(i + 1, expressio.length());
                        i = just_i;
                        just_i = 0;
                        just_not = false; 
                    }
                    else {
                    just_not = true;
                    just_i = i;
                    }
                }
                else just_not = false;
            } 
        }
        if ( expressio.contains("!!") ) ret = true;
        expressio = expressio.replaceAll("!!","  ");
        return ret;
    }
    // extreu de "expressio" tots els "!!", i retorna cert en cas d'haver-ne extret algun
    
    public void calcular_tokens() throws Exception {
        expressio = "(" + expressio + ")";  // tancament (...) inicial
        afegir_operador('(');
        int n = tokenitzar(1);
        if (n != (expressio.length()-1)) error();
        
    }
    
    public int tokenitzar(int i) throws Exception {
        int size = expressio.length();
        int tipus;
        while (i < size) {
            char c = expressio.charAt(i);
            if (c != ' ') {
                if (c != ')') {
                    tipus = tipus_op(c);
                    switch (tipus) {
                        case 0: {   // operador
                            afegir_operador(c);
                            if (c == '(') {
                                i = tractar_parentesi(i);
                            }
                            break;
                        } 
                        case 1: {   // inici / fi expressio
                            if (c == '{') {
                                i = tractar_conjunt(i);
                            }
                            else if (c == '"') {
                                i = tractar_sequencia(i);
                            }
                            else error();
                            break;
                        }
                        case 2: {   // lletres o altres
                            i = tractar_paraula(i);
                            break;
                        }
                        default: {error(); break;}
                    }
                }
                else {
                    afegir_operador(c);
                    return i;
                }
            }
        i++;    
        }
        error();
        return 0;   
    }
    // es crida a dins un parentesi
    
    private static int tipus_op(char c) {
        if ( (c == '&') | (c == '|') | (c == '!') | (c == '(') | (c == ')') ) return 0;
        if ( (c == '{') | (c == '}') | (c == '"') ) return 1;
        else return 2;
         
        // if ( ((c >= 'a')&(c <= 'z')) | ((c >= 'A')&(c <= 'Z')) ) return false;  // fals si es una lletra SIMPLE
    }
    // retorn : 0 -> operador
    //          1 -> inici / fi expressio
    //          2 -> lletres i altres
    
    private int tractar_parentesi(int i) throws Exception /* "i" es l'index de partida */ {
        i++;
        int size = expressio.length();
        char c = expressio.charAt(i);
        if (c == ')') error();  // el parentesi es buit!
        int mida_tokens_ant = tokens.size();
        int ret = tokenitzar(i);
        if (tokens.size() <= mida_tokens_ant) error();
        return ret;
    }
    private int tractar_conjunt(int i) throws Exception {
        int index_ini = i;
        i++;
        boolean buit = true;    // controla si el conjunt es buit encara
        int size = expressio.length();
        char c;
        int tipus;
        while (i < size) {
            c = expressio.charAt(i);
            if (c == '}') {     // final de conjunt
                if (buit) error();     // perque el conjunt sera buit!
                else {
                    String conjunt = expressio.substring(index_ini,i) + "}";
                    afegir_expressio(conjunt);  // l'afegim a "tokens"
                    return i;
                }
            }
            else {
                tipus = tipus_op(c);
                if ( (tipus == 0) | (tipus == 1) ) error();     // perque dins el conjunt hi ha algun operador o '{'/'"'
                else {  // "c" es una lletra o altre
                    if (c != ' ') buit = false;     // si hi ha alguna cosa que no sigui un espai, es que no es buit
                }
            }
            i++;
        }
        System.out.println("Error a tractar_conjunt()");
        error();
        return 0;
    }
    private int tractar_sequencia(int i) throws Exception {
        int index_ini = i;
        i++;
        boolean buit = true;    // controla si el conjunt es buit encara
        int size = expressio.length();
        char c;
        int tipus;
        while (i < size) {
            c = expressio.charAt(i);
            if (c == '"') {     // final de sequencia /**************/
                if (buit) error();     // perque la sequencia sera buida!
                else {
                    String sequencia = expressio.substring(index_ini,i) + "\""; /**************/
                    afegir_expressio(sequencia);  // l'afegim a "tokens"
                    return i;
                }
            }
            else {
                tipus = tipus_op(c);
                if ( (tipus == 0) | (tipus == 1) ) error();     // perque dins el conjunt hi ha algun operador o '{'/'"'
                else {  // "c" es una lletra o altre
                    if (c != ' ') buit = false;     // si hi ha alguna cosa que no sigui un espai, es que no es buit
                }
            }
            i++;
        }
        System.out.println("Error a tractar_sequencia()");
        error();
        return 0;
    }
    private int tractar_paraula(int i) throws Exception {
        int index_ini = i;
        int size = expressio.length();
        char c;
        int tipus;
        while (i < size) {
            c = expressio.charAt(i);
            tipus = tipus_op(c);
            if (tipus != 2 | c == ' ') {
                String paraula = expressio.substring(index_ini,i);
                afegir_expressio(paraula);  // l'afegim a "tokens"
                return i - 1;
            }
            i++;
        }
        System.out.println("Error a tractar_paraula()");
        error();
        return 0;
    }
    // tracten un parentesi / conjunt / sequencia / paraula
    // retornen l'index on ha conclos l'execucio
    // "i" te el mateix principi per les 4
    
    private void afegir_expressio(String exp) {
        Token t = new Token();
        t.set_expressio(exp);
        tokens.add(t);
    }
    // afegeix l'expressio "exp" a la llista "tokens"
    private void afegir_operador(char op) {
        Token t = new Token();
        t.set_operador(op);
        tokens.add(t);
    }
    // afegeix l'operador "op" a la llista "tokens"

    public void imprimir_tokens() {
        for (Token tok : tokens) {
            // obtenim una entrada de la llista
            tok.imprimir();
            System.out.print("-");
        }
        System.out.println();
    }
    
    public void error() throws Exception {
        throw new Exception();
    }
    // fa "petar" l'execucio degut a un error
    
    public static int prioritat(Node n) throws Exception {
        char c = n.get_token().get_op();
        switch (c) {
            case '(': return 2;
            case '&': return 1;
            case '|': return 0;
            case ')': return -1;
            default: throw new Exception();
        }
    }
    // retorna la prioritat numèrica pels 4 possibles operadors (n ha de ser node d'operador)
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    private int size;           //
    private String expressio;   // l'string de l'expressio
    public Node arbre;
    
    private int i;
    
    
    public Exp_bool() {
        size = 0;
        expressio = null;
        i = 0;
        arbre = null;
    }
    
    public Exp_bool(String E) {
        expressio = E;
        size = E.length();
        i = 0;
        arbre = new Node();
    }
    //  crea , afegeix l'expressio i inicialitza l'arbre
    
    public Node generar() throws Exception {
        Node ret = new Node();
        Node N = ret;
        Node pare = null;
        boolean obert = true;
        boolean just_not = false;
        char c;
        
        while (i < size) {  // bucle principal
            
            c = expressio.charAt(i);
            switch (c) {
                case '&': {
                    if (N.es_buit()) {  // si N es buit, no som en un final d'expr
                        if (N.fe() == null) error();
                        else if (N.fe().es_buit()) error();
                        N.set_and();
                        pare = N;
                        N.crear_fill_d();   // crear dret
                        N = N.fd();         // assignarlo a N
                    }
                    else {  // acabem de sortir d'una exp (sigui la que sigui, parentesi o cjt, seq...)
                        if (N.get_op() == '&') {
                            
                            Node a = new Node();
                            a.crear_fill_e();
                            a.e = N;
                            N = a;
                            N.set_and();
                            pare = N;
                            N.crear_fill_d();
                            N = N.fd();
                            
                        }
                        else if (N.get_op() == '|') {
                            
                            Node a = new Node();
                            a.crear_fill_e();
                            a.e = N.d;
                            N.d = a;
                            N = a;
                            N.set_and();
                            pare = N;
                            N.crear_fill_d();
                            N = N.fd();
                            
                        }
                        else error();
                    }
                }
                case '|': {
                    if (N.es_buit()) {
                        if (N.fe() == null) error();
                        else if (N.fe().es_buit()) error();
                        N.set_or();
                        pare = N;
                        N.crear_fill_d();
                        N = N.fd();
                    }
                    else {
                        
                        Node a = new Node();
                        a.crear_fill_e();
                        a.e = N;
                        N = a;
                        N.set_or();
                        pare = N;
                        N.crear_fill_d();
                        N = N.fd();
                        
                    }
                }
                case '!': {
                    
                    if (N.fe() != null) error();
                    else if ( (pare.get_op() == 'e') | (pare.get_op() == 'n' ) ) error();
                    N.negar();
                    pare = N;
                    N.crear_fill_d();
                    N = N.fd();
                    
                        
                        

                }
                case '(': {
                    if (N.fe() != null) error();
                    if (pare != null) {
                            if ( (pare.get_op() == 'e') | (pare.get_op() == 'n') ) error();
                    }
                    // ara ja podem cridar la "recursio"
                    i++;
                    N = generar();
                    // aqui hem acabat un parentesi, un possible final d'expressio
                    // per tant no podem assignar cap fill a aquest N (!!!)
                    // ni fer pare = N; (el pare segueix sent el mateix si no fem cap fill !!! )
                    
                }
                case '{': {
                    
                }
                case '"': {
                    
                }
                case '': {
                    
                }
            }
            i++;    
        }
        
        return ret;
    }
    // genera un arbre fins que es troba amb ')', i el retorna
    // si en trobar ')' no ha acabat amb "expressio", després haurà de PETAR
    
    public int 
    
    
   
    

 end = doc.indexOf(' ', start);  // posició del següent espai
                if (end >=0) {
                    paraula = doc.substring(start, end);

 this.text = (this.text).concat(to_add);

char charAt(int index)

string.indexOf(char c) -> comença per defecte a string[0]
string.indexOf(char c, int index) -> comença a string[index] INCLÒS (!)


Scanner in = new Scanner(System.in);
    String o1;

o1 = in.nextLine();


    
    
    
    

    
  
 private int size;       // mida del String "exp"
    private String exp;     // conte l'expressio
    
    
    Expressio_booleana() {}
    
    Expressio_booleana(String expressio) {
        exp = expressio;
        size = expressio.length();
    }
    
    public void set_expressio(String expressio) {
        exp = expressio;
    }
    public int get_size() {
        return size;
    }
    
    
    public void analitzar() {
        
    }
    
    private int parentesi(int i) throws Exception {
        char c;
        boolean buit = true;
        boolean op_anterior = false;
        boolean not_anterior = false;
        boolean exp_anterior = false;
        // "i" ha de contenir l'index des del qual comencem a iterar
        
        while (i < size) {
            c = exp.charAt(i);  // "c" es igual al caracter on som [i]
            
            if (c == '(') {     // nou parentesi
                if (exp_anterior) throw new Exception();    // Si veniem d'una EXPRESSIO, avorta
                i++;
                buit = false;
                i = this.parentesi(i);
                op_anterior = false;
                not_anterior = false;
                exp_anterior = true;
            }
            else if (c == ')') {    // acabem el parentesi
                if (buit) throw new Exception();
                return i + 1;
            }
            else if (c == '{') {    // nou conjunt
                if (exp_anterior) throw new Exception();    // Si veniem d'una EXPRESSIO, avorta
                i++;
                buit = false;
                i = this.conjunt_par(i);
                op_anterior = false;
                not_anterior = false;
                exp_anterior = true;
            }
            else if (c == '"') {    // nova sequencia
                if (exp_anterior) throw new Exception();    // Si veniem d'una EXPRESSIO, avorta
                i++;
                buit = false;
                i = this.sequencia_par(i);
                op_anterior = false;
                not_anterior = false;
                exp_anterior = true;
            }
            else if (c == '!') {    // NOT
                if (exp_anterior) throw new Exception();    // Si veniem d'una EXPRESSIO, avorta
                i++;
                buit = false;
                op_anterior = false;
                not_anterior = true;
                exp_anterior = false;
            }
            else if (c == '&' | c == '|') {    // AND i OR
                if (op_anterior | not_anterior) throw new Exception();
                if (!exp_anterior) throw new Exception();
                i++;
                op_anterior = true;
                not_anterior = false;
                exp_anterior = false;
            }
            else if (c == ' ') {
                i++;
            }
            else if (c >= 'a' & c <= 'z') {     // Si "c" es una lletra minuscula:
                i++;
                buit = false;
                i = this.paraula(i);
                op_anterior = false;
                not_anterior = false;
                exp_anterior = true;
            }
        }
        System.out.print("No hauriem d'haver arribat fins aqui... :|");
        throw new Exception();  // Si arribem aqui es mala senyal...
    }
    
    private int conjunt_par(int i) {
        
        return 0;
    }
    
    private int sequencia_par(int i) {
        
        return 0;
    }
    
    private int paraula(int i) {
        
        return 0;
    }
    
    */