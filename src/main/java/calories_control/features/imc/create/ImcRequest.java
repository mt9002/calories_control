package calories_control.features.imc.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImcRequest {

    private Double peso;
    private Double altura;
    private Long userId;

}
