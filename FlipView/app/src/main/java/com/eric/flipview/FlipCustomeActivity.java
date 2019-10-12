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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aphidmobile.flip.FlipViewController;
import com.aphidmobile.utils.AphidLog;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlipCustomeActivity extends Activity {
    private static final String TAG = "FlipCustomeActivity";

    private FlipViewController flipView;
    String[] mStrs = {
            "http://img4.imgtn.bdimg.com/it/u=2708216596,905266878&fm=26&gp=0.jpg",
            "http://www.doubor.com/wp-content/uploads/2016/07/16469197481300558030.jpg",
            "http://www.doubor.com/wp-content/uploads/2016/07/10341462841304346712.jpg",
            "http://b-ssl.duitang.com/uploads/item/201607/13/20160713111614_QWUYN.png",
            "http://hbimg.b0.upaiyun.com/573a6afc2de66b2977d725af7493ec4240d12501107be-wnCew7_fw658",
            "http://b-ssl.duitang.com/uploads/item/201608/01/20160801172139_kzJCi.thumb.700_0.png",
            "http://b-ssl.duitang.com/uploads/item/201607/19/20160719120429_A4Bwu.thumb.700_0.png"

    };
    private CustomeAdapter mAdapter;
    private String mCurrentPic = null;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.activity_title);


        setContentView(R.layout.activity_custome);
        flipView = findViewById(R.id.flipView);
        mAdapter = new CustomeAdapter(this);
        String pic = getRandomPic();
        List<String> list = new ArrayList<>(3);
        list.add(pic);
        list.add(pic);
        list.add(pic);
        mAdapter.setDatas(list);
        flipView.setAdapter(mAdapter, 1);


    }

    private String getRandomPic() {
        Random ra = new Random();
        return mCurrentPic = mStrs[ra.nextInt(mStrs.length - 1)];
    }

    public void onNext(View v) {
        mAdapter.setNextData(mCurrentPic,getRandomPic());
        flipView.setSelection(1);
        flipView.forwardPage(true);
    }


    public void onPrevious(View v) {
        mAdapter.setPreviousData(getRandomPic());
        flipView.setSelection(1);
        flipView.forwardPage(false);
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

    public static class CustomeAdapter extends BaseAdapter {

        private LayoutInflater inflater;

//        private int repeatCount = 1;
        private Context mContext;

        private List<String> mList;
        private int currentPos;

        public CustomeAdapter(Context context) {
            mContext = context;
            inflater = LayoutInflater.from(context);
            mList = new ArrayList<>(3);
        }

        public void setNextData(String currentPic,String s) {
//            int index = (currentPos + 1) % 3;
            Log.d(TAG, "setNextData: currentPos= "+currentPos);
            int index = 2;

            mList.set(1,currentPic);
            mList.add(index, s);
        }

        public void setPreviousData(String s) {
            /*int pos = currentPos - 1;
            if (pos < 0) {
               pos = 3- Math.abs(pos);
            }
            mList.add((pos) % 3, s);*/

            int index = 2;

            mList.set(1,mList.get(currentPos));
            mList.add(index, s);
        }

        public void setDatas(List<String> list) {
            mList = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }


        @Override
        public String getItem(int position) {
            return mList.get(position % 3);
        }

        @Override
        public long getItemId(int position) {
            return position % 3;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "getView: position = "+position);
            currentPos = position;
            View layout = convertView;
            if (convertView == null) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                layout = imageView;
                AphidLog.d("created new view from adapter: %d", position);
            }
            Glide.with(mContext).load(getItem(position)).into((ImageView) layout);


            return layout;
        }

        public void removeData(int index) {
            if (mList.size() > 1) {
                mList.remove(index);
            }
        }
    }
}
