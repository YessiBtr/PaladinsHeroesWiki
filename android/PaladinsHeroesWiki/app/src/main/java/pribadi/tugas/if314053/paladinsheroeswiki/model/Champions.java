package pribadi.tugas.if314053.paladinsheroeswiki.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Satya on 15-May-17.
 */

public class Champions extends RealmObject{
    @PrimaryKey
    private int id;
    private String nama;
    private String role;
    private String skills1;
    private String skills2;
    private String skills3;
    private String skills4;
    private String ultimate;
    private String story;
    private String summary;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSkills1() {
        return skills1;
    }

    public void setSkills1(String skills1) {
        this.skills1 = skills1;
    }

    public String getSkills2() {
        return skills2;
    }

    public void setSkills2(String skills2) {
        this.skills2 = skills2;
    }

    public String getSkills3() {
        return skills3;
    }

    public void setSkills3(String skills3) {
        this.skills3 = skills3;
    }

    public String getSkills4() {
        return skills4;
    }

    public void setSkills4(String skills4) {
        this.skills4 = skills4;
    }

    public String getUltimate() {
        return ultimate;
    }

    public void setUltimate(String ultimate) {
        this.ultimate = ultimate;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
