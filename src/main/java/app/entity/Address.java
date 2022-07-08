package app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(nullable = false)
    private String street;

    @Column(name = "house_number")
    private String house;

    @OneToMany(mappedBy = "address")
    private List<User> users;

}
