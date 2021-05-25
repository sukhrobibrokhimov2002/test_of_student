package uz.sukhrob.testofepos.entity;

import lombok.*;
import uz.sukhrob.testofepos.entity.template.AbsEntity;


import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends AbsEntity {

    private String fullName;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
}
