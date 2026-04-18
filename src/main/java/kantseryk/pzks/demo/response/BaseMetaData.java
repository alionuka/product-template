package kantseryk.pzks.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
/**
 * Author: Alona Kantseryk
 */
public class BaseMetaData {

    @Builder.Default
    private int code = 200;

    @Builder.Default
    private boolean success = true;

    @Builder.Default
    private String errorMessage = null;
}