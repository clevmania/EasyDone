package com.eclev.lawrence.easydone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by SYSTEM on 10/9/2017.
 */

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder>{
    private Context mContext;
    private List<todo> mTodo;

    public todoAdapter(Context context, List<todo> todo) {
        mContext = context;
        mTodo = todo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int LayoutTodoItems = R.layout.todo_list_items;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        Boolean shouldAddLayout = false;
        View view = inflater.inflate(LayoutTodoItems,parent,shouldAddLayout);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        todo mytodo = mTodo.get(position);
        holder.desc.setText(mytodo.getDesc());
        holder.duration.setText(mytodo.getDuration());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "You Clicked me", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTodo.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView desc,duration;

        public ViewHolder(View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.tv_title_item);
            duration = itemView.findViewById(R.id.tv_duration_item);
        }
    }
}
