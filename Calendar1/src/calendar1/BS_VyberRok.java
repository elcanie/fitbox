/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calendar1;

/**
 *
 * @author jey
 */
public class BS_VyberRok extends BS_Base{
    BS_Rok[] polozky;
    int maxPole=30;
    int pocetAktualizace=5;

    public BS_VyberRok(){
        polozky=new BS_Rok[5];
        for(int i=0; i<5; i++){
            polozky[i]=new BS_Rok(i);
        }
        active=0;
        firstSeen=1;
    }

    public void setVert(){
        pocetSeen=2;
        vert=true;
        if(polozky.length>2)
            lastSeen=(firstSeen+1)%polozky.length;
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
