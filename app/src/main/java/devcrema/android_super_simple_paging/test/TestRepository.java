package devcrema.android_super_simple_paging.test;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

public class TestRepository {

    private static TestRepository testRepository = null;

    private static final int MAX_COUNT = 33;

    public static TestRepository getInstance() {
        if (testRepository == null) testRepository = new TestRepository();
        return testRepository;
    }

    private TestRepository() {
    }

    public interface GetItemCallback {
        void onSuccess(List<SampleItem> sampleItems);
    }

    public void getItems(final int page, final int size, final GetItemCallback callback) {
        new TestExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(generateTestStub(page, size));
                    }
                });
            }
        });
    }

    private static List<SampleItem> generateTestStub(int page, int size) {
        List<SampleItem> sampleItems = new ArrayList<>();
        int pageCount = page * size + size;
        int max = (pageCount < MAX_COUNT) ? pageCount : MAX_COUNT;
        for (int i = page * size; i < max; i++) {
            sampleItems.add(new SampleItem((long) i, "content " + i));
        }
        return sampleItems;
    }

}
