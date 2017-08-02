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

public class ControllerDataPatch {
    Realm realm;

    public ControllerDataPatch(){
        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void insertData(final int id, final String judul, final String subjudul, final String general, final String url) {
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Patch modelPatch = realm.createObject(Patch.class, id);
                    modelPatch.setJudul(judul);
                    modelPatch.setSubjudul(subjudul);
                    modelPatch.setGeneral(general);
                    modelPatch.setUrl(url);
                }
            });
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<Patch> jenisModel = realm.where(Patch.class).findAll();
            jenisModel.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

}




