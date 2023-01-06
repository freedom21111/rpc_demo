package simple_rpc.core.rpc_protocol;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcResponse implements Serializable {
    private String header;

    private byte[] body;
}
