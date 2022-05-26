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
@Table(name = "district")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class District implements Serializable {

    private static final long serialVersionUID = -728169804668578277L;

    @Id
    private String id;

}