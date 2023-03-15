package com.example.coffee.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
public class Coffee {


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    @Min(value = 1, message = "Price may not be less than 1")
    private double price;

    @Column(name = "is_new")
    private boolean newProduct;

    @Column(name = "is_hot")
    private boolean hotProduct;

    @Column(columnDefinition = "text")
    private String description;

    private String picture;


    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cafe cafe;


    @ManyToMany(mappedBy = "coffeesList")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> ordersList;


}
