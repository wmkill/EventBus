package top.dever.eventbus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {

    private FrameLayout mListFragment,mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListFragment = ((FrameLayout)findViewById(R.id.list_fragment));
        mDetailFragment = ((FrameLayout)findViewById(R.id.detail_fragment));


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.list_fragment,new ListsFragment());
        transaction.add(R.id.detail_fragment,new DetailFragment());
        transaction.commit();
    }
}
