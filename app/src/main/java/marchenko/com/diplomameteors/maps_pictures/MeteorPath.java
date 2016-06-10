package marchenko.com.diplomameteors.maps_pictures;

import android.util.Log;

public class MeteorPath {
    private String TAG = "MY_TAG";
    private double numberOfQuadrant;
    private double xMiddleMeteorLine;
    private double yMiddleMeteorLine;

    static double b1_x;
    static double b2_x;
    static double b1_y;
    static double b2_y;


    public double getxMiddleMeteorLine() {
        return xMiddleMeteorLine;
    }

    public void setxMiddleMeteorLine(double xMiddleMeteorLine) {
        this.xMiddleMeteorLine = xMiddleMeteorLine;
    }

    public double getyMiddleMeteorLine() {
        return yMiddleMeteorLine;
    }

    public void setyMiddleMeteorLine(double yMiddleMeteorLine) {
        this.yMiddleMeteorLine = yMiddleMeteorLine;
    }

    public double getxBeginMeteorLine() {
        return b1_x;
    }

    public double getyBeginMeteorLine() {
        return b1_y;
    }

    public double getxEndMeteorLine() {
        return b2_x;
    }

    public double getyEndMeteorLine() {
        return b2_y;
    }


    /**
     * исчем координаты центра траектории метеора (х, у) для нанесения на карту
     */
    public void countMiddleMeteorPointCoord(double hourAngleInDegree, double declension, double numberOfQuadrant) {
        this.numberOfQuadrant = numberOfQuadrant;
        // находим точку в пикселях, через которую
        // сколько отступать влево от 6-ти в пикселях
        double part = MapProperties.HOR_RADIUS / 90.0 * declension;//575,195
        double radiusForNewArc = MapProperties.HOR_RADIUS - part;//333,805

        double k = (Math.tan(Math.toRadians(90 - hourAngleInDegree)));
        Log.d(TAG, "k = " + k);
        // 1) пусть зенит будет с координатами (0, 0).
        // уравнение прямой y=kx (1)
        // уравнение окружности y=x^2 + y^2 = radiusForNewArc^2; (2)
        // подставляем (1) в (2):
        // x^2 + k^2 * x^2 = radiusForNewArc^2; (3)
        // упрощаем
        // x^2 * (1 + k^2) = radiusForNewArc^2; (4)
        double x1, y1;
        double x2, y2;
        x1 = Math.sqrt(Math.pow(radiusForNewArc, 2) / (1 + Math.pow(k, 2))); //(5)
        x2 = -Math.sqrt(Math.pow(radiusForNewArc, 2) / (1 + Math.pow(k, 2))); //(6)
        Log.d(TAG, "x1 = " + x1 + "; x2 = " + x2);
        y1 = k * x1; //(7)
        y2 = k * x2; //(8)
        Log.d(TAG, "y1 = " + y1 + "; y2 = " + y2);
        // У нас два варианта точек, но мы знаем, в каком квадратне должна она лежать
        // после расчёта часового угла. Отталкиваясь от значения квадранта мы выбираем пару (х, у)
        //double x = 0, y = 0;
        chooseXYpairInRightQuadrant(x1, y1, x2, y2);

        // 2) расчитываем прорисовку, учитывая сдвиги пикселей по карте (ось у на карте увеличивается в противоположном направлении)
        setxMiddleMeteorLine(getxMiddleMeteorLine() + MapProperties.CENTER_X);
        setyMiddleMeteorLine(MapProperties.CENTER_Y - getyMiddleMeteorLine());
        Log.d(TAG, "xmiddle = " + getxMiddleMeteorLine() + " ymiddle = " + (int) getyMiddleMeteorLine());
        Log.d(TAG, "numberOfQuadrant="+numberOfQuadrant);
    }

    private void chooseXYpairInRightQuadrant(double x1, double y1, double x2, double y2) {
        if (numberOfQuadrant == 1) {
            if (x1 > 0 && y1 > 0) {
                setXY(x1, y1);
            } else if (x2 > 0 && y2 > 0) {
                setXY(x2, y2);
            } else {
                printErrorMess();
            }
        }
        if (numberOfQuadrant == 2) {
            if (x1 < 0 && y1 > 0) {
                setXY(x1, y1);
            } else if (x2 < 0 && y2 > 0) {
                setXY(x2, y2);
            } else {
                printErrorMess();
            }
        }
        if (numberOfQuadrant == 3) {
            if (x1 < 0 && y1 < 0) {
                setXY(x1, y1);
            } else if (x2 < 0 && y2 < 0) {
                setXY(x2, y2);
            } else {
                printErrorMess();
            }
        }

        if (numberOfQuadrant == 4) {
            if (x1 > 0 && y1 > 0) {
                setXY(x1, y1);
            } else if (x2 > 0 && y2 > 0) {
                setXY(x2, y2);
            } else {
                printErrorMess();
            }
        }
        // если центр на пересечении квадрантов 2 и 1
        if (numberOfQuadrant == 2.1) {
            if (y1 >= 0) {
                setXY(x1, y1);
            } else if (y2 >= 0) {
                setXY(x2, y2);
            } else {
                printErrorMess();
            }
        }
        // если центр на пересечении квадрантов 3 и 4
        if (numberOfQuadrant == 3.4) {
            if (y1 < 0) {
                setXY(x1, y1);
            } else if (y2 < 0) {
                setXY(x2, y2);
            } else {
                printErrorMess();
            }
        }
        // если центр на пересечении квадрантов 2 и 3
        if (numberOfQuadrant == 2.3) {
            if (x1 < 0) {
                setXY(x1, y1);
            } else if (x2 < 0) {
                setXY(x2, y2);
            } else {
                printErrorMess();
            }
        }
        // если центр на пересечении квадрантов 1 и 4
        if (numberOfQuadrant == 1.4) {
            if (x1 >= 0) {
                setXY(x1, y1);
            } else if (x2 >= 0) {
                setXY(x2, y2);
            } else {
                printErrorMess();
            }
        }
    }

