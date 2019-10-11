/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.flipview;

import android.app.Activity;
import android.os.Bundle;

import com.aphidmobile.flip.FlipViewController;
import com.eric.flipview.issue5.GalleryFlipAdapter;
import com.eric.flipview.R;

public class Issue5Activity extends Activity {

  private FlipViewController flipView;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(R.string.activity_title);

    flipView = new FlipViewController(this);

    flipView.setAdapter(new GalleryFlipAdapter(this, flipView));

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
}