package app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "region")
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region extends AbstractEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "region")
    private List<Address> addresses;
}
