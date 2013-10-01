/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calendar1;

/**
 *
 * @author jey
 */
public class BS_VyberKal extends BS_Base{
    String [] polozky;

    public BS_VyberKal(){
        polozky=new String[15];
        for(int i=0; i<15; i++){
            polozky[i]="kalendar "+i;
        }
        active=0;
        firstSeen=0;
        lastSeen=0;
        maxShownIndex=0;
    }

    public void setVert(){
        pocetSeen=10;
        vert=true;
        if(polozky.length>10)
            lastSeen=(firstSeen+9)%polozky.length;
        else
            lastSeen=firstSeen+polozky.length-1;
        countPocetShown();
    }
    
    public void setHor(){
        vert=false;
    }

    public void posunDolu(){
        if(active<maxShownIndex)
            active++;
        else{
            if(lastSeen==(polozky.length-1)){
                active=0;
                if(firstSeen!=0){
                    firstSeen=0;
                    set();
                }
            }
            else{
                firstSeen++;
                set();
            }
        }
    }

    public void posunNahoru(){
        if(active>0)
            active--;
        else{
            if(firstSeen==0){
                if(polozky.length>pocetSeen){
                    active=pocetSeen-1;
                    firstSeen=polozky.length-pocetSeen;
                    set();
                }
                else{
                    active=lastSeen;
                }
            }
            else{
                firstSeen--;
                set();
            }
        }
    }
}
