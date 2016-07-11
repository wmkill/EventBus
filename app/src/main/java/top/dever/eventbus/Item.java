package top.dever.eventbus;

/**
 * Created by Android Studio
 * Time: 2016/7/11 10:34
 * Author: wangmeng
 */
public class Item {
    private int id;
    private String mString;

    public Item(int id, String string) {
        this.id = id;
        mString = string;
    }

    public String getString() {
        return mString;
    }

    @Override
    public String toString() {
        return mString;
    }
}
