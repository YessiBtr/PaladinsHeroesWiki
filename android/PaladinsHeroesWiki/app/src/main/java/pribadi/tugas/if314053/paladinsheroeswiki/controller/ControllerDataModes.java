package pribadi.tugas.if314053.paladinsheroeswiki.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Champions;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Modes;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Patch;

/**
 * Created by Satya on 5/13/2017.
 */

public class ControllerDataModes {
    Realm realm;

    public ControllerDataModes(){
        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void insertData(final int id, final String mode, final String description, final String image){
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Modes modelModes = realm.createObject(Modes.class, id);
                    modelModes.setMode(mode);
                    modelModes.setDescription(description);
                    modelModes.setImage(image);

                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<Modes> jenisModel = realm.where(Modes.class).findAll();
            jenisModel.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

}




