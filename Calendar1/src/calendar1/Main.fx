package calendar1;

import java.lang.System;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

var colorStroke: Color = Color.RED;
var colorFill: Color = Color.WHITE;
var initState: Boolean = true;

def deskBarva=Color.BLACK;

var bs_VyberKal=new BS_VyberKal();
var bs_VyberTyden=new BS_VyberTyden();
var bs_VyberDen=new BS_VyberDen();
var bs_VyberMesic=new BS_VyberMesic();
var bs_VyberRok=new BS_VyberRok();



class Base{
    var text:String[];
    var barvaTextu:Color[];
    var barvaPozadi:Color[];
    var pocetPolozek:Number;
}

class VyberKalendare extends Base{
    var barvTextActive=Color.DARKRED;
    var barvPozadiActive=Color.WHITE;
    var barvTextPasive=Color.WHITE;
    var barvPozadiPasive=Color.BLACK;

    function posunDolu():Void{
        bs_VyberKal.posunDolu();
        nacti();
    }


    function posunNahoru():Void{
        bs_VyberKal.posunNahoru();
        nacti();
    }

    function setVert():Void{
        bs_VyberKal.setVert();
        nacti();
    }

    function setHor():Void{
    }

    function nacti():Void{
        delete text;
        delete barvaTextu;
        delete barvaPozadi;
        for(i in [bs_VyberKal.firstSeen..bs_VyberKal.lastSeen]){
            insert bs_VyberKal.polozky[i] into text;
        }
        for(i in [0..(bs_VyberKal.pocetSeen-1)]){
            if(i==bs_VyberKal.active){
                insert barvTextActive into barvaTextu;
                insert barvPozadiActive into barvaPozadi;
            }
            else{
                insert barvTextPasive into barvaTextu;
                insert barvPozadiPasive into barvaPozadi;
            }
        }

    }

}


class VyberTyden extends Base{
    var barvTextActive=Color.DARKRED;
    var barvPozadiActive=Color.WHITE;
    var barvTextPasive=Color.WHITE;
    var barvPozadiPasive=Color.DARKSLATEGREY;
    var tydny:BS_Tyden[];

    function setVert():Void{
        bs_VyberTyden.setVert();
        nacti();
        //println(barvaTextu);
    }

    function posunDolu():Void{
        bs_VyberTyden.posunDolu();
        nacti();
    }


    function posunNahoru():Void{
        bs_VyberTyden.posunNahoru();
        nacti();
    }


    function nacti(){
        delete tydny;
        delete barvaTextu;
        delete barvaPozadi;
        for(i in [bs_VyberTyden.firstSeen..bs_VyberTyden.lastSeen]){
            insert bs_VyberTyden.polozky[i] into tydny;
        }

        for(i in [0..(bs_VyberTyden.pocetSeen-1)]){
            if(i==bs_VyberTyden.active){
                insert barvTextActive into barvaTextu;
                insert barvPozadiActive into barvaPozadi;
            }
            else{
                insert barvTextPasive into barvaTextu;
                insert barvPozadiPasive into barvaPozadi;
            }
        }
    }

}


class VyberDen extends Base{
    var barvTextActive=Color.DARKRED;
    var barvPozadiActive=Color.WHITE;
    var barvTextPasive=Color.WHITE;
    var barvPozadiPasive=Color.DARKSLATEGREY;
    var dny:BS_Den[];

    function setVert():Void{
        bs_VyberDen.setVert();
        nacti();
        //println(barvaTextu);
    }

    function posunDolu():Void{
        bs_VyberDen.posunDolu();
        nacti();
    }


    function posunNahoru():Void{
        bs_VyberDen.posunNahoru();
        nacti();
    }

    function nacti(){
        delete dny;
        delete barvaTextu;
        delete barvaPozadi;
        for(i in [bs_VyberDen.firstSeen..bs_VyberDen.lastSeen]){
            insert bs_VyberDen.polozky[i] into dny;
        }

        for(i in [0..(bs_VyberDen.pocetSeen-1)]){
            if(i==bs_VyberDen.active){
                insert barvTextActive into barvaTextu;
                insert barvPozadiActive into barvaPozadi;
            }
            else{
                insert barvTextPasive into barvaTextu;
                insert barvPozadiPasive into barvaPozadi;
            }
        }
    }
}

