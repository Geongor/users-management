package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 *
 * Класс-обертка
 * Нужен для чтения списка возможных ролей
 *
 * */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleList {

    @XmlElement(name = "role")
    private List<Role> roles;

    public RoleList(List<Role> roles) {
        this.roles = roles;
    }

    public RoleList() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
