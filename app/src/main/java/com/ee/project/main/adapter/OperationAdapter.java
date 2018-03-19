package com.ee.project.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ee.project.R;
import com.ee.project.main.bean.OpDataBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 *
 */

public class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.ViewHolder>{
    private OnItemClickListener onItemClickListener;
    public List<OpDataBean> oplist;
    private Context context;

    public OperationAdapter(Context context,List<OpDataBean> oplist,OnItemClickListener onItemClickListener){
        this.context = context;
        this.oplist = oplist;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_operation_item,parent,false);
        final OperationAdapter.ViewHolder holder = new OperationAdapter.ViewHolder(view,onItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            OpDataBean opDataBean = oplist.get(position);
            holder.mOpImage.setImageResource(opDataBean.getOpImage());
            holder.mOpTitle.setText(opDataBean.getOpTitle());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return oplist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View opview;
        ImageView mOpImage;
        TextView mOpTitle;
        OnItemClickListener onItemClickListener;

        public ViewHolder(View view,OnItemClickListener onItemClickListener){
            super(view);
            opview = view;
            this.onItemClickListener = onItemClickListener;
            mOpImage = view.findViewById(R.id.mOpImage);
            mOpTitle = view.findViewById(R.id.mOpTitle);
            opview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onItemClickListener!=null){
                onItemClickListener.OnItemClick(view,getAdapterPosition());
            }
        }


    }

    //自定义view
    public interface OnItemClickListener{
        void OnItemClick(View view,int pos);
    }
}
