package com.ee.project.main.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.ee.project.R;
import com.ee.project.base.ui.fragment.BaseMvpFragment;
import com.ee.project.main.adapter.MessageAdapter;
import com.ee.project.main.bean.MessageBean;
import com.ee.project.main.presenter.MessagePresenter;
import com.ee.project.main.presenter.view.MessageView;
import com.ee.project.utils.util.DividerItemDecoration;
import com.ee.project.utils.util.HandlerUtils;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 *
 */

public class MessageFragment extends BaseMvpFragment<MessagePresenter> implements MessageView,OnRefreshListener,
        OnLoadMoreListener ,MessageAdapter.OnDragClickListener,HandlerUtils.OnReceiveMessageListener,MultiStateView.StateListener {
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private HandlerUtils.HandlerHolder handlerUtils;
    List<MessageBean.MessageBeansBean> msgList;
    private String errorMsg;
    private MultiStateView mMultiStateView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        swipeToLoadLayout = view.findViewById(R.id.swipeToLoadLayout);
        recyclerView = view.findViewById(R.id.swipe_target);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        swipeToLoadLayout.setRefreshHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_refresh_header, swipeToLoadLayout, false));
        /*设置上拉加载更多布局*/
        swipeToLoadLayout.setLoadMoreFooterView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_refresh_footer, swipeToLoadLayout, false));

        // 设置监听器
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);//上拉;

        mMultiStateView = view.findViewById(R.id.multiStateView);
        mMultiStateView.setStateListener(this);
        //重新加载数据
        mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR).findViewById(R.id.retry)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
                        Toast.makeText(getActivity(), "加载中...", Toast.LENGTH_SHORT).show();
                        Message msg = handlerUtils.obtainMessage();
                        msg.obj = mMultiStateView;
                        getData();
                        handlerUtils.sendMessageDelayed(msg, 3000);

                    }
                });

    }

    private void initData(){
        handlerUtils = new HandlerUtils.HandlerHolder(this);
        msgList = new ArrayList<>();
        getData();
    }

    private void getData(){
        //直接获取网络数据
        mPresenter.getMessage(60,"admin",this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }



    @Override
    public void messageSuccess(List<MessageBean.MessageBeansBean> messageBeansBeans) {
        if(messageBeansBeans.size()>0){
            msgList = messageBeansBeans;
            handlerUtils.sendEmptyMessage(200);
        }else{
            handlerUtils.sendEmptyMessage(1);
        }
    }

    @Override
    public void messageError(String message) {
        //Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
        errorMsg = message;
        handlerUtils.sendEmptyMessage(0);
    }


    @Override
    public void messageFail(String message) {
        errorMsg = message;
        handlerUtils.sendEmptyMessage(1);
    }

    /**
     * 加载数据的
     */
    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //getDevice();
                swipeToLoadLayout.setLoadingMore(false);
            }
        },2000);
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //getDevice();
                swipeToLoadLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what){
            case 0:
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
                break;

            case 1:
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
                break;

            case 200:
                if(messageAdapter==null){
                    messageAdapter = new MessageAdapter(getActivity(),msgList,this);
                    recyclerView.setAdapter(messageAdapter);
                }else{
                    messageAdapter.msgList = msgList;
                    messageAdapter.notifyDataSetChanged();
                }
                break;
        }
    }


    /**
     * 点击按钮事件区块
     * @param view
     * @param pos
     */
    @Override
    public void onEditClick(View view, int pos) {

    }

    @Override
    public void onDeleteClick(View view, int pos) {

    }

    @Override
    public void onItemClick(View view, int pos) {

    }

    @Override
    public void onStateChanged(int i) {
        Log.v("MSVSample", "onStateChanged; viewState: " + i);
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.error:
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
                return true;

            case R.id.empty:
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
                return true;

            case R.id.content:
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                return true;

            case R.id.loading:
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
