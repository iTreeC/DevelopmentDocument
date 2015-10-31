package com.itree.itreerecruit.layout_util;

import android.app.Activity;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

import com.itree.itreerecruit.R;


/**
 * Created by guanjiwei on 2015/10/18.
 */
public class Layout_viewflipper extends Activity {

    ViewFlipper viewFlipper = null;
    float startX;
    private void init() {
        viewFlipper = (android.widget.ViewFlipper) this.findViewById(R.id.viewFlipper);

    }

    

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_UP:

                if (event.getX() > startX) { // 向右滑动
                    viewFlipper.setInAnimation(this, R.anim.in_leftright);
                    viewFlipper.setOutAnimation(this, R.anim.out_leftright);
                    viewFlipper.showNext();
                } else if (event.getX() < startX) { // 向左滑动
                    viewFlipper.setInAnimation(this, R.anim.in_rightleft);
                    viewFlipper.setOutAnimation(this, R.anim.out_rightleft);
                    viewFlipper.showPrevious();
                }
                break;
        }

        return super.onTouchEvent(event);
    }
}
