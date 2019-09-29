package devcrema.android_super_simple_paging.test;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devcrema.android_super_simple_paging.OnNextPageCallback;
import devcrema.android_super_simple_paging.R;
import devcrema.android_super_simple_paging.SimplePaging;
import devcrema.android_super_simple_paging.SimplePagingRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SimplePagingRecyclerAdapter<SampleItem> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SampleAdapter();
        recyclerView.setAdapter(adapter);

        SimplePaging<SampleItem> paging = new SimplePaging<>(adapter, recyclerView);

        paging.startPaging(new OnNextPageCallback() {
            @Override
            public void onNextPage(final SimplePaging simplePaging, int page, int size) {
                //network fetch
                Toast.makeText(MainActivity.this, "call " + page + "/" + size, Toast.LENGTH_SHORT).show();
                TestRepository.getInstance().getItems(page, size, new TestRepository.GetItemCallback() {
                    @Override
                    public void onSuccess(List<SampleItem> sampleItems) {
                        simplePaging.nextPage(sampleItems);
                    }
                });
            }
        });
    }
}
