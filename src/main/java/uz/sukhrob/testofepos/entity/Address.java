package uz.sukhrob.testofepos.entity;

import lombok.*;
import uz.sukhrob.testofepos.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"city","street","homeNumber"}))
public class Address extends AbsEntity {
    private String city;
    private String street;
    private String homeNumber;
}