class VyberMesic extends Base{
    var barvTextActive=Color.DARKRED;
    var barvPozadiActive=Color.WHITE;
    var barvTextPasive=Color.WHITE;
    var barvPozadiPasive=Color.DARKSLATEGREY;
    var mesice:BS_Mesic[];

    function setVert():Void{
        bs_VyberMesic.setVert();
        nacti();
        //println(barvaTextu);
    }

    function posunDolu():Void{
        bs_VyberMesic.posunDolu();
        nacti();
    }


    function posunNahoru():Void{
        bs_VyberMesic.posunNahoru();
        nacti();
    }

    function nacti(){
        delete mesice;
        delete barvaTextu;
        delete barvaPozadi;
        for(i in [bs_VyberMesic.firstSeen..bs_VyberMesic.lastSeen]){
            insert bs_VyberMesic.polozky[i] into mesice;
        }

        for(i in [0..(bs_VyberMesic.pocetSeen-1)]){
            if(i==bs_VyberMesic.active){
                insert barvTextActive into barvaTextu;
                insert barvPozadiActive into barvaPozadi;
            }
            else{
                insert barvTextPasive into barvaTextu;
                insert barvPozadiPasive into barvaPozadi;
            }
        }
    }
}

class VyberRok extends Base{
    var barvTextActive=Color.DARKRED;
    var barvPozadiActive=Color.WHITE;
    var barvTextPasive=Color.WHITE;
    var barvPozadiPasive=Color.DARKSLATEGREY;
    var roky:BS_Rok[];

    function setVert():Void{
        bs_VyberRok.setVert();
        nacti();
        //println(barvaTextu);
    }

    function posunDolu():Void{
        bs_VyberRok.posunDolu();
        nacti();
    }


    function posunNahoru():Void{
        bs_VyberRok.posunNahoru();
        nacti();
    }

    function nacti(){
        delete roky;
        delete barvaTextu;
        delete barvaPozadi;
        for(i in [bs_VyberRok.firstSeen..bs_VyberRok.lastSeen]){
            insert bs_VyberRok.polozky[i] into roky;
        }

        for(i in [0..(bs_VyberRok.pocetSeen-1)]){
            if(i==bs_VyberRok.active){
                insert barvTextActive into barvaTextu;
                insert barvPozadiActive into barvaPozadi;
            }
            else{
                insert barvTextPasive into barvaTextu;
                insert barvPozadiPasive into barvaPozadi;
            }
        }
    }
}


var vyberCal=VyberKalendare{};
var vyberTyden=VyberTyden{};
var vyberDen=VyberDen{};
var vyberMesic=VyberMesic{};
var vyberRok=VyberRok{};



function zmenPlochu() : Void{
    if(vert){
        vert=false;
        scenaCislo++;
    }
    else{
        vert=true;
        scenaCislo--;
    }
    najdiPlochu();
}

function najdiPlochu() : Void{
    if(scenaCislo==0)
        scene=prihlaseniVert
    else if(scenaCislo==1)
        scene=prihlaseniHor
    else if(scenaCislo==2){
        vyberCal.setVert();
        scene=vyberCalVert;
    }
    else if(scenaCislo==3){
        vyberCal.setHor();
        scene=vyberCalHor;
    }
    else if (scenaCislo==4){
        vyberRok.setVert();
        scene=rokVert;
    }
    else if(scenaCislo==6){
        vyberMesic.setVert();
        scene=mesicVert;
    }
    else if(scenaCislo==8){
        vyberTyden.setVert();
        scene=tydenVert;
    }
    else if(scenaCislo==10){
        vyberDen.setVert();
        scene=denVert;
    }
    else if(scenaCislo==12){
        scene=denPodrobVert;
    }

}


var barva1=Color.WHITE;
var isOne=true;
var barva2=deskBarva;



def tlZmenPlochuVert:Group=Group{
    content:[
        Rectangle{
            x: 5
            y: 295
            arcWidth: 10
            arcHeight: 10
            width: 90
            height: 20
            fill: Color.DARKRED
        },
        Text{ x:10 y: 310 fill: Color.WHITE content: "Zmen plochu"}
    ]
    onMousePressed: function( e: MouseEvent ):Void {
        zmenPlochu()
    }

};

