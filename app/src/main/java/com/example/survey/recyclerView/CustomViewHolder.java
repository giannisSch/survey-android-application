package com.example.survey.recyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.survey.R;
import com.example.survey.json.Store;


public class CustomViewHolder extends AbstractViewHolder<Object> {
    private TextView branchNameText;
    private TextView branchAreaText;
    private TextView branchCityText;



    public CustomViewHolder(@NonNull View itemView, ItemClickListener listener) {
        super(itemView);
        setListener(listener);
        branchNameText = itemView.findViewById(R.id.branchNameTextView);
        branchAreaText = itemView.findViewById(R.id.branchAreaTextView);
        branchCityText = itemView.findViewById(R.id.branchCityTextView);
    }


    @Override
    public void present(Object data) {
        setData(data);
        if (data instanceof Store) {
            branchNameText.setText(((Store) data).getBranchName());
            branchCityText.setText(((Store) data).getBranchCity());
            branchAreaText.setText(((Store) data).getBranchArea());
        }else{
            //Do something for better User Experience
        }

    }
}
