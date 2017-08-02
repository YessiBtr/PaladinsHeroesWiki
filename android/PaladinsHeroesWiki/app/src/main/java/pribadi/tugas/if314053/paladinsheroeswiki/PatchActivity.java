package pribadi.tugas.if314053.paladinsheroeswiki;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.adapter.PatchAdapter;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Patch;

public class PatchActivity extends AppCompatActivity {

    Realm realm;

    RecyclerView recyclerView = null;
    private PatchAdapter patchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch);

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.rvpatch);
        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        RealmResults<Patch> hasil= realm.where(Patch.class).findAll();

        patchAdapter = new PatchAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patchAdapter);
        recyclerView.setHasFixedSize(true);

    }
}
