package com.example.survey.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.survey.R;
import com.example.survey.json.Store;


public class CustomAdapter extends ListAdapter<Object, AbstractViewHolder<Object>> {


    private ItemClickListener listener;
    public CustomAdapter(ItemClickListener listener) {
        super(new DiffItemCallbackClass<Object>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if (viewType == R.layout.store_holder_item_row) {
            return new CustomViewHolder(view, listener);
        }else {
            return new EmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder<Object> holder, int position) {
        holder.present(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof Store) {
            return R.layout.store_holder_item_row;
        }else {
            return R.layout.empty_holder_item_row;
        }
    }


}