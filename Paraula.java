/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordocuments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author sergiokov20
 */
public class Paraula {
    private String p; //String que forma la paraula.
    
    public Paraula() {
        
    }
    
    public Paraula(String p) {
        this.p = p;
    }
    
    public String get_p() {
        return p;
    }
    
    public void set_p(String p) {
        this.p = p;
    }
    
    public void imprimir() {
        System.out.println(p);
    }
    
    public boolean is_stop_word() throws FileNotFoundException, IOException {
        File file = new File("empty.cat");
        BufferedReader b = new BufferedReader(
            new InputStreamReader(new FileInputStream(file),"ISO-8859-1"));
        String aux;
        while ((aux = b.readLine()) != null) {
            if(p.equals(aux)) { 
                return true;
            }
        }
        return false;
    }
}
