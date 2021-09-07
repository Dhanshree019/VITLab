package com.example.vitlab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder>{

    private ArrayList<StaticRvModel> item;
    private Context context;
    int row_index=-1;

    public StaticRvAdapter(ArrayList<StaticRvModel> item,Context context){
        this.item=item;
        this.context=context;

    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent, false );
        StaticRVViewHolder staticRVViewHolder=new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, int position){
        StaticRvModel currentItem=item.get(position);
        holder.img.setImageResource(currentItem.getImage());
        holder.lab.setText(currentItem.getLab());
        holder.incharge.setText(currentItem.getIncharge());
        holder.time.setText(currentItem.getTime());
        holder.lunchtime.setText(currentItem.getLunchtime());
        holder.rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(),Details.class);
                intent.putExtra("Lab",currentItem.getLab());
                intent.putExtra("Time",currentItem.getTime());
                intent.putExtra("Incharge",currentItem.getIncharge());
                intent.putExtra("Lunchtime",currentItem.getLunchtime());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);

            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

            }
        });
        if(row_index==position){
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
        }
        else{
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
        }

    }
    @Override
    public int getItemCount(){
        return item.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView incharge,lab,time,lunchtime;
        ImageView img;
        LinearLayout linearLayout;
        ConstraintLayout rv;

        public StaticRVViewHolder(@NonNull View itemView){

            super(itemView);
            img=itemView.findViewById(R.id.img);
            lab=itemView.findViewById(R.id.lab);
            incharge=itemView.findViewById(R.id.incharge);
            time=itemView.findViewById(R.id.time);
            lunchtime=itemView.findViewById(R.id.lunchtime);
            linearLayout=itemView.findViewById(R.id.linearlayout);

            rv=itemView.findViewById(R.id.rv);
        }



    }
}
