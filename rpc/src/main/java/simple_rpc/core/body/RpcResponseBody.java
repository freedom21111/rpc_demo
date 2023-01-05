package simple_rpc.core.body;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RpcResponseBody {
        private Object returnObject;
}
