package pribadi.tugas.if314053.paladinsheroeswiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }
    public void champions(View view){
        Intent nextScreen = new Intent(getApplicationContext(), ChampionsActivity.class);
        startActivity(nextScreen);
    }
    public void modes(View view){
        Intent nextScreen = new Intent(getApplicationContext(), ModesActivity.class);
        startActivity(nextScreen);
    }
    public void patch(View view){
        Intent nextScreen = new Intent(getApplicationContext(), PatchActivity.class);
        startActivity(nextScreen);
    }
}
