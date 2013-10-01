/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calendar1;

/**
 *
 * @author jey
 */
public class BS_VyberDen extends BS_Base{
    BS_Den[] polozky;
    int maxPole=60;
    int pocetAktualizace=15;

    public BS_VyberDen(){
        polozky=new BS_Den[15];
        firstSeen=4;
        active=3;
        for(int i=0; i<15; i++){
            polozky[i]=new BS_Den(i);
        }
    }

    public void setVert(){
        pocetSeen=8;
        vert=true;
        if(polozky.length>8)
            lastSeen=(firstSeen+7)%polozky.length;
        else
            lastSeen=firstSeen+polozky.length-1;
        countPocetShown();
    }

    public void setHor(){
        vert=false;
    }

    public boolean posunDolu(){
        if(active<maxShownIndex)
            active++;
        else{
            if(lastSeen==polozky.length-1){
                return false;
            }
            firstSeen++;
            set();
        }
        return true;
    }

    public boolean posunNahoru(){
        if(active>0)
            active--;
        else{
            if(firstSeen==0){
                return false;
            }
            firstSeen--;
            set();
        }
        return true;
    }

}
