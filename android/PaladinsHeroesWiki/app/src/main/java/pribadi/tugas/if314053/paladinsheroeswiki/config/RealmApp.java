package pribadi.tugas.if314053.paladinsheroeswiki.config;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Satya on 5/14/2017.
 */

public class RealmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm. init(this);
        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("realmsaya.realm")
                .schemaVersion( 1)
                .build();
        // Use the config
        Realm realm = Realm. getInstance(config);
    }
}
