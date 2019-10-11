/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.flipview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aphidmobile.flip.FlipViewController;

public class FlipTextViewAltActivity extends FlipTextViewActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    flipView.setOnViewFlipListener(new FlipViewController.ViewFlipListener() {

      @Override
      public void onViewFlipped(View view, int position) {
        Toast.makeText(view.getContext(), "Flipped to page " + position, Toast.LENGTH_SHORT).show();
      }
    });
  }

}
