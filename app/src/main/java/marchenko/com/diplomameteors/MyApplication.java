package marchenko.com.diplomameteors;

import android.app.Application;

import marchenko.com.diplomameteors.mydb.DatabaseManager;

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
