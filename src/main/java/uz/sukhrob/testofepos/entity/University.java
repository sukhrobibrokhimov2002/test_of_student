package uz.sukhrob.testofepos.entity;

import lombok.*;
import uz.sukhrob.testofepos.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class University extends AbsEntity {

    @Column(unique = true)
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy ="university",cascade = CascadeType.REMOVE)
    private List<Faculty> faculties;



}
