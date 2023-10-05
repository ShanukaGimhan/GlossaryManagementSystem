package com.springboot.pos.entity;

import com.springboot.pos.entity.enums.MeasuringUnitType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    @Id
    @Column(name = "item_id", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "mesure_type", length = 100, nullable = false)
    private MeasuringUnitType measuringUnitType;        // create enum class and use here. because measure unit can be different

    @Column(name = "balance_qty", length = 100, nullable = false)
    private double balanceQty;                // fi we not need getter setter for specific variable, we can use @Getter(Access_level.none)

    @Column(name = "supply_price", length = 100, nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price", length = 100, nullable = false)
    private double sellingPrice;

    @Column(name = "active_state", columnDefinition = "TINYINT default 0")
    private boolean activeState;

}
