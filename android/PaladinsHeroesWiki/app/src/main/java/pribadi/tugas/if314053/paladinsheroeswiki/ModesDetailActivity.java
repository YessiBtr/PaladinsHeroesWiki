package pribadi.tugas.if314053.paladinsheroeswiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.API.RESTClientModes;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Modes;

public class ModesDetailActivity extends AppCompatActivity {

    Realm realm;
    private TextView mode, description;
    ImageView imageModes;
    private RealmList<Modes> modes;
    private Iterator<Modes> iterator;
    private RealmResults<Modes> modelModes;
    private int jenisModes= 0;
    private Modes obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes_detail);

        Intent intent = getIntent();
        jenisModes = Integer.valueOf(intent.getStringExtra("id"));

        realm = Realm.getDefaultInstance();

        initializeView();
        try {

            obj = realm.where(Modes.class).equalTo("id", jenisModes).findFirst();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        setModes(obj);
    }

    private  void initializeView(){

        mode =(TextView) findViewById(R.id.txtModes);
        description =(TextView) findViewById(R.id.txtDescription);
        imageModes = (ImageView) findViewById(R.id.imageModes);

    }

    private void setModes(Modes Cham){
        mode.setText(Cham.getMode());
        description.setText(Cham.getDescription());
        Glide.with(getApplicationContext()).load(RESTClientModes.URL+"/paladinswiki/img/"+obj.getImage()).into(imageModes);

    }
}