def tlZmenPlochuHor:Group=Group{
    content:[
        Rectangle{
            x: -30
            y: 40
            arcWidth: 10
            arcHeight: 10
            width: 90
            height: 20
            fill: Color.DARKRED
        },
        Text{ x:-25 y: 55 fill: Color.WHITE content: "Zmen plochu"}
    ]
    rotate: 90
    onMousePressed: function( e: MouseEvent ):Void {
        zmenPlochu()
    }

};

def tlZmenKalendarVert:Group=Group{
    content:[
        Rectangle{
            x: 135
            y: 295
            arcWidth: 10
            arcHeight: 10
            width: 100
            height: 20
            fill: Color.DARKRED
        },
        Text{ x:140 y: 310 fill: Color.WHITE content: "Zmen kalendar"}
    ]
    onMousePressed: function( e: MouseEvent ):Void {
        scenaCislo=2;
        najdiPlochu();
    }

}



var vyberCalHor=Scene{
    fill: deskBarva
    content: tlZmenPlochuHor
}


var vyberCalVert=Scene{
    fill: deskBarva
    content:[
        Group{
            content:[
                Rectangle{
                    x: 5
                    y: 12
                    width: 230
                    height: 25
                    arcWidth: 12
                    arcHeight: 12
                    fill: LinearGradient{
                        startX: 0.0
                        startY: 0.0
                        endX: 1.0
                        endY: 1.0
                        stops: [
                            Stop {offset: 0.0 color: Color.DARKCYAN}
                            Stop {offset: 1.0 color: deskBarva}
                        ]
                    }

                },
                Text{
                    x: 10
                    y: 30
                    fill: Color.WHITE
                    content: "Vyberte kalendar"
                }
            ]
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[0]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[0]
                    content: bind vyberCal.text[0]
                    x: 15
                    y: 60
                }
            ]
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[1]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[1]
                    content: bind vyberCal.text[1]
                    x: 15
                    y: 60
                }
            ]
            translateY: 25
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[2]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[2]
                    content: bind vyberCal.text[2]
                    x: 15
                    y: 60
                }
            ]
            translateY: 50
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[3]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[3]
                    content: bind vyberCal.text[3]
                    x: 15
                    y: 60
                }
            ]
            translateY: 75
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[4]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[4]
                    content: bind vyberCal.text[4]
                    x: 15
                    y: 60
                }
            ]
            translateY: 100
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[5]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[5]
                    content: bind vyberCal.text[5]
                    x: 15
                    y: 60
                }
            ]
            translateY: 125
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[6]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[6]
                    content: bind vyberCal.text[6]
                    x: 15
                    y: 60
                }
            ]
            translateY: 150
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[7]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[7]
                    content: bind vyberCal.text[7]
                    x: 15
                    y: 60
                }
            ]
            translateY: 175
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[8]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[8]
                    content: bind vyberCal.text[8]
                    x: 15
                    y: 60
                }
            ]
            translateY: 200
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[9]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[9]
                    content: bind vyberCal.text[9]
                    x: 15
                    y: 60
                }
            ]
            translateY: 225
        },
        Polygon {
            //nahoru
            points : [ 215,50, 210,65, 220,65 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberCal.posunNahoru();
            }
        },
        Polygon {
            //dolu
            points : [ 215,285, 210,270, 220,270 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberCal.posunDolu();
            }
        },
        Polygon {
            //oddalit
            points : [ 203,167, 213,155, 213,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=0;
                najdiPlochu();
            }
        },
        Polygon {
            //priblizit
            points : [ 227,167, 217,155, 217,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=8;
                najdiPlochu();
            }
        },
        tlZmenPlochuVert
    ]
}


