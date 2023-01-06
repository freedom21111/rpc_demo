package simple_rpc.core.body;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcResponseBody implements Serializable {
        private Object returnObject;
}
