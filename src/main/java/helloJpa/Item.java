package helloJpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //JOINED(정규화 등 !기본, 비즈니스에서 중요하고 복잡하면 사용!), SINGLE_TABLE(조회쿼리 빠름), TABLE_PER_CLASS(사용안함)
@DiscriminatorColumn
public abstract class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
