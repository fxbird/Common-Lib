package com.xdg.util;

import com.xdg.inter.CallableExt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {
    private final static Log log = LogFactory.getLog(ThreadUtil.class);

    public static void runConcurrently(ExecutorService pool, List materials, final CallableExt callableExt, int cntPerThread) throws InterruptedException {
        List<List> groups = ListUtil.split(materials, cntPerThread);
        log.debug("splited list size : " + groups.size());
        ArrayList<Callable<Object>> callables = new ArrayList<Callable<Object>>();
        for (final List group : groups) {
            Callable<Object> callable = new Callable<Object>() {
                public Object call() throws Exception {
                    callableExt.run(group);
                    return null;
                }
            };

            callables.add(callable);

        }

        pool.invokeAll(callables);
        pool.shutdown();

    }

    public static void runConcurrently(List materials, final CallableExt callableExt, int cntPerThread) throws InterruptedException {
        runConcurrently(Executors.newCachedThreadPool(), materials, callableExt, cntPerThread);
    }
}
