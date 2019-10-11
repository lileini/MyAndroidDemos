/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.flipview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;


import com.eric.flipview.R;
import com.aphidmobile.utils.UI;

public class FlipFragmentActivity extends FragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_layout);

    Button button = UI.findViewById(this, R.id.toggle_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.flip_text_view_fragment);
        if (fragment.isHidden()) {
          fragmentManager.beginTransaction().show(fragment).commit();
          ((Button) v).setText(R.string.hide_fragment);
        } else {
          fragmentManager.beginTransaction().hide(fragment).commit();
          ((Button) v).setText(R.string.show_fragment);
        }
      }
    });
  }
}
