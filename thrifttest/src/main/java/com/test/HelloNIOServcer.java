package com.test;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import service.demo.Hello;
import service.demo.HelloServiceImpl;

public class HelloNIOServcer {
    public static void main(String args []){
        try{
            //非阻塞io
         /*   TNonblockingServerSocket socket = new TNonblockingServerSocket(9998);
            TProcessor processor = new Hello.Processor<>(new HelloServiceImpl());
            TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
            arg.protocolFactory(new TBinaryProtocol.Factory());
            arg.transportFactory(new TFramedTransport.Factory());
            arg.processorFactory(new TProcessorFactory(processor));
            TServer server = new TNonblockingServer(arg);
            server.serve();*/
          /**
           * 多路复用
           * */
            TNonblockingServerSocket socket = new TNonblockingServerSocket(9998);
            TProcessor processor = new Hello.Processor<>(new HelloServiceImpl());
            TMultiplexedProcessor processor1 = new TMultiplexedProcessor();
            processor1.registerProcessor("helloservice",processor);
//            processor1.registerProcessor("helloservice2",processor*);

         //   TMultiplexedProtocol
//            TMultiplexedProtocol p = new TMultiplexedProtocol(processor1,"helloservice");

            TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
            arg.protocolFactory(new TBinaryProtocol.Factory());
            arg.transportFactory(new TFramedTransport.Factory());
            arg.processorFactory(new TProcessorFactory(processor));
//            arg.processorFactory(new TProcessorFactory(processor1));
            TServer server = new TNonblockingServer(arg);
            server.serve();
            /*  TServerSocket serverTransport = new TServerSocket(9999);
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();

            //管理处理器与hello服务的实现
            TProcessor processor = new Hello.Processor<>(new HelloServiceImpl());

            TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
            ttpsArgs.processor(processor);
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
            //线程池服务模型，标准阻塞io，
            TServer server = new TThreadPoolServer(ttpsArgs);
            System.out.println("start server on prot 9999");
            server.serve();*/

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
