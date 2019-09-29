package devcrema.android_super_simple_paging.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor implements Executor {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void execute(Runnable command) {
        executorService.execute(command);
    }
}
