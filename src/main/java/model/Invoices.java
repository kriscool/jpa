package model;

import javax.persistence.*;

@Entity
@Table(name="invoices",schema = "public")
public class Invoices {
    public Invoices(){}

    @OneToOne
    @JoinColumn(name = "id_order")
    private Order order;

    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}
