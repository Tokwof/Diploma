package marchenko.com.mydiplomaormlite;

import android.app.Application;

import marchenko.com.mydiplomaormlite.mydb.DatabaseManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DatabaseManager.getInstance().release();
    }
}
