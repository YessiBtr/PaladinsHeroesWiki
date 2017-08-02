package pribadi.tugas.if314053.paladinsheroeswiki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Patch;


public class PatchDetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    Realm realm;
    private TextView judul, subjudul, general,url;
    private RealmList<Patch> patch;
    private Iterator<Patch> iterator;
    private RealmResults<Patch> modelPatch;
    private int idPatch= 0;
    private Patch obj;

    public static final String API_KEY = "AIzaSyDjQFdvdeSvq24M8eDh-Rh6PuWsN0N3cPk";

    public static String VIDEO_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch_detail);

        Intent intent = getIntent();
        idPatch = Integer.valueOf(intent.getStringExtra("id"));

        realm = Realm.getDefaultInstance();

        initializeView();

        try {
            obj = realm.where(Patch.class).equalTo("id", idPatch).findFirst();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        setPatch(obj);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
    }

    private  void initializeView(){
        judul =(TextView) findViewById(R.id.txtJudul);
        subjudul =(TextView) findViewById(R.id.txtSubJudul);
        general =(TextView) findViewById(R.id.txtGeneral);
    }

    private void setPatch(Patch pat){
        judul.setText(pat.getJudul());
        subjudul.setText(pat.getSubjudul());
        general.setText(pat.getGeneral());
        VIDEO_ID = pat.getUrl();
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if (!b){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    private  PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(ErrorReason errorReason) {

        }
    };

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failure to Initialize!", Toast.LENGTH_LONG).show();
    }
}
