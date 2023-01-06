package simple_rpc.IDL.Hello;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class HelloRequest implements Serializable {
    private String name;
}
