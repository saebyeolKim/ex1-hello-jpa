package helloJpa;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

//    @ManyToOne(fetch = FetchType.LAZY) //지연로딩, 프록시로 조회 getReference, member 만 쓰고 team 은 실제로 사용하지 않을 때 사용
    @ManyToOne(fetch = FetchType.EAGER) //즉시로딩, member 와 team 을 거의 항상 같이 사용할 때, !실무에선 사용하면 안됨!
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
