package pribadi.tugas.if314053.paladinsheroeswiki.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.API.RESTClientChampions;
import pribadi.tugas.if314053.paladinsheroeswiki.ChampionsDetailActivity;
import pribadi.tugas.if314053.paladinsheroeswiki.R;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Champions;


/**
 * Created by Satya on 15-May-17.
 */

public class ChampionsAdapter extends RealmRecyclerViewAdapter<Champions, ChampionsAdapter.ViewHolder> {
        Context mContext;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.daftar_champions, parent, false);

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Champions obj = getItem(position);
            String nmChampions = obj.getNama();
            String imgChampions = obj.getImage();


            holder.namaChampions.setText(nmChampions);
            Glide.with(mContext).load(RESTClientChampions.URL+"/paladinswiki/img/"+obj.getImage()).into(holder.gambarChampions);

            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try{
                        Intent intent = new Intent(mContext.getApplicationContext(), ChampionsDetailActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("id", String.valueOf(obj.getId()));
                        mContext.startActivity(intent);
                    }catch (Exception e)
                    {
                        Log.d("hasil error",e.getMessage());
                    }



                }
            });
        }


        class ViewHolder extends RecyclerView.ViewHolder{
            TextView namaChampions;
            ImageView gambarChampions;
            public CardView card_view;

            ViewHolder(View view){
                super(view);

                card_view = (CardView) itemView.findViewById(R.id.cardViewChampions);
                namaChampions= (TextView) view.findViewById(R.id.namaChampions);
                gambarChampions = (ImageView) view.findViewById(R.id.champions_image);

            }
        }

        public ChampionsAdapter(RealmResults<Champions> data, Context mContext){

            super(data, true);
            this.mContext = mContext;
            //layoutInflater = LayoutInflater.from(context);
        }
    }
