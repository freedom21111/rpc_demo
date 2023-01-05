package simple_rpc.core.body;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcRequestBody implements Serializable {
    private String interName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
}
