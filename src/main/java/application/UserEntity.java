package application;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

}
