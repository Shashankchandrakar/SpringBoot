package application;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Product")
@Data
public class ProductEntity {

    @Id
  //  @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private  String colour;
}
