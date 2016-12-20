package fitxers;

public class Token {
    
    private boolean e;
    private boolean negacio;
    private char operador;
    private String operand;
    
    public Token() {
        negacio = false;
    }
    
    public void set_expressio(String s) {
        e = true;
        operand = s;
    }
    public void set_operador(char op) {
        e = false;
        operador = op;
    }
    
    public boolean es_operador() {
        return !e;
    }
    public boolean es_expressio() {
        return e;
    }
    // permet saber si es o no expressio (un operand). Altrament sera un operador
    
    public char get_op() throws Exception {
        if (!e) return operador;
        // System.out.println("FAIL!!! Intent d'obtenir operador d'una expressio");
        throw new Exception();
    }
    // retorna l'operador si ho es, altrament peta
    public String get_exp() throws Exception {
        if (e) return operand;
        // System.out.println("FAIL!!! Intent d'obtenir expressio d'un operador");
        throw new Exception();
    }
    // retorna l'expressio si ho es, altrament peta
    
    public boolean negat() {
        return negacio;
    }
    
    public void negar() {
        negacio = true;
    }
    
    public void imprimir() {
        if (negacio) System.out.print("#");
        if (e) {
            System.out.print(operand);
        }
        else {
            System.out.print(operador);
        }
    }
}
