package pribadi.tugas.if314053.paladinsheroeswiki;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.adapter.ModesAdapter;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Modes;

public class ModesActivity extends AppCompatActivity {

    Realm realm;

    RecyclerView recyclerView = null;
    private ModesAdapter modesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.rvmodes);
        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        RealmResults<Modes> hasil= realm.where(Modes.class).findAll();

        modesAdapter = new ModesAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(modesAdapter);
        recyclerView.setHasFixedSize(true);

    }

}
