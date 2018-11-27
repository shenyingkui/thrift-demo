package com.test;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import service.demo.Hello;
import service.demo.HelloServiceImpl;

public class HelloServcer {
    public static void main(String args []){
        try{
            TServerSocket serverTransport = new TServerSocket(9999);
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();

            //管理处理器与hello服务的实现
            TProcessor processor = new Hello.Processor<>(new HelloServiceImpl());

            TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
            ttpsArgs.processor(processor);
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
            //线程池服务模型，标准阻塞io，
            TServer server = new TThreadPoolServer(ttpsArgs);
            System.out.println("start server on prot 9999");
            server.serve();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
