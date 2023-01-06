package simple_rpc.core.client;

import simple_rpc.core.body.RpcRequestBody;
import simple_rpc.core.rpc_protocol.RpcRequest;
import simple_rpc.core.rpc_protocol.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcClientTransfer {

    public RpcResponse sendRequest(RpcRequest rpcRequest){
        try(Socket socket = new Socket("localhost", 9000)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            RpcResponse rpcResponse = (RpcResponse) objectInputStream.readObject();

            return rpcResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
