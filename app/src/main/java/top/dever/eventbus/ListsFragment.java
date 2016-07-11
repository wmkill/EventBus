package top.dever.eventbus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

public class ListsFragment extends Fragment implements
        AdapterView.OnItemClickListener {

    private ListView mListView;
    private ArrayList<Item> mItemList;

    public ListsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mItemList = new ArrayList();
        for(int i=0;i<20;i++) {
            mItemList.add(new Item(i, "item" + i));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setAdapter(new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, mItemList));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mListView = ((ListView) view.findViewById(R.id.list));
        mListView.setOnItemClickListener(this);
        return view;
    }

    /**
     * 如果项目是在Android Studio中，
     * 需要在onEventMainThread 这个回调方法上加上 @Subscribe 注解
     * Eclipse中则不需要
     * @param
     */
    @Subscribe
    public void onEventMainThread(String event){
        if(event.equals("ok")){
            /**
             * 在DetailFragment界面判断如果界面加载完成，发送事件
             * 如果在onEventMainThread中接收到相应的事件，
             * 则说明加载完成，可以去更新数据了
             *
             * 为什么要判断页面是否加载完成？
             * 因为在ListsFragment中去发送item的时候，是在当前界面发送的，
             * 并不知道接收页面是否加载完毕，所以需要在接收界面加载完毕后，发送相关数据，通知发送界面
             *
             * 以上仅当显示默认数据时需要
             */
            //当布局和数据加载完后，发送第一个Item，用于默认显示；
            EventBus.getDefault().post(mItemList.get(0));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        Item item = mItemList.get(position);
        EventBus.getDefault().post(item);
    }
}
