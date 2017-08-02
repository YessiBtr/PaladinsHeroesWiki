package pribadi.tugas.if314053.paladinsheroeswiki.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Champions;

/**
 * Created by Satya on 5/13/2017.
 */

public class ControllerDataChampions {
    Realm realm;

    public ControllerDataChampions(){
        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
    public void insertData(final int id, final String nama,final String role, final String ultimate,final String skills1, final String skills2, final String skills3, final String skills4, final String story, final String summary, final String image){
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Champions modelChampions = realm.createObject(Champions.class, id);
                    modelChampions.setNama(nama);
                    modelChampions.setRole(role);
                    modelChampions.setSkills1(skills1);
                    modelChampions.setSkills2(skills2);
                    modelChampions.setSkills3(skills3);
                    modelChampions.setSkills4(skills4);
                    modelChampions.setUltimate(ultimate);
                    modelChampions.setStory(story);
                    modelChampions.setSummary(summary);
                    modelChampions.setImage(image);

                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }


    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<Champions> jenisModel = realm.where(Champions.class).findAll();
            jenisModel.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

}




