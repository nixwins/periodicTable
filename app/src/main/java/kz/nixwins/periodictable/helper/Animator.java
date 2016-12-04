package kz.nixwins.periodictable.helper;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Random;

import kz.nixwins.periodictable.R;

/**
 * Created by nixwins on 11/29/16.
 */

public class Animator {

    private Context context;
    private Animation animation;

    public Animator(Context context) {
        this.context = context;
    }

    public void animLeftToRight(View view){

        animation = AnimationUtils.loadAnimation(context,
                R.anim.left2right_anitmation);
        view.startAnimation(animation);
    }

    public void animRightToLeft(View view){
        animation = AnimationUtils.loadAnimation(context, R.anim.right2left_animation);
        view.startAnimation(animation);

    }

    public void animZoomIn(View view){
        animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in_animation);
        view.startAnimation(animation);
    }

    public void animBlink(View view){
        animation = AnimationUtils.loadAnimation(context, R.anim.blink_animation);
        view.startAnimation(animation);
    }

    public  void animRandom(View view){
        Random rand = new Random();
        switch (rand.nextInt(2)){
            case 1:
               animLeftToRight(view);
                break;
            case 2:
                animRightToLeft(view);
                break;
        }
    }
}