var tydenVert=Scene{
    fill:deskBarva
    content:[
        Group{
            content:[
                Rectangle{
                    x: 5
                    y: 12
                    width: 230
                    height: 25
                    arcWidth: 12
                    arcHeight: 12
                    fill: LinearGradient{
                        startX: 0.0
                        startY: 0.0
                        endX: 1.0
                        endY: 1.0
                        stops: [
                            Stop {offset: 0.0 color: Color.DARKCYAN}
                            Stop {offset: 1.0 color: deskBarva}
                        ]
                    }

                },
                Text{
                    x: 10
                    y: 30
                    fill: Color.WHITE
                    content: "Prehled tydnu"
                }
            ]
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 75
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberTyden.barvaPozadi[0]
                },
                Text{
                    font: Font.font(null, FontWeight.BOLD, 10)
                    fill: bind vyberTyden.barvaTextu[0]
                    content: bind vyberTyden.tydny[0].datum
                    x: 15
                    y: 65
                    underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: "Po:"
                    x: 15
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: bind vyberTyden.tydny[0].po
                    x: 35
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: "Ut:"
                    x: 15
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: bind vyberTyden.tydny[0].ut
                    x: 35
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: "St:"
                    x: 15
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: bind vyberTyden.tydny[0].st
                    x: 35
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: "Ct:"
                    x: 95
                    y: 65
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: bind vyberTyden.tydny[0].ct
                    x: 115
                    y: 65
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: "Pa:"
                    x: 95
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: bind vyberTyden.tydny[0].pa
                    x: 115
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: "So:"
                    x: 95
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: bind vyberTyden.tydny[0].so
                    x: 115
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: "Ne:"
                    x: 95
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[0]
                    content: bind vyberTyden.tydny[0].ne
                    x: 115
                    y: 110
                }
            ]
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 75
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberTyden.barvaPozadi[1]
                },
                Text{
                    font: Font.font(null, FontWeight.BOLD, 10)
                    fill: bind vyberTyden.barvaTextu[1]
                    content: bind vyberTyden.tydny[1].datum
                    x: 15
                    y: 65
                    underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: "Po:"
                    x: 15
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: bind vyberTyden.tydny[1].po
                    x: 35
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: "Ut:"
                    x: 15
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: bind vyberTyden.tydny[1].ut
                    x: 35
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: "St:"
                    x: 15
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: bind vyberTyden.tydny[1].st
                    x: 35
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: "Ct:"
                    x: 95
                    y: 65
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: bind vyberTyden.tydny[1].ct
                    x: 115
                    y: 65
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: "Pa:"
                    x: 95
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: bind vyberTyden.tydny[1].pa
                    x: 115
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: "So:"
                    x: 95
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: bind vyberTyden.tydny[1].so
                    x: 115
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: "Ne:"
                    x: 95
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[1]
                    content: bind vyberTyden.tydny[1].ne
                    x: 115
                    y: 110
                }
            ]
            translateY: 80
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 75
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberTyden.barvaPozadi[2]
                },
                Text{
                    font: Font.font(null, FontWeight.BOLD, 10)
                    fill: bind vyberTyden.barvaTextu[2]
                    content: bind vyberTyden.tydny[2].datum
                    x: 15
                    y: 65
                    underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: "Po:"
                    x: 15
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: bind vyberTyden.tydny[2].po
                    x: 35
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: "Ut:"
                    x: 15
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: bind vyberTyden.tydny[2].ut
                    x: 35
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: "St:"
                    x: 15
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: bind vyberTyden.tydny[2].st
                    x: 35
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: "Ct:"
                    x: 95
                    y: 65
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: bind vyberTyden.tydny[2].ct
                    x: 115
                    y: 65
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: "Pa:"
                    x: 95
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: bind vyberTyden.tydny[2].pa
                    x: 115
                    y: 80
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: "So:"
                    x: 95
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: bind vyberTyden.tydny[2].so
                    x: 115
                    y: 95
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: "Ne:"
                    x: 95
                    y: 110
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberTyden.barvaTextu[2]
                    content: bind vyberTyden.tydny[2].ne
                    x: 115
                    y: 110
                }
            ]
            translateY: 160
        },
        Polygon {
            //nahoru
            points : [ 215,50, 210,65, 220,65 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberTyden.posunNahoru();
            }

        },
        Polygon {
            //dolu
            points : [ 215,285, 210,270, 220,270 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberTyden.posunDolu();
            }
        },
        Polygon {
            //oddalit
            points : [ 203,167, 213,155, 213,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=6;
                najdiPlochu();
            }
        },
        Polygon {
            //priblizit
            points : [ 227,167, 217,155, 217,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=10;
                najdiPlochu();
            }
        },
        tlZmenPlochuVert,
        tlZmenKalendarVert
    ]
};


