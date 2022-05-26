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
@Table(name = "uf")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Uf implements Serializable {

    private static final long serialVersionUID = 3609016016678887072L;

    @Id
    private String id;

}
