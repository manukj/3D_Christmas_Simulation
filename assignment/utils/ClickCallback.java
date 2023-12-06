package utils;

/**
 *  I declare that this code is my own work
 *  Author: Manu Kenchappa Junjanna 
 */

/**
 * The ClickCallback interface defines the methods that can be called when
 * certain actions are triggered by a click event on the GUI.
 */
public interface ClickCallback {

    public void dimMainLight(int lightIndex);

    public void brightenMainLight(int lightIndex);

    public void toggleMainLight(int lightIndex);

    public void toggleSpotLight();

    public void dimSpotLight();

    public void brightenSpotLight();

    public void increaseRockSpeed(int alienIndex);

    public void decreaseRockSpeed(int alienIndex);

    public void increaseRollSpeed(int alienIndex);

    public void decreaseRollSpeed(int alienIndex);

    public void changeRollDirection(int alienIndex);

    public void toggleRockAnimation(int alienIndex);

    public void toggleRollAnimation(int alienIndex);

}