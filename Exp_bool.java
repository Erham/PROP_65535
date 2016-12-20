package fitxers;

import gestordocuments.Frase;
import gestordocuments.Paraula;
import static gestordocuments.Paraula.comparar_2paraules;
import gestordocuments.Text;
import java.io.IOException;
import java.util.ArrayList;
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
    
    public ArrayList<Integer> avaluar_exp(ArrayList<Integer> coleccio) throws Exception {
        
        verificar_parentesis();
        redundancia_not();
        calcular_tokens();
        verificar_not();
        verificar_correctesa();
        crear_arbre();

        return get_llista(coleccio);
    }
    
    public ArrayList<Integer> get_llista(ArrayList<Integer> coleccio) throws IOException, Exception {
        ArrayList<Integer> documents_trobats = new ArrayList<>();

        for (Integer id : coleccio) {   // ===== PER CADA ID DOCUMENT ===== (START)
            Fitxer ftx = new Fitxer(Integer.toString((int)id));     // li donem el nom i el creem
            ftx.llegir();     // f ara conte el text del document
            String t = ftx.get_text();
            
            t = t.replace(',',' ');
            Text text = new Text();
            text.set_textstring(t);
            
            text.dividir();
            text.crear_divisions();
            
            // text ara te la llista de frases
            ArrayList<Frase> llista_frases = new ArrayList<>();
            llista_frases = text.get_lf();
            // frases te la llista de frases del text;

            llista_frases.remove(llista_frases.size()-1);
            for (Frase frase : llista_frases) {
                for (Paraula par : frase.get_lp()) {
                }
            }
            
            boolean match = false;
            for (Frase f : llista_frases) {    // iteracio per cada una de les frases del text

                if (satisfa_expressio(f,arbre)) {
                    match = true;
                    break;
                }
            }
            if (match) {
                documents_trobats.add(id);  // si satisfa lexpressio, l'afegim a la llista
            }
            
        }   // ===== PER CADA ID DOCUMENT ===== (END)
        
        return documents_trobats;
    }
    // coleccio: llista amb tots els ids dels documents
    
    public boolean satisfa_expressio(Frase f, Node node) throws Exception {
         
        if (node.es_fulla()) {  // --- BASE ------------------------

            if (node.get_token().es_expressio()) {  // sempre hauria de ser-ho
                String exp = node.get_token().get_exp();
                boolean ret;
                switch (exp.charAt(0)) {
                    case '{': {     // CONJUNT
                        ret = satisfa_conjunt(exp, f.get_lp());
                        break;
                    }
                    case '"': {     // SEQUENCIA
                        ret = satisfa_sequencia(exp, f.get_lp());
                        break;
                    }
                    default: {      // PARAULA
                        ret = satisfa_paraula(exp, f.get_lp());
                    }
                }
                if (node.get_token().negat()) return !ret;
                else return ret;
            }
            else error();
        }   // --- FI BASE ------------------------
        
        else {  // --- RECURSIÓ ------------------------
            
            boolean left = satisfa_expressio(f, node.get_left());
            
            char op = 0;
            if (node.get_token().es_operador()) {
                
                op = node.get_token().get_op();
            }
            else error();
            
            boolean right = satisfa_expressio(f, node.get_right());
            
            switch (op) {
                case '&': {
                    if (node.get_token().negat()) return !(left & right);
                    else return (left & right);
                }
                case '|': {
                    
                    if (node.get_token().negat()) return !(left | right);
                    else return (left | right);
                }
                default: error();
            }
            
        }   // --- FI RECURSIÓ ------------------------
        return false;
        
    }
    
    public boolean satisfa_conjunt(String conjunt, ArrayList<Paraula> llista_par) {
        conjunt = conjunt.substring(1,conjunt.length()-1);    // extreu els { }
        conjunt = " " + conjunt + " ";
        
        ArrayList<Paraula> paraules_conjunt = new ArrayList<>();
        String aux = "";
        for (int i = 0; i < conjunt.length(); i++) {
            char c = conjunt.charAt(i);
            if (c != ' ') {     // si no som en un espai:
                aux = aux + c;
            }
            else if (aux != "") {paraules_conjunt.add(new Paraula(aux)); aux = "";}
        }
        
        // paraules_conjunt te les paraules del conjunt 
        
        for (Paraula par1 : paraules_conjunt) {
            // mirar si par1 es a llista_par
            boolean c = false;
            for (Paraula par2 : llista_par) {
                
                if (comparar_2paraules(par1,par2)) {
                    c = true;
                    break;
                }
            }
            if (!c) return false;
        }
        return true;
    }
    public boolean satisfa_sequencia(String sequencia, ArrayList<Paraula> llista_par) {
        sequencia = sequencia.substring(1,sequencia.length()-1);
        Frase frase = new Frase();
        for (Paraula p1 : llista_par) {
            frase.afegir(p1);
        }
        String frase_string = frase.get_frasestring();
        return frase_string.matches("(.*)"+ sequencia +"(.*)");
    }
    public boolean satisfa_paraula(String paraula, ArrayList<Paraula> llista_par) {
        Paraula p1 = new Paraula(paraula);
        for (Paraula p2 : llista_par) {
            if (comparar_2paraules(p1,p2)) return true;
        }
        return false;
    }
    
    public boolean crear_arbre() throws Exception {
        Pair auxiliar = new Pair();
        auxiliar = crear_subarbre(0);
        arbre = auxiliar.tree;
        return auxiliar.index + 1 == tokens.size();
    }
    private Pair crear_subarbre(int j) throws Exception {
        Stack<Node> ops = new Stack<>();
        Stack<Node> exps = new Stack<>();
        Pair ret = new Pair();
     // -------------------------
        int p_new;
        int p_old;
        int size = tokens.size();
        for (int i = j; i < size; i++) {
            Node act = new Node();
            Token token = tokens.get(i);
            act.set_token(token);       // ara act te el token actual
            if (token.es_expressio()) {     // EXPRESSIO
                exps.push(act);
            }   // FI EXPRESSIO
            else {  // OPERADOR
                p_new = prioritat(act);
                if (p_new == 2) {   // parentesi
                    ops.push(act);
                    Pair temp = new Pair();
                    temp = crear_subarbre(i + 1);
                    i = temp.index;
                    if (ops.peek().get_token().negat()) {
                        temp.tree.negar();
                    }
                    exps.push(temp.tree);
                    ops.pop();
                }
                else if (ops.empty()) {
                    ops.push(act);
                }
                else {  // no es parentesi i ops no es buit
                    p_old = prioritat(ops.peek());
                    if (p_new > p_old) {ops.push(act);}
                    else {  // hem trobat un op amb menys o = prioritat que l'actual   
                        do {
                            Node arbre2 = new Node();
                            arbre2 = ops.peek(); ops.pop();
                            
                            // ara afegir left & right a l'arbre2
                            Node esquerre = new Node();
                            esquerre = exps.peek(); exps.pop();
                            
                            Node dret = new Node(); 
                            
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
                                p_old = prioritat(ops.peek());
                            }
                        } while ( !(ops.empty() | p_new > p_old) );
                        if (p_new == -1) {  // en acabar, segur que ops es buit, perque -1 es el minim
                            ret.index = i;
                            ret.tree = exps.peek();
                            return ret;
                        }
                        ops.push(act);
                    }
                }
            }   // FI OPERADOR
            j = i;
        }
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
        //System.out.println("Error a tractar_conjunt()");
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
        //System.out.println("Error a tractar_sequencia()");
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
        //System.out.println("Error a tractar_paraula()");
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