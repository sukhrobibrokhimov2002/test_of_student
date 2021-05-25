package uz.sukhrob.testofepos.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResStudent {
    private String fullName;
    private String city;
    private String street;
    private String homeNumber;
}
