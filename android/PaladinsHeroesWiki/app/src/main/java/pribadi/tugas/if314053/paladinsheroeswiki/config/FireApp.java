package pribadi.tugas.if314053.paladinsheroeswiki.config;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Satya on 5/13/2017.
 */

public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
