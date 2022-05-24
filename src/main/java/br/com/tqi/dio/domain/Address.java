package br.com.tqi.dio.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "postal_code")
    private String postalCode;
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}