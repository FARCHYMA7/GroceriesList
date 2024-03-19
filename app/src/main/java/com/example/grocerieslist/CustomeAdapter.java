package com.example.grocerieslist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeVH> {
    ArrayList<DataModel> dataSet;

    public CustomeAdapter(ArrayList<DataModel> dataSet){
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);

        return new CustomeVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeVH holder, int position) {
        TextView textViewItem = holder.textViewItem;
        textViewItem.setText(dataSet.get(position).getNameItem());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

class CustomeVH extends RecyclerView.ViewHolder{

    TextView textViewItem;
    TextView textViewAmount;
    private CustomeAdapter adapter;

    public CustomeVH(@NonNull View itemView) {
        super(itemView);

        textViewItem = itemView.findViewById(R.id.item_name);
        textViewAmount = itemView.findViewById(R.id.textView_amount);
        itemView.findViewById(R.id.btn_delete).setOnClickListener(view -> {
            adapter.dataSet.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());

        });

        itemView.findViewById(R.id.imageView_plus).setOnClickListener(view -> {
            String a = String.valueOf(textViewAmount.getText());
            int num = Integer.parseInt(a) + 1 ;
            textViewAmount.setText((Integer.toString(num)));
            adapter.dataSet.get(getAdapterPosition()).setAmount(num);

        });

        itemView.findViewById(R.id.imageView_minus).setOnClickListener(view -> {
            String a = String.valueOf(textViewAmount.getText());
            int num = Integer.parseInt(a);
            if (num != 1)
            {
                num--;
                textViewAmount.setText((Integer.toString(num)));
                adapter.dataSet.get(getAdapterPosition()).setAmount(num);
            }
        });

    }

    public CustomeVH linkAdapter(CustomeAdapter adapter){
        this.adapter = adapter;
        return this;

    }
}



