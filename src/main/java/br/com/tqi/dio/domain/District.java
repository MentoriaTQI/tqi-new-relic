package br.com.tqi.dio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "district")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class District implements Serializable {

    private static final long serialVersionUID = -728169804668578277L;

    @Id
    private String id;

    @OneToMany(mappedBy = "district", orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

}