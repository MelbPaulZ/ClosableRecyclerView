package com.developer.paul.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.developer.paul.recyclerview.itemDecorators.DividerItemDecorator;

/**
 * Created by Paul on 2/5/17.
 */

public class ClosableRecyclerViewUtil {
    public static Drawable getIconRes(Context context, String type){
        switch (type){
            case "Location":
                return context.getResources().getDrawable(R.drawable.contact_female_icon);
            case "Repeat":
                return context.getResources().getDrawable(R.drawable.contact_male_icon);
            case "Note" :
                return context.getResources().getDrawable(R.drawable.search_icon_square);
            default:
                return context.getResources().getDrawable(R.drawable.icon_bg_backarrow);
        }
    }

}
