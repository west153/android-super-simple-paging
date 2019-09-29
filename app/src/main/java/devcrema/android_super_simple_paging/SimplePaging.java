package devcrema.android_super_simple_paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;

public class SimplePaging<T> {

    private SimplePagingRecyclerAdapter<T> adapter;
    private RecyclerView recyclerView;
    private OnNextPageCallback onNextPageCallback;

    private int page = 0;
    private int size = 10;
    private int preloadCount = 3;

    private boolean isLoading = false;
    private boolean isLastPage = false;

    public SimplePaging(SimplePagingRecyclerAdapter<T> adapter, RecyclerView recyclerView) {
        this.adapter = adapter;
        this.recyclerView = recyclerView;
    }

    public SimplePaging(SimplePagingRecyclerAdapter<T> adapter, RecyclerView recyclerView, int page, int size){
        this(adapter, recyclerView);
        this.page = page;
        this.size = size;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPreloadCount(int preloadCount) {
        this.preloadCount = preloadCount;
    }

    public void nextPage(Collection<T> items) {
        isLoading = false;
        if(items == null || items.size() <= 0) {
            this.isLastPage = true;
            return;
        }
        if(items.size() < size) this.isLastPage = true;
        adapter.addItems(items);
    }

    public void startPaging(final OnNextPageCallback onNextPageCallback){
        this.onNextPageCallback = onNextPageCallback;
        onNextPageCallback.onNextPage(SimplePaging.this, page, size);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (!(layoutManager instanceof LinearLayoutManager)) return;

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    Log.d("PAGING_LOG", "visibleItemCount: "+ visibleItemCount
                            + " firstVisibleItemPosition: "+ firstVisibleItemPosition +
                            " totalItemCount: "+ totalItemCount);
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - preloadCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= size) {
                        isLoading = true;
                        page++;
                        onNextPageCallback.onNextPage(SimplePaging.this, page, size);
                    }
                }
            }
        });
    }

}
