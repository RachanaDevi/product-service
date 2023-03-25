package com.example.productservice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products = new ArrayList<>();

    public ProductCategory() {
    }

    public ProductCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String name() {
        return name;
    }

    //    TELEVISION,
//    LAPTOP,
//    MOBILE_PHONE,
//    REFRIGERATOR_AND_FREEZER,
//    WASHING_MACHINE,
//    AIR_CONDITIONER,
//    SPEAKERS_MEDIA_PLAYER,
//    MICROWAVE_AND_OVEN,
//    MONITOR_AND_ALL_IN_ONES,
//    VACUUM_CLEANER,
//    DISHWASHER,
//    WATER_PURIFIER,
//
//    // from Croma
//    FAN,
//    ROOM_HEATER,
//    GEYSER,
//    SEWING_MACHINE,
//    VR_HEADSET,
//    TABLET_AND_IPAD,
//    ELECTRIC_CHIMNEY,
//    NETWORK_COMPONENT,
//    KEYBOARD,
//    MOUSE,
//    HEADPHONES_AND_EARPHONES,
//    GAMING
}
