package kr.co.justlive.android_super_simple_paging;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;

public class SimplePaging<T> {

    private SimplePagingRecyclerAdapter<T> adapter;
    private RecyclerView recyclerView;
    private OnNextPageCallback onNextPageCallback;

    //TODO change to builder pattern
    private int page = 0;
    private int size = 10;
    private int preloadCount = 1;

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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (!(layoutManager instanceof LinearLayoutManager)) return;

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                //TODO adjust preloadCount
                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= size) {
                        isLoading = true;
                        onNextPageCallback.onNextPage(SimplePaging.this, page, size);
                    }
                }
            }
        });
    }

}
