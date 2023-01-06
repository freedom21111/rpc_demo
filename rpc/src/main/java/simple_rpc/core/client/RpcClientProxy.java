package simple_rpc.core.client;

import simple_rpc.core.body.RpcRequestBody;
import simple_rpc.core.body.RpcResponseBody;
import simple_rpc.core.rpc_protocol.RpcRequest;
import simple_rpc.core.rpc_protocol.RpcResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcClientProxy implements InvocationHandler {
    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                this
        );
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequestBody rpcRequestBody = RpcRequestBody.builder()
                .interName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramTypes(method.getParameterTypes())
                .parameters(args)
                .build();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(rpcRequestBody);
        byte[] bytes =byteArrayOutputStream.toByteArray();

        RpcRequest rpcRequest = RpcRequest.builder()
                .header("freedom")
                .body(bytes)
                .build();

        RpcClientTransfer rpcClient = new RpcClientTransfer();
        RpcResponse rpcResponse = rpcClient.sendRequest(rpcRequest);

        String header =rpcResponse.getHeader();
        byte[] body =rpcResponse.getBody();
        if(header.equals("freedom")){

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            RpcResponseBody rpcResponseBody =(RpcResponseBody)objectInputStream.readObject();
            Object retObject= rpcResponseBody.getReturnObject();
                    return retObject;
        }


        return null;
    }
}
