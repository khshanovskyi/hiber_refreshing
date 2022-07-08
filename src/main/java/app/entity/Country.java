package app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country extends AbstractEntity{

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String abbreviation;

    @OneToMany(mappedBy = "country")
    private List<Address> addresses;

    @OneToMany(mappedBy = "country")
    private List<Region> regions;
}
