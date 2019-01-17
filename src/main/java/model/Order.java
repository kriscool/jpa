package model;


import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="order",schema = "public")
public class Order {

    public Order(){}
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "car_order",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id") }
    )
    private Set<Car> cars = new HashSet<Car>();

    private Date date_order;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    public Date getDate_order() {
        return date_order;
    }

    public void setDate_order(Date date_order) {
        this.date_order = date_order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
