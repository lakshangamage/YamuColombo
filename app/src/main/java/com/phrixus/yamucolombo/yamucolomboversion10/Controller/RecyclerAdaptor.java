package com.phrixus.yamucolombo.yamucolomboversion10.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phrixus.yamucolombo.yamucolomboversion10.R;
import com.phrixus.yamucolombo.yamucolomboversion10.view.RouteInfoActivity;

/**
 * Created by Lakshan on 2016-05-03.
 */
public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.RecyclerViewHolder>{
    String[] routeNumbers, from_to, routeinfo;
    Context context;
    public RecyclerAdaptor(String[] routeNumbers, String[] from_to, String[] routeinfo,Context context ){
        this.routeNumbers = routeNumbers;
        this.from_to = from_to;
        this.routeinfo = routeinfo;
        this.context = context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,context,routeNumbers,routeinfo);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.route_no_txt.setText(routeNumbers[position]);
        holder.from_to_txt.setText(from_to[position]);
    }

    @Override
    public int getItemCount() {
        return routeNumbers.length;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView route_no_txt, from_to_txt;
        String[] route_no, routeinfo;
        Context context;
        public RecyclerViewHolder(View itemView, Context context, String[] route_no,String[] routeinfo) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.route_no=route_no;
            this.routeinfo=routeinfo;
            this.context = context;
            route_no_txt = (TextView) itemView.findViewById(R.id.route_no);
            from_to_txt = (TextView) itemView.findViewById(R.id.route_from_to);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(this.context, RouteInfoActivity.class);
            intent.putExtra("route_no",route_no[position]);
            intent.putExtra("routeinfo",routeinfo[position]);
            this.context.startActivity(intent);
        }
    }
}
