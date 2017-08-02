package pribadi.tugas.if314053.paladinsheroeswiki;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import pribadi.tugas.if314053.paladinsheroeswiki.API.APIPaladins;
import pribadi.tugas.if314053.paladinsheroeswiki.API.RESTClientChampions;
import pribadi.tugas.if314053.paladinsheroeswiki.API.RESTClientModes;
import pribadi.tugas.if314053.paladinsheroeswiki.API.RESTClientPatch;
import pribadi.tugas.if314053.paladinsheroeswiki.API.ResponseChampions;
import pribadi.tugas.if314053.paladinsheroeswiki.API.ResponseModes;
import pribadi.tugas.if314053.paladinsheroeswiki.API.ResponsePatch;
import pribadi.tugas.if314053.paladinsheroeswiki.controller.ControllerDataChampions;
import pribadi.tugas.if314053.paladinsheroeswiki.controller.ControllerDataModes;
import pribadi.tugas.if314053.paladinsheroeswiki.controller.ControllerDataPatch;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Champions;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Modes;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Patch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashscreenActivity extends AppCompatActivity {

    Context myContext;
    ProgressDialog progress;
    APIPaladins ApiPaladins;

    Realm realm;

    private ProgressBar progressBar = null;
    private AsyncTaskRunner asyncTaskRunner = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Realm.init(this);

        myContext = getApplicationContext();
        progress = ProgressDialog. show(SplashscreenActivity. this, "Inisialisasi Data", "Sedang Mengunduh Data Untuk Aplikasi" , true);

        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        progressBar = (ProgressBar)
                findViewById(R.id. progressBar);

        progressBar.getProgressDrawable().setColorFilter(Color. RED,
                PorterDuff.Mode. SRC_IN);
        asyncTaskRunner = new AsyncTaskRunner();
        asyncTaskRunner.execute();

        //Input data from webservice
        callChampions();
        callModes();
        callPatch();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(mainIntent);
                finish();

            }
        },6500);

        ImageView image = (ImageView) findViewById(R.id.splashscreen);
    }

    class AsyncTaskRunner extends AsyncTask<Void, Integer, Integer>
    {
        private int progress = 0;
        @Override
        protected Integer doInBackground(Void...voids) {
            for (int i = 0; i <= 6; i++) {
                publishProgress(progress); // Calls
                final int value = i;
                try {
                    Thread. sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress = value;
            }
            return progress;
        }
        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(Integer...values) {
            progressBar.setProgress(values[ 0]);
        }
    }

    private void callChampions(){
        //  atur callback jika getChampions dipanggil
        Callback<ResponseChampions> hasildatachampions = new
                Callback<ResponseChampions>() {
                    @Override
                    public void onResponse(Call<ResponseChampions> call,
                                           Response<ResponseChampions> response) {
                        if (response.isSuccessful()) {
                            List<Champions> hasilData = response.body().getChampions();
                            int jumlahData = response.body().getChampions().size();
                            if(jumlahData>0){
                                ControllerDataChampions cc = new ControllerDataChampions();
                                cc.deleteData();
                                for(int y=0; y<jumlahData;y++){
                                    final Champions tmpHasil = hasilData.get(y);
                                    try
                                    {
                                        realm.executeTransaction(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                Champions modelHasilData = realm.createObject(Champions.class, tmpHasil.getId());
                                                modelHasilData.setNama(tmpHasil.getNama());
                                                modelHasilData.setRole(tmpHasil.getRole());
                                                modelHasilData.setSkills1(tmpHasil.getSkills1());
                                                modelHasilData.setSkills2(tmpHasil.getSkills2());
                                                modelHasilData.setSkills3(tmpHasil.getSkills3());
                                                modelHasilData.setSkills4(tmpHasil.getSkills4());
                                                modelHasilData.setUltimate(tmpHasil.getUltimate());
                                                modelHasilData.setStory(tmpHasil.getStory());
                                                modelHasilData.setSummary(tmpHasil.getSummary());
                                                modelHasilData.setImage(tmpHasil.getImage());
                                            }
                                        });
                                    }catch (Exception e){
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }finally {
//                                      realm.close();
                                    }
                                }
                            } else{
                                Toast. makeText(getApplicationContext(), "DATA SEDANG TIDAK TERSEDIA", Toast. LENGTH_LONG).show();
                            }
                            progress.dismiss();
                        } else {
                            Log. e("onResponse failure", "Code: " + response.code() +" , Message: " + response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseChampions> call, Throwable t)
                    {
                        // TODO Auto-generated method stub
                        progress.dismiss();
                        Toast. makeText(getApplicationContext(), "AKSES KE SERVER GAGAL"+t.getMessage(), Toast. LENGTH_LONG).show();
                    }
                };
        //consuming web service here
        ApiPaladins= RESTClientChampions.get();
        Call<ResponseChampions> callHasilData = ApiPaladins.getChampions();
        callHasilData.enqueue(hasildatachampions);
    }

    private void callModes(){
        //  atur callback jika getChampions dipanggil
        Callback<ResponseModes> hasildatamodes = new
                Callback<ResponseModes>() {
                    @Override
                    public void onResponse(Call<ResponseModes> call,
                                           Response<ResponseModes> response) {
                        if (response.isSuccessful()) {
                            List<Modes> hasilData = response.body().getModes();
                            int jumlahData = response.body().getModes().size();
                            if(jumlahData>0){
                                ControllerDataModes cm = new ControllerDataModes();
                                cm.deleteData();
                                for(int y=0; y<jumlahData;y++){
                                    final Modes tmpHasil = hasilData.get(y);
                                    try
                                    {
                                        realm.executeTransaction(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                Modes modelHasilData = realm.createObject(Modes.class, tmpHasil.getId());
                                                modelHasilData.setMode(tmpHasil.getMode());
                                                modelHasilData.setDescription(tmpHasil.getDescription());
                                                modelHasilData.setImage(tmpHasil.getImage());
                                            }
                                        });
                                    }catch (Exception e){
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }finally {
//                                      realm.close();
                                    }
                                }
                            } else{
                                Toast. makeText(getApplicationContext(), "DATA SEDANG TIDAK TERSEDIA", Toast. LENGTH_LONG).show();
                            }
                            progress.dismiss();
                        } else {
                            Log. e("onResponse failure", "Code: " + response.code() +" , Message: " + response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseModes> call, Throwable t)
                    {
                        // TODO Auto-generated method stub
                        progress.dismiss();
                        Toast. makeText(getApplicationContext(), "AKSES KE SERVER GAGAL"+t.getMessage(), Toast. LENGTH_LONG).show();
                    }
                };
        //consuming web service here
        ApiPaladins= RESTClientModes.get();
        Call<ResponseModes> callHasilData = ApiPaladins.getModes();
        callHasilData.enqueue(hasildatamodes);
    }

    private void callPatch(){
        //  atur callback jika getPatch dipanggil
        Callback<ResponsePatch> hasildatapatch = new
                Callback<ResponsePatch>() {
                    @Override
                    public void onResponse(Call<ResponsePatch> call, Response<ResponsePatch> response) {
                        if (response.isSuccessful()) {
                            List<Patch> hasilData = response.body().getPatch();
                            int jumlahData = response.body().getPatch().size();

                            if(jumlahData>0){
                                ControllerDataPatch cl = new ControllerDataPatch();
                                cl.deleteData();
                                for(int y=0; y<jumlahData;y++){
                                    final Patch tmpHasil = hasilData.get(y);
                                    try
                                    {
                                        realm.executeTransaction(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                Patch modelHasilData = realm.createObject(Patch.class, tmpHasil.getId());
                                                modelHasilData.setJudul(tmpHasil.getJudul());
                                                modelHasilData.setSubjudul(tmpHasil.getSubjudul());
                                                modelHasilData.setGeneral(tmpHasil.getGeneral());
                                                modelHasilData.setUrl(tmpHasil.getUrl());
                                            }
                                        });
                                    }catch (Exception e){
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }finally {
//                                      realm.close();
                                    }
                                }
                            } else{
                                Toast. makeText(getApplicationContext(), "DATA SEDANG TIDAK TERSEDIA", Toast. LENGTH_LONG).show();
                            }
                            progress.dismiss();
                        } else {
                            Log. e("onResponse failure", "Code: " + response.code() +" , Message: " + response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponsePatch> call, Throwable t)
                    {
                        // TODO Auto-generated method stub
                        progress.dismiss();
                        Toast. makeText(getApplicationContext(), "AKSES KE SERVER GAGAL"+t.getMessage(), Toast. LENGTH_LONG).show();
                    }
                };
        //consuming web service here
        ApiPaladins= RESTClientPatch.get();
        Call<ResponsePatch> callHasilData = ApiPaladins.getPatch();
        callHasilData.enqueue(hasildatapatch);
    }
}
