package com.merkost.myexams.ui.timer

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

/*
public class MyTimer : RelativeLayout() {
    val context: Context;
    private ImageView imgView;
    private TextView lblView;
    LayoutInflater inflater;
*/
/*Do I need all three constructors for an Android custom view?*//*


//you will need the constructor public MyView(Context context, AttributeSet attrs), otherwise you will get an Exception when Android tries to inflate your View.
    public CustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

//The third constructor is usually used when you extend a style and customize it, and then you would like to set that style to a given View in your layouts
    public CustomView(Context context)
    {
        super(context);
        this.context = context;
        init();
    }



    public void init()
    {
        LayoutInflater.from(context).inflate(R.layout.widget_customview_main, this);
        lblView = (TextView) findViewById (R.id.lblView);
        imgView = (ImageView) findViewById (R.id.imgView);
    }
}*/
