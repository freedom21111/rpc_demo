package server;

import simple_rpc.IDL.Hello.HelloRequest;
import simple_rpc.IDL.Hello.HelloResponse;
import simple_rpc.IDL.Hello.HelloServer;

public class HelloServiceImpl implements HelloServer {

    @Override
    public HelloResponse hello(HelloRequest request) {
        String name = request.getName();
        String retMsg = "hello: " + name;
        HelloResponse response = new HelloResponse(retMsg);
        return response;

    }

    @Override
    public HelloResponse hi(HelloRequest request) {
        String name = request.getName();
        String retMsg = "hi: " + name;
        HelloResponse response = new HelloResponse(retMsg);
        return response;
    }

}
