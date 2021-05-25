package uz.sukhrob.testofepos.entity;

import lombok.*;
import uz.sukhrob.testofepos.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Courses extends AbsEntity {
    private String courseNumber;
    @ManyToOne
    private Faculty faculty;
}
