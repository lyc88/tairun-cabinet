package com.tairun.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by lyc on 2017/8/1.
 */
public class ThreadTest {

    private static Log logger1 = LogFactory.getLog(ThreadTest.class);
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Thread1());
        thread.start();


        BlockingDeque blockingDeque = new LinkedBlockingDeque();

        blockingDeque.put("A");
        blockingDeque.put("B");
        blockingDeque.put("C");
        System.out.println(blockingDeque.size());
        for (int i=0,len=blockingDeque.size();i<len;i++) {
            System.out.println(blockingDeque.take()+"==="+blockingDeque.size());
        }
        FutureTask<Integer> future = new FutureTask<Integer>(new Thread2());
        new Thread(future).start();

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String str = "{\n" +
                "\t\"identifier\":\t123456,\n" +
                "\t\"action\":\t\"courier_login\",\n" +
                "\t\"account\":\t\"12315\",\n" +
                "\t\"password\":\t\"12456\"\n" +
                "}";

        //Thread.sleep(2000);
        logger1.debug("hello");
        //System.out.println(str.getBytes().length);
    }
}
class Thread1 implements Runnable{

    @Override
    public void run() {

        throw new RuntimeException("error");
    }
}

class Thread2 implements Callable{

    @Override
    public Object call() throws Exception {

        for(int i=0 ;i<100;i++) {
            //System.out.println(i);
        }
        return 100;
    }
}