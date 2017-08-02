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
import pribadi.tugas.if314053.paladinsheroeswiki.API.RESTClientChampions;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Champions;

public class ChampionsDetailActivity extends AppCompatActivity {

    Realm realm;
    private TextView nama, role, skill1,skill2, skill3, skill4, ultimate, story, summary;
    ImageView imageChampions;
    private RealmList<Champions> champions;
    private Iterator<Champions> iterator;
    private RealmResults<Champions> modelChampions;
    private int jenisChampions= 0;
    private Champions obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions_detail);

        Intent intent = getIntent();
        jenisChampions = Integer.valueOf(intent.getStringExtra("id"));

        realm = Realm.getDefaultInstance();

        initializeView();
        try {

            obj = realm.where(Champions.class).equalTo("id", jenisChampions).findFirst();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        setChampions(obj);
    }

    private  void initializeView(){

        nama =(TextView) findViewById(R.id.txtjudulNama);
        role =(TextView) findViewById(R.id.txtRole);
        skill1 =(TextView) findViewById(R.id.txtSkill1);
        skill2 = (TextView) findViewById(R.id.txtSkill2);
        skill3 = (TextView) findViewById(R.id.txtSkill3);
        skill4 = (TextView) findViewById(R.id.txtSkill4);
        ultimate = (TextView) findViewById(R.id.txtUltimate);
        story = (TextView) findViewById(R.id.txtStory);
        summary = (TextView) findViewById(R.id.txtSummary);
        imageChampions = (ImageView) findViewById(R.id.imageChampions);

    }

    private void setChampions(Champions Cham){
        nama.setText(Cham.getNama());
        role.setText("Role : "+Cham.getRole());
        skill1.setText("Skill1 : "+Cham.getSkills1());
        skill2.setText("Skill2 : "+Cham.getSkills2());
        skill3.setText("Skill3 : "+Cham.getSkills3());
        skill4.setText("Skill4 : "+Cham.getSkills4());
        ultimate.setText("Ultimate : "+Cham.getUltimate());
        story.setText("Story : "+Cham.getStory());
        summary.setText("Summary : "+Cham.getSummary());
        Glide.with(getApplicationContext()).load(RESTClientChampions.URL+"/paladinswiki/img/"+obj.getImage()).into(imageChampions);

    }
}
