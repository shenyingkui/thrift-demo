package com.test.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import service.demo.Hello;

public class HelloServiceClientNIO {

    public static void main(String [] args){
        System.out.println();
        TSocket tSocket = new TSocket("localhost",9998);
        TTransport transport = new TFramedTransport(tSocket);
         if(!transport.isOpen()){
             try {
                 transport.open();
                 TProtocol protocol = new TBinaryProtocol(transport);
              /*   Hello.Client client = new Hello.Client(protocol);

                 String result  = client.helloString("你好，申英魁");
                 System.out.println(result);
                 transport.close();*/

//Tmul测试
                 TMultiplexedProtocol p = new TMultiplexedProtocol(protocol,"helloservice");
                 Hello.Client client = new Hello.Client( p);
                 String result  = client.helloString("你好，申英魁");
                 System.out.println(result);
                 transport.close();
             } catch (TTransportException e) {
                 e.printStackTrace();
             } catch (TException e) {
                 e.printStackTrace();
             }
         }


        /*TTransport transport = new TSocket("localhost",9999);
        try {
            transport.open();

            //设置传输协议
            TProtocol protocol = new TBinaryProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);

            String result  = client.helloString("你好，申英魁");
            System.out.println(result);
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }*/

    }


}
