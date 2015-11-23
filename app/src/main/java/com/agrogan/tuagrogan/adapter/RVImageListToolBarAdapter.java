package com.agrogan.tuagrogan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

import com.bumptech.glide.Glide;

import com.agrogan.tuagrogan.Common;
import com.agrogan.tuagrogan.R;
import com.agrogan.tuagrogan.viewmodel.ListViewItem;

import java.util.ArrayList;

/**
 * Created by LogicStudio on 19/10/2015.
 */
public class RVImageListToolBarAdapter extends RecyclerView.Adapter<RVImageListToolBarAdapter.ListHolder> {


    ArrayList<ListViewItem> listItem;
    Boolean _showRatingBar;
    Context _context;
    static ArrayList<ListViewItem> listItemHolder;
    static Context _contextHolder;
    Integer _layout;

    public RVImageListToolBarAdapter(ArrayList<ListViewItem> items, Context context, Boolean showRating){
        this.listItem = items;
        this.listItemHolder = items;
        _showRatingBar = showRating;
        _context = context;
        _contextHolder = context;
    }

    public RVImageListToolBarAdapter(ArrayList<ListViewItem> items, Context context, Boolean showRating,Integer layout){
        this.listItem = items;
        this.listItemHolder = items;
        _showRatingBar = showRating;
        _context = context;
        _contextHolder = context;
        _layout = layout;
    }



    @Override
    public ListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view= null;
        if(_layout == null)
             view = inflater.inflate(R.layout.card_toolbar, viewGroup, false);
        else
            view = inflater.inflate(R.layout.card_toolbar_with_plus, viewGroup, false);

        return new ListHolder(view);
    }

    private static void showSnackBar(View view,String msg) {
        Snackbar
                .make(view.findViewById(R.id.cvList), msg, Snackbar.LENGTH_LONG)
                .show();
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

        if (!_showRatingBar) {
            listHolder.ratingBar.setVisibility(View.GONE);
        } else {
            //Setea el StarRating
            if (listHolder.ratingBar != null) {
                listHolder.ratingBar.setNumStars(item.starCount);
                if (item.ratingValue <= item.starCount) {
                    listHolder.ratingBar.setRating(item.ratingValue);
                }
            }
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

        listHolder.toolBar.setTitle(item.text);


        if(item.textElement != null){
            for (int ele = 0; ele < item.textElement.size(); ele++) {
                if(ele==0){
                    listHolder.txtItem1.setText(item.textElement.get(ele));
                }
                else if (ele==1){
                    listHolder.txtItem2.setText(item.textElement.get(ele));
                }
                else
                    break;
            }
        }
        else {
            listHolder.txtItem1.setVisibility(View.GONE);
            listHolder.txtItem2.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        ImageView img;
        TextView txt;
        TextView txtSubText;
        RatingBar ratingBar;
        TextView txtSubTextInner;
        Toolbar toolBar;

        TextView txtItem1;
        TextView txtItem2;

        ListHolder(final View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cvList);
            img = (ImageView)itemView.findViewById(R.id.img);
            txt = (TextView) itemView.findViewById(R.id.txt);
            txtSubText = (TextView) itemView.findViewById(R.id.txtSubText);
            txtSubTextInner = (TextView) itemView.findViewById(R.id.txtSubTextInner);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            toolBar = (Toolbar) itemView.findViewById(R.id.TbCard);

            txtItem1 = (TextView)itemView.findViewById(R.id.txtItem1);
            txtItem2 = (TextView)itemView.findViewById(R.id.txtItem2);

            txt.setOnClickListener(this);
            txtSubText.setOnClickListener(this);
            if (toolBar != null) {
                //inflate your menu
                toolBar.inflateMenu(R.menu.menu_comercio);
                toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_masinfo:
                            {
                                                         /*PromosActivity.createInstance(
                                        (Activity) _contextHolder, listItemHolder.get(getAdapterPosition()));
*/
                                showSnackBar(itemView,"Pronto m\u00e1s informaci\u00f3n...");
                                return true;
                            }
                        }

                        return false;
                    }
                });
            }
        }

        @Override
        public void onClick(View v) {
            int position = getPosition();
            //final ListViewItem itemData=  listItem.get(position);


        }
    }
}
