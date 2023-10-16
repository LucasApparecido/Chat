package ueg.back.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Users implements Serializable {

    private String name;
    private Set<String> setOnlines = new HashSet<String>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getSetOnlines() {
        return setOnlines;
    }


    public void setSetOnlines(Set<String> setOlines) { this.setOnlines = setOnlines; }

}




