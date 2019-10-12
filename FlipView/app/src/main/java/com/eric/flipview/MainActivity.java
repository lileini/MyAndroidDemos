/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.flipview;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setListAdapter(
        new SimpleAdapter(
            this, getData(), android.R.layout.simple_list_item_1, new String[]{"title"},
            new int[]{android.R.id.text1}
        )
    );
    getListView().setScrollbarFadingEnabled(false);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Intent intent = new Intent(
        Intent.ACTION_VIEW,
        Uri.parse("http://openaphid.github.com/")
    );
    startActivity(intent);

    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
    Intent intent = new Intent(this, (Class<? extends Activity>) map.get("activity"));
    startActivity(intent);
  }

  private List<? extends Map<String, ?>> getData() {
    List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
    addItem(data, "custome", FlipCustomeActivity.class);
    addItem(data, "TextViews", FlipTextViewActivity.class);
    addItem(data, "Buttons", FlipButtonActivity.class);
    addItem(data, "Complex Layouts", FlipComplexLayoutActivity.class);
    addItem(data, "Async Content", FlipAsyncContentActivity.class);
    addItem(data, "Event Listener", FlipTextViewAltActivity.class);
    addItem(data, "Horizontal", FlipHorizontalLayoutActivity.class);
    addItem(data, "Issue #5", Issue5Activity.class);
    addItem(data, "XML Configuration", FlipTextViewXmlActivity.class);
    addItem(data, "Fragment", FlipFragmentActivity.class);
    addItem(data, "Dynamic Adapter Size", FlipDynamicAdapterActivity.class);
    addItem(data, "WebView", FlipWebViewActivity.class);
    addItem(data, "Delete page", FlipDeleteAdapterActivity.class);
    addItem(data, "Issue #51", Issue51Activity.class);

    return data;
  }

  private void addItem(List<Map<String, Object>> data, String title,
                       Class<? extends Activity> activityClass) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("title", data.size() + ". " + title);
    map.put("activity", activityClass);
    data.add(map);
  }
}
