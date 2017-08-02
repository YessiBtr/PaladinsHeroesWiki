package pribadi.tugas.if314053.paladinsheroeswiki;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.adapter.ChampionsAdapter;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Champions;

public class ChampionsActivity extends AppCompatActivity {

    Realm realm;

    RecyclerView recyclerView = null;
    private ChampionsAdapter championsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.rvchampions);
        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        RealmResults<Champions> hasil= realm.where(Champions.class).findAll();

        championsAdapter = new ChampionsAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(championsAdapter);
        recyclerView.setHasFixedSize(true);

    }

}
