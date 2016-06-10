package marchenko.com.diplomameteors.maps_pictures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import marchenko.com.diplomameteors.R;
import marchenko.com.diplomameteors.ormsqlite_dao.OrmSqliteMeteorDao;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.EquatorialСoordinate;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Meteor;

public class DrawingSaving {

    private Context context;

    public DrawingSaving(Context current){
        this.context = current;
    }

    public void drawingLines(List<EquatorialСoordinate> eqCoordsList) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        // открываем чистую карту
        Bitmap bmpMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.map);
        // создаём новую чистую картинку
        Bitmap newBitmap = Bitmap.createBitmap(bmpMap.getWidth(), bmpMap.getHeight(), Bitmap.Config.RGB_565);
        // помещаем на холст чистую картинку
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        // рисуем на созданном холсте копию исходной карты
        canvas.drawBitmap(bmpMap, 0, 0, paint);

        OrmSqliteMeteorDao meteorDao = new OrmSqliteMeteorDao();


        for(EquatorialСoordinate eqCoord : eqCoordsList) {

           // double betaAngle;


            MeteorPath meteorPath = new MeteorPath();
            Meteor curMeteor = meteorDao.findMeteor(eqCoord.getMeteor().getId());


           // betaAngle=eqCoord.getHourAngleInDegree()

            meteorPath.countMiddleMeteorPointCoord(eqCoord.getHourAngleInDegree(), eqCoord.getDeclension(), eqCoord.getQuadrantNumber());
            meteorPath.countBeginEndMeteorPointCoord(curMeteor.getP(), curMeteor.getLength());
            canvas.drawLine((int) meteorPath.getxBeginMeteorLine(), (int) meteorPath.getyBeginMeteorLine(),
                    (int) meteorPath.getxEndMeteorLine(), (int) meteorPath.getyEndMeteorLine(), paint);
        }
        // сохраняем карту с прорисованными линиями
        savePictToStorage(newBitmap, MapProperties.NAME_RESULT_MAP);
    }

    private void savePictToStorage(Bitmap bitmap, String fileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
