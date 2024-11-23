package mall.shopping.mall.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String password;
    private String email;


}
