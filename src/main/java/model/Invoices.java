package model;

import javax.persistence.*;

@Entity
@Table(name="invoices",schema = "public")
public class Invoices {
    public Invoices(){}

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne
    @JoinColumn(name = "id_order")
    private Order order;

    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}
