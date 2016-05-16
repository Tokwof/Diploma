package marchenko.com.mydiplomaormlite.counters;

import android.util.Log;

public class CounterEquatorialCoordinate {
    public double getNumberOfQuadrant() {
        return numberOfQuadrant;
    }

    private double numberOfQuadrant;
 //   MeteorPath meteorPath = MeteorPath.getInstance();
    // перевод горизонтальных координат в экваториальные
    // склонение светила declension_delta
    public double countDeclension_delta(double latitude, int zenit, double azimut) {
        double sinDelta, delta;
        double sinFi = Math.sin(Math.toRadians(latitude));
        double cosFi = Math.cos(Math.toRadians(latitude));
        double sinZ = Math.sin(Math.toRadians(zenit));
        double cosZ = Math.cos(Math.toRadians(zenit));
        double cosA = Math.cos(Math.toRadians(azimut));

        sinDelta = sinFi * cosZ - cosFi * sinZ * cosA;
     //   Log.d("MY_TAG", "sinDelta = " + sinDelta);
        delta = Math.toDegrees(Math.asin(sinDelta));
        Log.d("MY_TAG", "delta = " + delta);
     //   Log.d("MY_TAG", "OTHER delta = " + (meteorProperties.getH() - 90 + meteorProperties.getLatitudeObservations_fi_degrees()));
       // equatorialСoordinate.setDeclension(delta);
        return delta;//equatorialСoordinate.getDeclension();
    }

    // определить часовой угол t
    public double countHourAngleInDegree(double latitude, int zenit, double azimut, double declension) {
        double t = 0;
        double sinT, t1;
        double sinZ = Math.sin(Math.toRadians(zenit));
        double sinA = Math.sin(Math.toRadians(azimut));
        double cosDelta = Math.cos(Math.toRadians(declension));
        sinT = (sinZ * sinA) / cosDelta;// эта формула хорошая для t=0 или =180
        t1 = Math.toDegrees(Math.asin(sinT));
        //////
        double cosT, t2;
        double cosFi = Math.cos(Math.toRadians(latitude));
        double sinFi = Math.sin(Math.toRadians(latitude));
        double cosA = Math.cos(Math.toRadians(azimut));
        double cosZ = Math.cos(Math.toRadians(zenit));
        cosT =  (cosFi * cosZ + sinFi * sinZ * cosA) / cosDelta;// эта формула хорошая для t=90 или =270
        t2 = Math.toDegrees(Math.acos(cosT));
        // выбираем t
        t = getRightT(t, sinT, t1, cosT, t2);
   //     Log.d("MY_TAG", "meteorProperties.getNumberOfQuadrant() = "+ meteorPath.getNumberOfQuadrant());
        // устанавливаем градусную меру
        // устанавливаем временную меру
     //   Log.d("MY_TAG", "sinT = " + sinT + " t1 = " + t1);
     //   Log.d("MY_TAG", "sinZ = " + sinZ + " sinA = " + sinA);
     //   Log.d("MY_TAG", "cosDelta = " + cosDelta + " cosT = " + cosT);
      //  Log.d("MY_TAG", "cosFi = " + cosFi + " sinFi = " + sinFi);
     //   Log.d("MY_TAG", "cosA = " + cosA + " cosZ = " + cosZ + " t2 = " + t2);

        //equatorialСoordinate.setHourAngleInHour(t / 15.0);
     //   Log.d("MY_TAG", "t degree  = " + t);
    //    Log.d("MY_TAG", "t hour  = " + equatorialСoordinate.getHourAngleInHour());
        return t;
    }

    public double countHourAngleInHour(double tInDegree) {
        return tInDegree / 15.0;
    }

    private double getRightT(double t, double sinT, double t1, double cosT, double t2) {
        if((sinT > 0) && (cosT > 0)) {//1 квадрант
            t = ((t1 > 0) && (t1 < 90)) ? t1 : t2;
            numberOfQuadrant = 1;
        }
        if((sinT > 0) && (cosT < 0)) {//2 квадрант
            t = ((t1 > 90) && (t1 < 180)) ? t1: t2;
            numberOfQuadrant = 2;
        }
        if((sinT < 0) && (cosT < 0)) {//3 квадрант
            t = ((t1 > 180) && (t1 < 270)) ? t1 : t2;
            numberOfQuadrant = 3;
        }
        if((sinT < 0) && (cosT > 0)) {//4 квадрант
            t = ((t1 > 270) && (t1 < 360)) ? t1 : t2;
            numberOfQuadrant = 4;
        }
        // если на границах квадратнтов
        if(sinT == 0) {
            t = (cosT < 0) ? 180 : 0;
            numberOfQuadrant = (cosT < 0) ? 2.3 : 1.4;
        }
        if(cosT == 0) {
            t = (sinT > 0) ? 90 : 270;
            numberOfQuadrant = (sinT > 0) ? 2.1 : 3.4;
        }
        return t;
    }

}
