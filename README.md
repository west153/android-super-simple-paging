# android-super-simple-paging-library

android paging does not supporting about nestedScrollView with RecyclerView 
and create so many classes to use.

I want to make it simple.

## RecyclerView Paging

1. extends SimplePagingRecyclerAdapter

```java
public class SampleAdapter extends SimplePagingRecyclerAdapter<SampleItem> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view holder
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //bind view holder
        //get item by position
        getItemByPosition(position);
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {
        
        public SampleViewHolder(@NonNull View itemView) {
            super(itemView);
            //item bind
        }

    }
}
```

2. create SimplePaging.

```java
SimplePagingRecyclerAdapter adapter = new SampleAdapter();
SimplePaging<SampleItem> paging = new SimplePaging<>(adapter, recyclerView);

```

3. start paging and fetching data.

```java
paging.startPaging(new OnNextPageCallback() {
            @Override
            public void onNextPage(final SimplePaging simplePaging, int page, int size) {
                //data fetch example
                TestRepository.getInstance().getItems(page, size, new TestRepository.GetItemCallback() {
                    @Override
                    public void onSuccess(List<SampleItem> sampleItems) {
                        simplePaging.nextPage(sampleItems);
                    }
                });
            }
        });
```