package top.dever.eventbus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    private TextView mTxt;

    public DetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().post("ok");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mTxt = ((TextView) view.findViewById(R.id.txt));
        return view;
    }

    /** List点击时会发送些事件，接收到事件后更新详情 */
    @Subscribe
    public void onEventBackgroundThread(final Item item){
        Log.d("onEventMainThread", item.toString());
        Log.d("onEventMainThread", ""+mTxt.getId());
        if (item != null)
            mTxt.setText(item.getString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
