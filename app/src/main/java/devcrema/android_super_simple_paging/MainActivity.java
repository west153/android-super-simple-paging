package devcrema.android_super_simple_paging;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SimplePagingRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimplePaging paging = new SimplePaging<>(adapter, recyclerView);

        paging.startPaging(new OnNextPageCallback() {
            @Override
            public void onNextPage(SimplePaging simplePaging, int page, int size) {
                //network fetch
                simplePaging.nextPage(Arrays.asList(new Object()));
            }
        });
    }
}
