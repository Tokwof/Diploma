package marchenko.com.diplomameteors.transfer_objects.transfer_objects_avtomat_meteor_syst;

import android.util.Log;

import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Meteor;

public class HorizontalCoordinates {
    private String TAG = "MY_TAG";
    /**
     * Горизонтальные координаты:
     * 1) азимут (соответствует параметру P’)
     */
    private double azimuth;
    /**
     * Горизонтальные координаты:
     * 2) высота
     */
    private double h;


    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public boolean setAzimuth(double azimuth) {
        this.azimuth = azimuth;
        return true;
    }

    // Высота ( = 90 – Z , где Z – зенитное расстояние)
    public void countHeight(int zenit) {
        setH(90 - zenit);
    }

    /**
     * Расчёт астрономического азимута от севера
     * @param pprime P' в градусном значении
     */
    public void countAzimuth(double pprime) {
        Log.d(TAG, "pprime="+pprime);
        this.azimuth = pprime % 360;
        Log.d(TAG, "azimuth="+azimuth);
    }
}
