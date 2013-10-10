/*
 *  CalendarJFX.fx - 
 *  The main program for a compiled JavaFX calendar program
 *
 *  Developed 2008 by James L. Weaver (jim.weaver at lat-inc.com)
 *  to serve as a compiled JavaFX Script example.
 */

import javafx.ui.*;
import javafx.ui.canvas.*;
import java.lang.System;

Frame {
  var calModel = 
    CalendarModel {}
  title: "CalendarJFX"
  width: 600
  height: 600
  visible: true
  content:
    BorderPanel {
      top:
        BorderPanel {
          left:
            FlowPanel {
              content: [
                Button {
                  text: "<<"
                  action:
                    function():Void {
                      calModel.prevYear();
                    }
                },
                Button {
                  text: "<"
                  action:
                    function():Void {
                      calModel.prevMonth();
                    }
                }
              ]
            }
          center:
            FlowPanel {
              content:
                SimpleLabel {
                  text: bind "{calModel.selectedMonthStr} {calModel.selectedYearStr}"
                  font:
                    Font {
                      size: 24
                      style: FontStyle.BOLD
                    }
                }
            }
          right:
            FlowPanel {
              content: [
                Button {
                  text: ">"
                  action:
                    function():Void {
                      calModel.nextMonth();
                    }
                },
                Button {
                  text: ">>"
                  action:
                    function():Void {
                      calModel.nextYear();
                    }
                }
              ]
            }
          bottom:
            GridPanel {
              // TODO: Internationalize days of the week
              var days = ["Sun", "Mon", "Tue", 
                          "Wed", "Thu", "Fri", "Sat"]
              rows: 1
              columns: 7
              cells:
                for (day in days) {
                  SimpleLabel {
                    text: day
                    font:
                      Font {
                        size: 18
                        style: FontStyle.BOLD
                      }
                    horizontalAlignment: 
                      HorizontalAlignment.CENTER
                  }
                }
            }
        }
      center:
        GridPanel {
          vgap: 1
          hgap: 1
          rows: 6
          columns: 7
          cells:
            for (idx in [1..42]) {
              CalendarCell {
                calModel: calModel
                dayOfMonthStr: bind calModel.getDayInMonthStrForCell(idx as Integer);
                cellGregCal: bind calModel.getDateForCell(idx as Integer);
              }
            }
        }
    }
  onClose:
    function():Void {
      System.exit(0);
    }  
}