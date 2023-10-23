package helloJpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    private Long id;
    private String name;
//    @OneToMany(mappedBy = "team") //Member에 있는 team
//    private List<Member> members = new ArrayList<>(); // add할 때 null이 안뜨기 위해 ArrayList로 초기화


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
