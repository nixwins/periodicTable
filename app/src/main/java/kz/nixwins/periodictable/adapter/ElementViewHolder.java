package kz.nixwins.periodictable.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.apache.commons.lang3.text.WordUtils;

import kz.nixwins.periodictable.R;
import kz.nixwins.periodictable.helper.Animator;
import kz.nixwins.periodictable.helper.ElementColor;
import kz.nixwins.periodictable.helper.ParseString;
import kz.nixwins.periodictable.model.Element;
import kz.nixwins.periodictable.ui.activity.ElementActivity;

/**
 * Created by nixwins on 11/23/16.
 */

public class ElementViewHolder extends RecyclerView.ViewHolder  {

    private View itemView;
    private LinearLayout containerLl;

    private TextView symbol;
    private TextView  name;
    private TextView latinName;
    private TextView atomicNumber;
    private TextView atomicWeight;
    private TextView electronConfig;
    private TextView electronEngergyLvl;

    public ElementViewHolder(final View itemView) {

        super(itemView);
        this.itemView = itemView;
        containerLl = (LinearLayout) itemView.findViewById(R.id.contanierLl);

        symbol  = (TextView) itemView.findViewById(R.id.symbol);
        name    = (TextView) itemView.findViewById(R.id.name);
        latinName   = (TextView) itemView.findViewById(R.id.latin_name);
        atomicNumber    = (TextView) itemView.findViewById(R.id.atomic_nubmer);
        atomicWeight    = (TextView) itemView.findViewById(R.id.atomic_weight);
        electronConfig  = (TextView)itemView.findViewById(R.id.electron_configuration);
        electronEngergyLvl  =(TextView) itemView.findViewById(R.id.energy_level);

    }


    public void bind(final Element element, final Context context){

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(itemView.getContext(), ElementActivity.class);
                intent.putExtra("element", element);
             /*   ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation();*/
                context.startActivity(intent);

            }
        });

//        Log.d("period ", element.getPeriod().toString());
        //setContainerBackgroundByElementType(element, containerLl);
        ElementColor.setElementBackgroundColor(element.getElectronEnergyType(), containerLl);

        symbol.setText(WordUtils.capitalize(element.getSymbol()));
        name.setText(WordUtils.capitalize(element.getName()));
        latinName.setText(WordUtils.capitalize(element.getLatinName()));
        atomicNumber.setText(Integer.toString(element.getAtomicNumber()));
        atomicWeight.setText(Double.toString(element.getAtomicWeight()));
        electronConfig.setText(Html.fromHtml(ParseString.superscript(element.getElectronConfiguration())));
        electronEngergyLvl.setText(element.getElectronEnergyLevel());

        Animator animator = new Animator(context);
        animator.animLeftToRight(containerLl);
    }

}
