package com.ee.project.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ee.project.R;
import com.ee.project.main.bean.MessageBean;
import com.ee.project.utils.util.DateUtils;
import java.util.List;

/**
 * Created by rnd on 2018/3/16.
 *
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    public List<MessageBean.MessageBeansBean> msgList;
    private Context context;
    private OnDragClickListener onDragClickListener;

    public MessageAdapter(Context context, List<MessageBean.MessageBeansBean> msgList,OnDragClickListener onDragClickListener){
        this.context = context;
        this.msgList = msgList;
        this.onDragClickListener = onDragClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_message_item,parent,false);
        final MessageAdapter.ViewHolder holder = new MessageAdapter.ViewHolder(view,onDragClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            MessageBean.MessageBeansBean msgbean = msgList.get(position);
            //类型为1 的时候为
            if (msgbean.getMessageType() == 1) {
                holder.msgImage.setImageResource(R.drawable.lone);
            }
            //依次判断
            holder.msgTitle.setText(msgbean.getMessageTitle());
            holder.msgCon.setText(msgbean.getMessageCon());
            String a = DateUtils.getDateToString(msgbean.getMessageTime());
            holder.msgTime.setText(a);

            if(msgbean.getMessageRead()==0){
                //显示
                holder.msgRead.setVisibility(View.VISIBLE);
            }else{
                //隐藏
                holder.msgRead.setVisibility(View.GONE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View msgview;
        ImageView msgImage;
        TextView msgTitle;
        TextView msgCon;
        TextView msgTime;
        TextView msgRead;
        OnDragClickListener onDragClickListener;
        Button btnEdit,btnDelete;

        public ViewHolder(View view,OnDragClickListener onDragClickListener){
            super(view);
            msgview = view;
            this.onDragClickListener = onDragClickListener;
            msgImage = view.findViewById(R.id.mMsgImage);
            msgTitle = view.findViewById(R.id.mMsgTitle);
            msgCon = view.findViewById(R.id.mMsgCon);
            msgTime = view.findViewById(R.id.mMsgTime);
            msgRead = view.findViewById(R.id.mMsgRead);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);

            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(onDragClickListener!=null){
                onDragClickListener.onItemClick(v,getAdapterPosition());
            }

            switch (v.getId()){
                case R.id.btnEdit:
                    if(onDragClickListener!=null){
                        onDragClickListener.onEditClick(v,getAdapterPosition());
                    }
                    break;

                case R.id.btnDelete:
                    if(onDragClickListener!=null){
                        onDragClickListener.onDeleteClick(v,getAdapterPosition());
                    }
                    break;
            }
        }
    }

    //自定义拖动接口
    public interface OnDragClickListener{
        void onEditClick(View view,int pos);
        void onDeleteClick(View view,int pos);
        void onItemClick(View view,int pos);
    }

}
