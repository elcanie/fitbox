/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

/**
 *
 * @author RUBEN
 */
public class Recurso<T extends Object> extends ResourceBundle {

    Hashtable<String, T> recursos;

    public Recurso() {
        recursos = new Hashtable<String, T>();
    }

    @Override
    protected Object handleGetObject(String key) {
        return recursos.get(key); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enumeration<String> getKeys() {
        return recursos.keys();

    }

    public void putObject(String key, T objeto) {
        recursos.put(key, objeto);
    }
}
