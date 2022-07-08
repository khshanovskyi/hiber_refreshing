package app.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    private String name;

    private int age;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
