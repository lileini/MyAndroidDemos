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
import android.view.Gravity;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;

import com.aphidmobile.flip.FlipViewController;
import com.eric.flipview.adapter.TravelAdapter;

public class FlipHorizontalLayoutActivity extends Activity {

  private FlipViewController flipView;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(R.string.activity_title);

    flipView = new FlipViewController(this, FlipViewController.HORIZONTAL);

    flipView.setAdapter(new TravelAdapter(this));
    flipView.setAnimationBitmapFormat(Bitmap.Config.ARGB_4444);

    setContentView(flipView);
    FrameLayout parent = (FrameLayout) flipView.getParent();
    Button button1 = new Button(this);
    button1.setText("下一页");
    FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
    params1.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
    parent.addView(button1,params1);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        flipView.forwardPage(true);
      }
    });


    Button button2 = new Button(this);
    button2.setText("上一页");
    FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
    params2.gravity = Gravity.CENTER_VERTICAL;
    parent.addView(button2,params2);
    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        flipView.forwardPage(false);
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