var tydenHor=Scene{

};

var denVert=Scene{
    fill: deskBarva
    content:[
        Group{
            content:[
                Rectangle{
                    x: 5
                    y: 12
                    width: 230
                    height: 25
                    arcWidth: 12
                    arcHeight: 12
                    fill: LinearGradient{
                        startX: 0.0
                        startY: 0.0
                        endX: 1.0
                        endY: 1.0
                        stops: [
                            Stop {offset: 0.0 color: Color.DARKCYAN}
                            Stop {offset: 1.0 color: deskBarva}
                        ]
                    }

                },
                Text{
                    x: 10
                    y: 30
                    fill: Color.WHITE
                    content: "Prehled dni"
                }
            ]
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 23
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberDen.barvaPozadi[0]
                },
                Text{
                    fill: bind vyberDen.barvaTextu[0]
                    content: bind vyberDen.dny[0].datum
                    font: Font.font(null, FontWeight.BOLD, 10)
                    underline: true
                    x: 15
                    y: 65
                },
                Text{
                    fill: bind vyberDen.barvaTextu[0]
                    content: bind vyberDen.dny[0].akce
                    font: Font { size: 10 }
                    x: 120
                    y: 65
                }
            ]
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 23
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberDen.barvaPozadi[1]
                },
                Text{
                    fill: bind vyberDen.barvaTextu[1]
                    content: bind vyberDen.dny[1].datum
                    font: Font.font(null, FontWeight.BOLD, 10)
                    underline: true
                    x: 15
                    y: 65
                },
                Text{
                    fill: bind vyberDen.barvaTextu[1]
                    content: bind vyberDen.dny[1].akce
                    font: Font { size: 10 }
                    x: 120
                    y: 65
                }
            ]
            translateY: 30
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 23
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberDen.barvaPozadi[2]
                },
                Text{
                    fill: bind vyberDen.barvaTextu[2]
                    content: bind vyberDen.dny[2].datum
                    font: Font.font(null, FontWeight.BOLD, 10)
                    underline: true
                    x: 15
                    y: 65
                },
                Text{
                    fill: bind vyberDen.barvaTextu[2]
                    content: bind vyberDen.dny[2].akce
                    font: Font { size: 10 }
                    x: 120
                    y: 65
                }
            ]
            translateY: 60
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 23
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberDen.barvaPozadi[3]
                },
                Text{
                    fill: bind vyberDen.barvaTextu[3]
                    content: bind vyberDen.dny[3].datum
                    font: Font.font(null, FontWeight.BOLD, 10)
                    underline: true
                    x: 15
                    y: 65
                },
                Text{
                    fill: bind vyberDen.barvaTextu[3]
                    content: bind vyberDen.dny[3].akce
                    font: Font { size: 10 }
                    x: 120
                    y: 65
                }
            ]
            translateY: 90
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 23
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberDen.barvaPozadi[4]
                },
                Text{
                    fill: bind vyberDen.barvaTextu[4]
                    content: bind vyberDen.dny[4].datum
                    font: Font.font(null, FontWeight.BOLD, 10)
                    underline: true
                    x: 15
                    y: 65
                },
                Text{
                    fill: bind vyberDen.barvaTextu[4]
                    content: bind vyberDen.dny[4].akce
                    font: Font { size: 10 }
                    x: 120
                    y: 65
                }
            ]
            translateY: 120
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 23
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberDen.barvaPozadi[5]
                },
                Text{
                    fill: bind vyberDen.barvaTextu[5]
                    content: bind vyberDen.dny[5].datum
                    font: Font.font(null, FontWeight.BOLD, 10)
                    underline: true
                    x: 15
                    y: 65
                },
                Text{
                    fill: bind vyberDen.barvaTextu[5]
                    content: bind vyberDen.dny[5].akce
                    font: Font { size: 10 }
                    x: 120
                    y: 65
                }
            ]
            translateY: 150
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 23
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberDen.barvaPozadi[6]
                },
                Text{
                    fill: bind vyberDen.barvaTextu[6]
                    content: bind vyberDen.dny[6].datum
                    font: Font.font(null, FontWeight.BOLD, 10)
                    underline: true
                    x: 15
                    y: 65
                },
                Text{
                    fill: bind vyberDen.barvaTextu[6]
                    content: bind vyberDen.dny[6].akce
                    font: Font { size: 10 }
                    x: 120
                    y: 65
                }
            ]
            translateY: 180
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 23
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberDen.barvaPozadi[7]
                },
                Text{
                    fill: bind vyberDen.barvaTextu[7]
                    content: bind vyberDen.dny[7].datum
                    font: Font.font(null, FontWeight.BOLD, 10)
                    underline: true
                    x: 15
                    y: 65
                },
                Text{
                    fill: bind vyberDen.barvaTextu[7]
                    content: bind vyberDen.dny[7].akce
                    font: Font { size: 10 }
                    x: 120
                    y: 65
                }
            ]
            translateY: 210
        },
        Polygon {
            //nahoru
            points : [ 215,50, 210,65, 220,65 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberDen.posunNahoru();
            }

        },
        Polygon {
            //dolu
            points : [ 215,285, 210,270, 220,270 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberDen.posunDolu();
            }
        },
        Polygon {
            //oddalit
            points : [ 203,167, 213,155, 213,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=8;
                najdiPlochu();
            }
        },
        Polygon {
            //priblizit
            points : [ 227,167, 217,155, 217,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=12;
                najdiPlochu();
            }
        },
        tlZmenPlochuVert,
        tlZmenKalendarVert
    ]
}



