package pribadi.tugas.if314053.paladinsheroeswiki.API;

/**
 * Created by Satya on 5/14/2017.
 */
import retrofit2.Call;
import retrofit2.http.POST;

public interface APIPaladins {
    /*URL Web service:  */
    @POST("/paladinswiki/champions")
    Call<ResponseChampions> getChampions();

    @POST("/paladinswiki/modes")
    Call<ResponseModes> getModes();

    @POST("/paladinswiki/patch")
    Call<ResponsePatch> getPatch();
}
