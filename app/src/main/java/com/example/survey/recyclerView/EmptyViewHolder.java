package com.example.survey.recyclerView;

import android.view.View;

import androidx.annotation.NonNull;

public class EmptyViewHolder extends AbstractViewHolder<Object> {
    public EmptyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void present(Object data) {

    }
}
