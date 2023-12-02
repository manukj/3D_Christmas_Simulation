package utils;

/**
 *  I declare that this code is my own work
 *  Author: Manu Kenchappa Junjanna 
 */
public interface ClickCallback {

    public void dimMainLight();

    public void brightenMainLight();

    public void toggleMainLight();

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