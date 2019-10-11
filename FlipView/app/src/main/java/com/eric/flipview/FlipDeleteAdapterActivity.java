/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.flipview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aphidmobile.flip.FlipViewController;
import com.eric.flipview.adapter.TravelAdapter;
import com.eric.flipview.R;

public class FlipDeleteAdapterActivity extends Activity {

  private FlipViewController flipView;
  private TravelAdapter adapter;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(R.string.activity_title);

    flipView = new FlipViewController(this);

    //Use RGB_565 can reduce peak memory usage on large screen device, but it's up to you to choose the best bitmap format 
    flipView.setAnimationBitmapFormat(Bitmap.Config.RGB_565);

    adapter = new TravelAdapter(this);
    flipView.setAdapter(adapter);

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    menu.add("Delete Page");
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    adapter.removeData(flipView.getSelectedItemPosition());
    adapter.notifyDataSetChanged();
    return true;
  }
}
