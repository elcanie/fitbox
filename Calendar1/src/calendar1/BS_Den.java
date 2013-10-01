/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calendar1;

/**
 *
 * @author jey
 */
public class BS_Den {
    String datum;
    String akce;

    public BS_Den(int i){
        if(i%2==0){
            datum="Pondeli 12.12.2009:";
            akce="2 akce";
        }
        else{
            datum="Sobota 1.1.2122:";
            akce="85 akci";
        }
    }
}
