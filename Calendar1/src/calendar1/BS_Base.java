/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calendar1;

/**
 *
 * @author jey
 */
abstract public class BS_Base {

    int active;
    int firstSeen;
    int lastSeen;
    int maxShownIndex; //rozdil mezi fisrt a last
    int pocetSeen;
    boolean vert;

    protected void countPocetShown(){
        //TODO: dodelat else pri zjistovani poctu zobrazenych polozek
        if(lastSeen>=firstSeen)
            maxShownIndex=lastSeen-firstSeen;
    }

    protected void set(){
        if(vert)
            setVert();
        else
            setHor();
    }

    abstract public void setVert();
    abstract public void setHor();

}
