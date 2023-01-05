package simple_rpc.core.rpc_protocol;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RpcRequest {
    private String header;

    private byte[] body;
}
