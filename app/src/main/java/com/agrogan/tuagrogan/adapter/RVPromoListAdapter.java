package com.agrogan.tuagrogan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.agrogan.tuagrogan.Common;
import com.agrogan.tuagrogan.R;
import com.agrogan.tuagrogan.viewmodel.ListViewItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LogicStudio on 22/10/2015.
 */
public class RVPromoListAdapter extends RecyclerView.Adapter<RVPromoListAdapter.ListHolder> {


    ArrayList<ListViewItem> listItem;
    Context _context;
    static Context _contextHolder;

    public RVPromoListAdapter(ArrayList<ListViewItem> items, Context context){
        this.listItem = items;
        _context = context;
    }


    @Override
    public ListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final View view = inflater.inflate(R.layout.promos_activas, viewGroup, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListHolder listHolder, int i) {

        ListViewItem item = listItem.get(i);
        AssetManager assets = _context.getAssets();
        if (item.image != 0) {
            listHolder.img.setImageResource(item.image);
        } else if (item.imgSrc != null) {
            String src;

            if (item.imgSrc.contains("~")) {
                src = item.imgSrc.replace("~/", Common.RootWebSiteUrl);
            } else {
                src = item.imgSrc;
            }

            Glide.with(_context).load(src).into(listHolder.img);
        }
        if(item.imgSrc == null){
            if(item.image == 0)
                listHolder.img.setVisibility(View.GONE);
        }

        //Setea los textos y la fuente.
        listHolder.txt.setText(item.text);
        Common.setFontOnView(assets, listHolder.txt, "fonts/Arial/arial.ttf");

        if (!item.subText.isEmpty()) {
            listHolder.txtSubText.setText(item.subText);
            Common.setFontOnView(assets, listHolder.txtSubText, "fonts/Arial/arial.ttf");
        }

        if (item.subTextInner != null) {
            listHolder.txtSubTextInner.setText(item.subTextInner);
            Common.setFontOnView(assets, listHolder.txtSubTextInner, "fonts/Arial/arial.ttf");
        }

        //Le cambia el tama�o a la imagen, en el caso de que haya sido especificado.
        if (item.imageSize != 0) {
            int px = Common.dpToPx(item.imageSize, _context);
            listHolder.img.getLayoutParams().height = px;
            listHolder.img.getLayoutParams().width = px;
        }


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class ListHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        CardView cv;
        ImageView img;
        TextView txt;
        TextView txtSubText;
        TextView txtSubTextInner;

        ListHolder(final View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cvList);
            img = (ImageView)itemView.findViewById(R.id.img);
            txt = (TextView) itemView.findViewById(R.id.txt);
            txtSubText = (TextView) itemView.findViewById(R.id.txtSubText);
            txtSubTextInner = (TextView) itemView.findViewById(R.id.txtSubTextInner);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

        }
    }

}
