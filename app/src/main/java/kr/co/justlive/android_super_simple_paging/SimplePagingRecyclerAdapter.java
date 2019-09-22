package kr.co.justlive.android_super_simple_paging;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class SimplePagingRecyclerAdapter <Item> extends RecyclerView.Adapter {

    public List<Item> items = new ArrayList<>();

    public List<Item> getItems(){
        return items;
    }

    public void addItems(Collection<Item> addedItem){
        items.addAll(addedItem);
        notifyDataSetChanged();
    }

    public void clearItems(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