var prihlaseniVert=Scene {
    fill: deskBarva
    content: [
        Group{
            content:[
                Rectangle{
                    x: 5
                    y: 12
                    width: 230
                    height: 25
                    arcWidth: 12
                    arcHeight: 12
                    fill: LinearGradient{
                        startX: 0.0
                        startY: 0.0
                        endX: 1.0
                        endY: 1.0
                        stops: [
                            Stop {offset: 0.0 color: Color.DARKCYAN}
                            Stop {offset: 1.0 color: deskBarva}
                        ]
                    }

                },
                Text{
                    x: 10
                    y: 30
                    fill: Color.WHITE
                    content: "Prihlaseni do Google kalendar"
                }

            ]
            onMousePressed: function( e: MouseEvent ):Void {
                if(isOne){
                    barva1=deskBarva;
                    barva2=Color.WHITE;
                    isOne=false;
                }else{
                    barva1=Color.WHITE;
                    barva2=deskBarva;
                    isOne=true;
                }
            }

        },
        Text{
            x: 10
            y: 55
            fill: Color.WHITE
            content: "Email:"
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 60
                    width: 160
                    height: 20
                    fill: Color.DARKCYAN
                    stroke: bind barva1
                }

            ]
        },
        Text{
            x: 10
            y: 95
            fill: Color.WHITE
            content: "Heslo:"
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 100
                    width: 160
                    height: 20
                    fill: Color.DARKCYAN
                    stroke: bind barva2
                }

            ]
        },
        Group{
            //tlacitko pro prihlaseni
            content:[
                Rectangle{
                    x: 10
                    y: 125
                    width: 60
                    height: 20
                    arcWidth: 10
                    arcHeight: 10
                    fill: Color.DARKRED
                },
                Text{
                    fill: Color.WHITE
                    content: "prihlasit"
                    x: 15
                    y: 139
                }

            ]
            onMouseClicked: function( e: MouseEvent ):Void {
                scenaCislo=2;
                najdiPlochu();
            }
        },
        tlZmenPlochuVert
    ]
};//Scene

var prihlaseniHor=Scene {
    fill: Color.BLACK
    content:[
        Group{
            content:[
                Rectangle{
                    x: 65
                    y: 147
                    width: 310
                    height: 25
                    arcWidth: 12
                    arcHeight: 12
                    fill: LinearGradient{
                        startX: 0.0
                        startY: 0.0
                        endX: 1.0
                        endY: 1.0
                        stops: [
                            Stop {offset: 0.0 color: Color.DARKCYAN}
                            Stop {offset: 1.0 color: deskBarva}
                        ]
                    }

                },
                Text{
                    x: 70
                    y: 165
                    fill: Color.WHITE
                    content: "Prihlaseni do Google kalendar"
                }

            ]
            rotate: 90
        },
        tlZmenPlochuHor
    ]
};//Scene

