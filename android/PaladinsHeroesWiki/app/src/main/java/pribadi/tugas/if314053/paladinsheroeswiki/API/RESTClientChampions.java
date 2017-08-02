package pribadi.tugas.if314053.paladinsheroeswiki.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Satya on 5/14/2017.
 */

public class RESTClientChampions {
    private static APIPaladins REST_CLIENT;
    public static String URL = "http://10.0.2.2/";
    static { //dieksekusi sebelum constructor, tapi hanya sekali untuk semua instans
        setupRestClient();
    }
    private RESTClientChampions() {}
    public static APIPaladins get() {
        return REST_CLIENT;
    }
    private static void setupRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(APIPaladins.class);
    }
}
