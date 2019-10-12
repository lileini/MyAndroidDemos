/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.flipview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.aphidmobile.flip.FlipViewController;
import com.eric.flipview.R;

public class Issue51Activity extends Activity {

  private FlipViewController flipView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle(R.string.activity_title);

    flipView = new FlipViewController(this);

    flipView.setAdapter(new MyBaseAdapter(this));

    setContentView(flipView);
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

  private static class MyBaseAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    private MyBaseAdapter(Context context) {
      inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
      return 3;
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
      if (position == 0) {
        return inflater.inflate(R.layout.page1, null);
      } else if (position == 1) {
        return inflater.inflate(R.layout.page2, null);
      } else {
        return inflater.inflate(R.layout.page3, null);
      }
    }
  }
}
