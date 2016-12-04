package kz.nixwins.periodictable.ui.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by nixwins on 12/1/16.
 */

public class ElementTextView extends TextView {

    public ElementTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ElementTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ElementTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Light.ttf");
            setTypeface(tf);
        }
    }

}
