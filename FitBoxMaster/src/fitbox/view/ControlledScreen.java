/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.view;

import fitbox.controller.ScreensController;

/**
 *
 * @author RUBEN
 */
public interface ControlledScreen {
    //Permite la injeccion del padre

    public void setScreenParent(ScreensController screenParent);

}
