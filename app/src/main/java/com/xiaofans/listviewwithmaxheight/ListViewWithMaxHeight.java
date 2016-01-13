package com.xiaofans.listviewwithmaxheight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * Created by zhaoyu on 2016/1/13.
 */
public class ListViewWithMaxHeight extends ListView {
  public static int WITHOUT_MAX_HEIGHT_VALUE = -1;

  private int maxHeight = WITHOUT_MAX_HEIGHT_VALUE;

  public ListViewWithMaxHeight(Context context) {
    super(context);
  }

  public ListViewWithMaxHeight(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray a = context.getTheme().obtainStyledAttributes(
        attrs,
        R.styleable.ListViewWithMaxHeight,
        0, 0);

    try {
      setMaxHeight(a.getDimensionPixelSize(R.styleable.ListViewWithMaxHeight_maxHeight, 0));
    } finally {
      a.recycle();
    }
  }


  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    try {
      int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
      if (maxHeight != WITHOUT_MAX_HEIGHT_VALUE
          && heightSize > maxHeight) {
        heightSize = maxHeight;
      }
      heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(heightSize, View.MeasureSpec.AT_MOST);
      getLayoutParams().height = heightSize;
    } catch (Exception e) {
    } finally {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
  }

  public void setMaxHeight(int maxHeight) {
    Log.w("ListViewMaxHeight", "maxHeight is:" + maxHeight);
    this.maxHeight = maxHeight;
  }
}
