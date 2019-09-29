package devcrema.android_super_simple_paging.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import devcrema.android_super_simple_paging.R;
import devcrema.android_super_simple_paging.SimplePagingRecyclerAdapter;

public class SampleAdapter extends SimplePagingRecyclerAdapter<SampleItem> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SampleViewHolder(inflater.inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SampleViewHolder sampleViewHolder = (SampleViewHolder) holder;
        sampleViewHolder.getId().setText(String.valueOf(getItemByPosition(position).getId()));
        sampleViewHolder.getContent().setText(getItemByPosition(position).getContent());
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView content;

        public SampleViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.main_item_id);
            content = itemView.findViewById(R.id.main_item_content);
        }

        public TextView getId() {
            return id;
        }

        public TextView getContent() {
            return content;
        }
    }
}
