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
public class Faculty extends AbsEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    public University university;
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    private List<Courses> coursesList;

}
