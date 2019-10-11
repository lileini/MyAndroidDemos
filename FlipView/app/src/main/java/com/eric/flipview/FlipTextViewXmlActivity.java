/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.flipview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.aphidmobile.flip.FlipViewController;
import com.eric.flipview.views.NumberTextView;
import com.eric.flipview.R;

/**
 * @author Paul Burke paulburke.co
 */
public class FlipTextViewXmlActivity extends FlipTextViewActivity {

  protected FlipViewController flipView;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(R.string.activity_title);
    setContentView(R.layout.xml_layout);

    flipView = (FlipViewController) findViewById(R.id.flipView);

    flipView.setAdapter(new BaseAdapter() {
      @Override
      public int getCount() {
        return 10;
      }

      @Override
      public Object getItem(int position) {
        return position;
      }

      @Override
      public long getItemId(int position) {
        return position;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        NumberTextView view;
        if (convertView == null) {
          final Context context = parent.getContext();
          view = new NumberTextView(context, position);
          view.setTextSize(context.getResources().getDimension(R.dimen.textSize));
        } else {
          view = (NumberTextView) convertView;
          view.setNumber(position);
        }

        return view;
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    flipView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    flipView.onPause();
  }

}
