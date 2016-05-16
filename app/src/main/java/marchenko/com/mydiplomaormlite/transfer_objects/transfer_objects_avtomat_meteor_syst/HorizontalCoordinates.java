package marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_avtomat_meteor_syst;

import android.util.Log;

public class HorizontalCoordinates {
  //  Meteor meteor;
    /**
     * Горизонтальные координаты:
     * 1) азимут (соответствует параметру P’)
     */
    private double azimuth;
    /**
     * Горизонтальные координаты:
     * 2) высота
     */
 //   private double h;

 //   public HorizontalCoordinates(Meteor meteor) {
 //       this.meteor = meteor;
 //   }

//    public double getH() {
//        return h;
//    }

//    public void setH(double h) {
//        this.h = h;
//    }

    public double getAzimuth() {
        return azimuth;
    }

    public boolean setAzimuth(double azimuth) {
        this.azimuth = azimuth;
        return true;
    }

    // Высота ( = 90 – Z , где Z – зенитное расстояние)
//    public void countHeight() {
//        setH(90 - meteor.getZenit());
//        Log.d("MY_TAG", "h = " + h);
//    }

    /**
     * Расчёт астрономического азимута
     * @param pprime P' в градусном значении
     */
    public void countAzimuth(double pprime) {
        this.azimuth = (180 + pprime) % 360;

    }
}
