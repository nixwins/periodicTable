package kz.nixwins.periodictable.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kz.nixwins.periodictable.R;
import kz.nixwins.periodictable.model.Element;

/**
 * Created by nixwins on 11/23/16.
 */

public class ElementAdapter extends RecyclerView.Adapter<ElementViewHolder>{

    protected List<Element> elements;
    protected List<Element> list;
    protected Context       context;

    public ElementAdapter(List<Element> elements){

        this.elements = elements;
        this.list = elements;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);
        this.context        = recyclerView.getContext();
    }

    @Override
    public ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.element_cardview_layout, parent, false);

        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ElementViewHolder holder, int position) {
        holder.bind(elements.get(position), context);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public void updateList(List<Element> list){
        elements = list;
        notifyDataSetChanged();
    }

}