var denPodrobVert=Scene{
    fill: deskBarva
    content:[
        Group{
            content:[
                Rectangle{
                    x: 5
                    y: 12
                    width: 230
                    height: 25
                    arcWidth: 12
                    arcHeight: 12
                    fill: LinearGradient{
                        startX: 0.0
                        startY: 0.0
                        endX: 1.0
                        endY: 1.0
                        stops: [
                            Stop {offset: 0.0 color: Color.DARKCYAN}
                            Stop {offset: 1.0 color: deskBarva}
                        ]
                    }

                },
                Text{
                    x: 10
                    y: 30
                    fill: Color.WHITE
                    content: "Pondeli 1.12.2009"
                }
            ]
        },
        Group{
            content:[
                Rectangle{
                    x: 10
                    y: 45
                    width: 160
                    height: 20
                    fill: bind vyberCal.barvaPozadi[0]
                },
                Text{
                    fill: bind vyberCal.barvaTextu[0]
                    content: "ajshdakjshda dsj lfhalfh weafhkjhalsdaslf jsdfh d"
                    x: 15
                    y: 60
                }
            ]
        },
        tlZmenPlochuVert
    ]
};

var mesicVert=Scene{
    fill: deskBarva
    content:[
        Group{
            content:[
                Rectangle{
                    x: 5
                    y: 12
                    width: 230
                    height: 25
                    arcWidth: 12
                    arcHeight: 12
                    fill: LinearGradient{
                        startX: 0.0
                        startY: 0.0
                        endX: 1.0
                        endY: 1.0
                        stops: [
                            Stop {offset: 0.0 color: Color.DARKCYAN}
                            Stop {offset: 1.0 color: deskBarva}
                        ]
                    }
                },
                Text{
                    x: 10
                    y: 30
                    fill: Color.WHITE
                    content: "Prehled mesicu"
                }
            ]
        },
        Group{
            content: [
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 110
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberMesic.barvaPozadi[0]
                },
                Text{
                    font: Font.font(null, FontWeight.BOLD, 10)
                    fill: bind vyberMesic.barvaTextu[0]
                    content: bind vyberMesic.mesice[0].nazev
                    x: 15
                    y: 65
                    underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[0]
                    content: bind vyberMesic.mesice[0].prvni
                    x: 15
                    y: 80
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[0]
                    content: bind vyberMesic.mesice[0].druhy
                    x: 15
                    y: 95
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[0]
                    content: bind vyberMesic.mesice[0].treti
                    x: 15
                    y: 110
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[0]
                    content: bind vyberMesic.mesice[0].ctvrty
                    x: 15
                    y: 125
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[0]
                    content: bind vyberMesic.mesice[0].paty
                    x: 15
                    y: 140
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[0]
                    content: bind vyberMesic.mesice[0].sesty
                    x: 15
                    y: 155
                    //underline: true
                }
            ]
        },
        Group{
            content: [
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 110
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberMesic.barvaPozadi[1]
                },
                Text{
                    font: Font.font(null, FontWeight.BOLD, 10)
                    fill: bind vyberMesic.barvaTextu[1]
                    content: bind vyberMesic.mesice[1].nazev
                    x: 15
                    y: 65
                    underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[1]
                    content: bind vyberMesic.mesice[1].prvni
                    x: 15
                    y: 80
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[1]
                    content: bind vyberMesic.mesice[1].druhy
                    x: 15
                    y: 95
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[1]
                    content: bind vyberMesic.mesice[1].treti
                    x: 15
                    y: 110
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[1]
                    content: bind vyberMesic.mesice[1].ctvrty
                    x: 15
                    y: 125
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[1]
                    content: bind vyberMesic.mesice[1].paty
                    x: 15
                    y: 140
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberMesic.barvaTextu[1]
                    content: bind vyberMesic.mesice[1].sesty
                    x: 15
                    y: 155
                    //underline: true
                }
            ]
            translateY: 120
        },
        Polygon {
            //nahoru
            points : [ 215,50, 210,65, 220,65 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberMesic.posunNahoru();
            }

        },
        Polygon {
            //dolu
            points : [ 215,285, 210,270, 220,270 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberMesic.posunDolu();
            }
        },
        Polygon {
            //oddalit
            points : [ 203,167, 213,155, 213,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=4;
                najdiPlochu();
            }
        },
        Polygon {
            //priblizit
            points : [ 227,167, 217,155, 217,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=8;
                najdiPlochu();
            }
        },
        tlZmenPlochuVert,
        tlZmenKalendarVert
    ]
}

