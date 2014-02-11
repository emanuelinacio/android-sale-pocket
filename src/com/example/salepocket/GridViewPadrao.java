package com.example.salepocket;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridViewPadrao extends BaseAdapter {

private Context context;
	
	private String[] grade;
	

	
	public GridViewPadrao(Context context, String[] GRADE) {
        this.context = context;
        this.grade = GRADE;
    }



	public int getCount() {
        return grade.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            tv = new TextView(context);
            tv.setLayoutParams(new GridView.LayoutParams(50, 30));
            tv.setTextSize(15);
        }
        else {
            tv = (TextView) convertView;
        }

            tv.setText(this.grade[position]);
        return tv;
    }
	
}
