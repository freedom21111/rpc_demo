package simple_rpc.core.server;

import lombok.AllArgsConstructor;
import simple_rpc.core.body.RpcRequestBody;
import simple_rpc.core.body.RpcResponseBody;
import simple_rpc.core.rpc_protocol.RpcRequest;
import simple_rpc.core.rpc_protocol.RpcResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

@AllArgsConstructor
public class RpcServerWork implements Runnable{

    private Socket socket;
    private HashMap<String,Object> registeredService;

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            RpcRequest rpcRequest =(RpcRequest) objectInputStream.readObject();

            if(rpcRequest.getHeader().equals("freedom")){

                byte[]body=rpcRequest.getBody();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
                ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);//反序列化
                RpcRequestBody rpcRequestBody =(RpcRequestBody) ois.readObject();


                Object service= registeredService.get(rpcRequestBody.getInterName());
                Method method=service.getClass().getMethod(rpcRequestBody.getMethodName(),rpcRequestBody.getParamTypes());
                Object returnObject=method.invoke(service,rpcRequestBody.getParameters());

                RpcResponseBody rpcResponseBody = RpcResponseBody.builder()
                        .returnObject(returnObject)
                        .build();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
                oos.writeObject(rpcResponseBody);
                byte[] bytes=byteArrayOutputStream.toByteArray();

                RpcResponse rpcResponse = RpcResponse.builder()
                        .header("freedom")
                        .body(bytes)
                        .build();

                objectOutputStream.writeObject(rpcResponse);
                objectOutputStream.flush();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
