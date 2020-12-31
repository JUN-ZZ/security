package com.jun.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_authority")
@Data
public class Authority extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            columnDefinition = "int default 0"
    )
    private Long parent_id;

    @Column
    private String authName;

    @Column
    private String url;

    @Column
    private String requestMethod;

    @Column
    private String code;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities",fetch = FetchType.LAZY)
    private Set<Role> roles;

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", authName='" + authName + '\'' +
                ", url='" + url + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        if (!super.equals(o)) return false;

        Authority authority = (Authority) o;

        if (getId() != null ? !getId().equals(authority.getId()) : authority.getId() != null) return false;
        if (getParent_id() != null ? !getParent_id().equals(authority.getParent_id()) : authority.getParent_id() != null)
            return false;
        if (getAuthName() != null ? !getAuthName().equals(authority.getAuthName()) : authority.getAuthName() != null)
            return false;
        if (getUrl() != null ? !getUrl().equals(authority.getUrl()) : authority.getUrl() != null) return false;
        if (getRequestMethod() != null ? !getRequestMethod().equals(authority.getRequestMethod()) : authority.getRequestMethod() != null)
            return false;
        return getCode() != null ? getCode().equals(authority.getCode()) : authority.getCode() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getParent_id() != null ? getParent_id().hashCode() : 0);
        result = 31 * result + (getAuthName() != null ? getAuthName().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getRequestMethod() != null ? getRequestMethod().hashCode() : 0);
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        return result;
    }
}