    private void setXY(double newMidX, double newMidY) {
        setxMiddleMeteorLine(newMidX);
        setyMiddleMeteorLine(newMidY);
    }

    private void printErrorMess() {
        Log.d(TAG, "Должен быть " + numberOfQuadrant +
                " квадрант, но расчёты координат соответствуют другому расположению!");
    }

    public void countBeginEndMeteorPointCoord(double myP, double L) {
        double alpha = countAlpha(myP);
        // перевод градусной меры длины в пиксельную
        L = MapProperties.HOR_RADIUS * L / 90.0;

        b1_x = count_x_B1(xMiddleMeteorLine, L, alpha);
        b2_x = count_x_B2(xMiddleMeteorLine, L, alpha);
        b1_y = count_y_B1(yMiddleMeteorLine, L, alpha);
        b2_y = count_y_B2(yMiddleMeteorLine, L, alpha);

        double buf = 0;
        // 1 четверть
//        if (myP > 9 && myP < 12) {
//            if (b1_x < b2_x) {
//            } else {
//                changeXplaces(b1_x, b2_x);
//            }
//            if (b1_y > b2_y) {
//            } else {
//                changeYplaces(b1_y, b2_y);
//            }
//        }
//        // 2 четверть
//        if (myP > 0 && myP < 3) {
//            if (b1_x > b2_x) {
//            } else {
//                changeXplaces(b1_x, b2_x);
//            }
//            if (b1_y > b2_y) {
//            } else {
//                changeYplaces(b1_y, b2_y);
//            }
//        }
//        // 3 четверть
//        if (myP > 3 && myP < 6) {
//            if (b1_x > b2_x) {
//            } else {
//                changeXplaces(b1_x, b2_x);
//            }
//            if (b1_y < b2_y) {
//            } else {
//                changeYplaces(b1_y, b2_y);
//            }
//        }
//        // 4 четверть
//        if (myP > 3 && myP < 6) {
//            if (b1_x < b2_x) {
//            } else {
//                changeXplaces(b1_x, b2_x);
//            }
//            if (b1_y > b2_y) {
//            } else {
//                changeYplaces(b1_y, b2_y);
//            }
//        }
//
//        if (myP == 0) {
//            if (b1_y > b2_y) {
//            } else {
//                changeYplaces(b1_y, b2_y);
//            }
//        }
//        if (myP == 3) {
//            if (b1_x > b2_x) {
//            } else {
//                changeXplaces(b1_x, b2_x);
//            }
//        }
//        if (myP == 6) {
//            if (b1_y < b2_y) {
//            } else {
//                changeYplaces(b1_y, b2_y);
//            }
//        }
//        if (myP == 9) {
//            if (b1_x < b2_x) {
//            } else {
//                changeXplaces(b1_x, b2_x);
//            }
//        }
          Log.d(TAG,"b1_x = "+b1_x + "\t; b2_x = "+b2_x+";\nb1_y = "+b1_y + "\t; b2_y[i] = "+b2_y);
        Log.d(TAG, "L = "+ L);

    }


    public static double countAlpha(double p) {
        double alpha = (p * 30 + 90) % 360;
        return alpha;
    }

    static double count_x_B1(double x_c, double L, double Alpha) {
        return x_c + 0.5 * L * Math.cos(Math.toRadians(Alpha));
    }

    static double count_x_B2(double x_c, double L, double Alpha) {
        return x_c - 0.5 * L * Math.cos(Math.toRadians(Alpha));
    }

    static double count_y_B1(double y_c, double L, double Alpha) {
        return y_c - 0.5 * L * Math.sin(Math.toRadians(Alpha));
    }

    static double count_y_B2(double y_c, double L, double Alpha) {
        return y_c + 0.5 * L * Math.sin(Math.toRadians(Alpha));
    }

    private static void changeYplaces(double b1_y, double b2_y) {
        double buf = b1_y;
        b1_y = b2_y;
        b2_y = buf;
    }

    private static void changeXplaces(double b1_x, double b2_x) {
        double buf = b1_x;
        b1_x = b2_x;
        b2_x = buf;
    }
}
