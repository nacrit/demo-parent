package com.example.demo.mistakes.demo03;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author zhenghao
 * @description 测试
 * @date 2020/6/28 16:51
 */
@RestController
@RequestMapping("/tp")
@Slf4j
public class ThreadPoolDemoController {

    // 自定义线程池
    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            2, 2,
            1, TimeUnit.HOURS,
            new ArrayBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("batchfileprocess-threadpool-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    {
//        init();
    }

    /**
     * 线程池的混用策略
     * 对于执行比较慢、数量不大的 IO 任务，或许要考虑更多的线程数，而不需要太大的队列。
     * 而对于吞吐量较大的计算型任务，线程数量不宜过多，可以是 CPU 核数或核数 *2（理由是，线程一定调度到某个 CPU 进行执行，
     * 如果任务本身是 CPU 绑定的任务，那么过多的线程只会增加线程切换的开销，并不能提升吞吐量），但可能需要较长的队列来做缓冲。
     */
    public void init() {
//        ThreadPoolUtil.printStats(threadPool);
        ThreadPoolUtil.printStats(asyncCalcThreadPool);
//        new Thread(() -> {
//            //模拟需要写入的大量数据
//            String payload = IntStream.rangeClosed(1, 1_000_000)
//                    .mapToObj(__ -> "a")
//                    .collect(Collectors.joining(""));
//            while (true) {
//                threadPool.execute(() -> {
//                    try {
//                        //每次都是创建并写入相同的数据到相同的文件
//                        Files.write(Paths.get("demo.txt"), Collections.singletonList(LocalTime.now().toString() + ":" + payload + "\n"), UTF_8, CREATE, TRUNCATE_EXISTING);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    log.info("batch file processing done");
//                });
//            }
//        }).start();
    }

    @GetMapping("/wrong")
    public int wrong2() throws ExecutionException, InterruptedException {
        return threadPool.submit(calcTask()).get();
    }


    private static ThreadPoolExecutor asyncCalcThreadPool = new ThreadPoolExecutor(
            200, 200,
            1, TimeUnit.HOURS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactoryBuilder().setNameFormat("asynccalc-threadpool-%d").build());

    @GetMapping("/right")
    public int right() throws ExecutionException, InterruptedException {
        return asyncCalcThreadPool.submit(calcTask()).get();
    }

    private Callable<Integer> calcTask() {
        return () -> {
            TimeUnit.MILLISECONDS.sleep(10);
            return 1;
        };
    }
}
