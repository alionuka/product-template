package kantseryk.pzks.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
/**
 * Author: Alona Kantseryk
 */
public class ApiResponse<T> {

    private BaseMetaData meta;

    private T data;
}
