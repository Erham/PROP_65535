package fitxers;

public class Node {
    
    private Token tok;      // expressio, sigui conjunt, sequencia o paraula
    private Node left;          // Node esquerre
    private Node right;          // Node dret
    
    public Node() {
        this.crear_arrel();
        left = null;
        right = null;
    }
    // Crea un Node expressio unitaria amb token inicialitzat i no negat
    
    private void crear_arrel() {
        tok = new Token();
    }
    // crea l'arrel no negada

    public boolean es_buit() {
        return (tok == null & left == null & right == null);
    }
    public boolean te_left() {
        return (left != null);
    }
    public boolean te_right() {
        return (right != null);
    }
    public boolean es_fulla() {
        return (left == null & right == null);
    }
    
    public void negar() {
        tok.negar();
    }
    
    public Token get_token() {
        return tok;
    } 
    public Node get_left() {
        return left;
    }
    public Node get_right() {
        return right;
    }
    
    public void set_token(Token t) {
        tok = t;
    }
    public void create_left_child(Node e) {
        left = e;
    }
    public void create_right_child(Node d) {
        right = d;
    }
    // considerar la versio booleana, que diu si s'ha pogut crear o no
    
    public void imprimir() throws Exception {
        
        if (this.es_fulla()) {
            if(this.tok.negat())        System.out.print('!');
            if (this.tok.es_operador()) System.out.print(this.tok.get_op());
            else                        System.out.print(this.tok.get_exp());
        }
        else {
            if(this.tok.negat())        System.out.print('!');
            System.out.print(" [");
            this.left.imprimir();
            if (this.tok.es_operador()) System.out.print(this.tok.get_op());
            else                        System.out.print(this.tok.get_exp());
            this.right.imprimir();
            System.out.print("] ");
        }
        
    }
}