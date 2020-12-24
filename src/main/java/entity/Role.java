package entity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "role")
public class Role {

    private int authorityLevel;
    String name;

    public Role(int authorityLevel, String name) {
        this.authorityLevel = authorityLevel;
        this.name = name;
    }

    public Role() {
    }

    public int getAuthorityLevel() {
        return authorityLevel;
    }

    public void setAuthorityLevel(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
