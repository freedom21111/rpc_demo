package simple_rpc.IDL.Hello;

public interface HelloServer {
    HelloResponse hello(HelloRequest helloRequest);
    HelloResponse hi(HelloRequest helloRequest);
}
