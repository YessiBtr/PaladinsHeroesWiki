package pribadi.tugas.if314053.paladinsheroeswiki.API;


import java.util.List;

import pribadi.tugas.if314053.paladinsheroeswiki.model.Champions;

/**
 * Created by Satya on 5/14/2017.
 */

public class ResponseChampions {

    private String error;
    private List<Champions> champions;

    public String getError(){
        return error;
    }

    public List<Champions> getChampions(){
        return champions;
    }

    public void setChampions(List<Champions> champions){
        this.champions = champions;
    }
}
