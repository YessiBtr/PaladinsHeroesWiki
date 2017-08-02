package pribadi.tugas.if314053.paladinsheroeswiki.API;

import java.util.List;

import pribadi.tugas.if314053.paladinsheroeswiki.model.Modes;

/**
 * Created by Satya on 16-May-17.
 */

public class ResponseModes {
    private String error;
    private List<Modes> modes;

    public String getError(){
        return error;
    }

    public List<Modes> getModes(){
        return modes;
    }

    public void setModes(List<Modes> modes){
        this.modes = modes;
    }
}
