package pribadi.tugas.if314053.paladinsheroeswiki.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Satya on 16-May-17.
 */

public class RESTClientModes {
    private static APIPaladins REST_CLIENT;
    public static String URL = "http://10.0.2.2/";
    static { //dieksekusi sebelum constructor, tapi hanya sekali untuk semua instans
        setupRestClientModes();
    }
    private RESTClientModes() {}
    public static APIPaladins get() {
        return REST_CLIENT;
    }
    private static void setupRestClientModes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(APIPaladins.class);
    }
}
