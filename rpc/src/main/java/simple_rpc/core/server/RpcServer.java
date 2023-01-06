package simple_rpc.core.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.*;

public class RpcServer {
    private final ExecutorService threadPool;

    private final HashMap<String,Object> registeredService;

    public RpcServer(){
        int corePoolSize=5;
        int maxPoolSize=50;
        long keepAliveTime=60;
        BlockingQueue<Runnable> workingQueue =new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory= Executors.defaultThreadFactory();
        this.threadPool=new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,TimeUnit.SECONDS,workingQueue,threadFactory);
        this.registeredService=new HashMap<String,Object>();
    }

    public void register(Object service){
        registeredService.put(service.getClass().getInterfaces()[0].getName(),service);
    }

    public void serve(int port){
        try(ServerSocket serverSocket = new ServerSocket(port);) {
            System.out.println("server starting...");
            Socket headSocket;
            while((headSocket=serverSocket.accept())!=null){
                System.out.println("client connected, ip :"+headSocket.getInetAddress());
                threadPool.execute(new RpcServerWork(headSocket,registeredService));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
