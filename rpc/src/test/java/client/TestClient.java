package client;

import simple_rpc.IDL.Hello.HelloRequest;
import simple_rpc.IDL.Hello.HelloResponse;
import simple_rpc.IDL.Hello.HelloServer;
import simple_rpc.core.client.RpcClientProxy;

public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        HelloServer service = rpcClientProxy.getService(HelloServer.class);

        HelloRequest helloRequest = new HelloRequest("000000");

        HelloResponse hello = service.hello(helloRequest);

        String msg = hello.getMsg();

        System.out.println(msg);

        HelloResponse hiResponse = service.hi(helloRequest);
        String hiMsg = hiResponse.getMsg();
        System.out.println(hiMsg);
    }
}