var rokVert=Scene{
    fill: deskBarva
    content:[
        Group{
            content:[
                Rectangle{
                    x: 5
                    y: 12
                    width: 230
                    height: 25
                    arcWidth: 12
                    arcHeight: 12
                    fill: LinearGradient{
                        startX: 0.0
                        startY: 0.0
                        endX: 1.0
                        endY: 1.0
                        stops: [
                            Stop {offset: 0.0 color: Color.DARKCYAN}
                            Stop {offset: 1.0 color: deskBarva}
                        ]
                    }
                },
                Text{
                    x: 10
                    y: 30
                    fill: Color.WHITE
                    content: "Prehled roku"
                }
            ]
        },
        Group{
            content: [
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 110
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberRok.barvaPozadi[0]
                },
                Text{
                    font: Font.font(null, FontWeight.BOLD, 10)
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].nazev
                    x: 15
                    y: 65
                    underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].leden
                    x: 15
                    y: 80
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].unor
                    x: 15
                    y: 95
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].brezen
                    x: 15
                    y: 110
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].duben
                    x: 15
                    y: 125
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].kveten
                    x: 15
                    y: 140
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].cerven
                    x: 15
                    y: 155
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].cervenec
                    x: 100
                    y: 80
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].srpen
                    x: 100
                    y: 95
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].zari
                    x: 100
                    y: 110
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].rijen
                    x: 100
                    y: 125
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].listopad
                    x: 100
                    y: 140
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[0]
                    content: bind vyberRok.roky[0].prosinec
                    x: 100
                    y: 155
                    //underline: true
                }
            ]
        },
        Group{
            content: [
                Rectangle{
                    x: 10
                    y: 50
                    width: 180
                    height: 110
                    arcWidth: 20
                    arcHeight: 20
                    fill: bind vyberRok.barvaPozadi[1]
                },
                Text{
                    font: Font.font(null, FontWeight.BOLD, 10)
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].nazev
                    x: 15
                    y: 65
                    underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].leden
                    x: 15
                    y: 80
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].unor
                    x: 15
                    y: 95
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].brezen
                    x: 15
                    y: 110
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].duben
                    x: 15
                    y: 125
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].kveten
                    x: 15
                    y: 140
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].cerven
                    x: 15
                    y: 155
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].cervenec
                    x: 100
                    y: 80
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].srpen
                    x: 100
                    y: 95
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].zari
                    x: 100
                    y: 110
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].rijen
                    x: 100
                    y: 125
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].listopad
                    x: 100
                    y: 140
                    //underline: true
                },
                Text{
                    font: Font { size: 10}
                    fill: bind vyberRok.barvaTextu[1]
                    content: bind vyberRok.roky[1].prosinec
                    x: 100
                    y: 155
                    //underline: true
                }
            ]
            translateY: 120
        },
        Polygon {
            //nahoru
            points : [ 215,50, 210,65, 220,65 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberRok.posunNahoru();
            }

        },
        Polygon {
            //dolu
            points : [ 215,285, 210,270, 220,270 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                vyberRok.posunDolu();
            }
        },
        Polygon {
            //priblizit
            points : [ 227,167, 217,155, 217,180 ]
            fill: Color.DARKRED
            onMousePressed: function( e: MouseEvent ):Void {
                scenaCislo=6;
                najdiPlochu();
            }
        },
        tlZmenPlochuVert,
        tlZmenKalendarVert
    ]
}




var scene;
var scenaCislo=8;
var vert=true;

najdiPlochu();

Stage {
    title: "Kalendar"
    width: 240
    height: 320
    scene: bind scene

}//Stage