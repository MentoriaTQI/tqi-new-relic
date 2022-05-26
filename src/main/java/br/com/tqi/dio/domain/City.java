package br.com.tqi.dio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "city")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {

    private static final long serialVersionUID = -1573343401340917312L;

    @Id
    private String id;

}