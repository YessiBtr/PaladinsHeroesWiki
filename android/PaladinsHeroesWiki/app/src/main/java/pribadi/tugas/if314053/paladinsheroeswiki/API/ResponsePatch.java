package pribadi.tugas.if314053.paladinsheroeswiki.API;

import java.util.List;

import pribadi.tugas.if314053.paladinsheroeswiki.model.Patch;

/**
 * Created by Satya on 17-May-17.
 */

public class ResponsePatch {
    private String error;
    private List<Patch> patch;

    public String getError(){
        return error;
    }

    public List<Patch> getPatch(){
        return patch;
    }

    public void setPatch(List<Patch> patch){
        this.patch = patch;
    }
}
