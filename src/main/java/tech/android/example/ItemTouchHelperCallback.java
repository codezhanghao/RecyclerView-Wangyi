package tech.android.example;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by hzh on 2016/7/8.
 * 该类工作与ItemTouchHelper和你的app之间，起一个桥梁的作用
 * 主要负责，定义用户drag和swipe的方向，以及当户产生了指定手势会收到相应的回调方法
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback
{
    private OnItemPositionChangeListener  mListener;

    //通过构造函数，设置接口实例
    public ItemTouchHelperCallback(OnItemPositionChangeListener mListener)
    {
        this.mListener = mListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        //the direction of item which be dragged
        final int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                | ItemTouchHelper.UP | ItemTouchHelper.DOWN;


        //can be dragged, can not be swiped
        return makeMovementFlags(dragFlags, 0);
    }

    //接口回调，Adapter根据这个接口交换item的位置
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
    {
        if(mListener != null) {
            return mListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }

        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {

    }

    public interface OnItemPositionChangeListener
    {
        boolean onItemMove(int fromPos, int toPos);
    }
}
