package uz.sukhrob.testofepos.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResFaculty {
    private String facultyName;
    private String universityName;
}
