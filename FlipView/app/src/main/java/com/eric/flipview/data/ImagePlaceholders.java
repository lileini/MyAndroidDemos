/*
 * ==============================================================
 *   Copyright (C) China TSP Company Limited 2019
 * ==============================================================
 *
 */

package com.eric.flipview.data;

import com.aphidmobile.utils.AphidLog;

public class ImagePlaceholders {

  public static final
  String[]
      CATEGORIES =
      {"abstract", "animals", "city", "food", "nightlife", "fashion", "people",
       "nature", "sports", "technics", "transport"};

  public static String getImageUrl(String category, int w, int h) {
    return AphidLog.format("http://lorempixel.com/%d/%d/%s/1/", w, h, category);
  }
}
