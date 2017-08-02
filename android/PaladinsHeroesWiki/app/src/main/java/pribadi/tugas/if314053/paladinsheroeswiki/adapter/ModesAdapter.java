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
import pribadi.tugas.if314053.paladinsheroeswiki.API.RESTClientModes;
import pribadi.tugas.if314053.paladinsheroeswiki.ModesDetailActivity;
import pribadi.tugas.if314053.paladinsheroeswiki.R;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Modes;

/**
 * Created by Satya on 16-May-17.
 */

public class ModesAdapter extends RealmRecyclerViewAdapter<Modes, ModesAdapter.ViewHolder> {
    Context mContext;

    @Override
    public ModesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daftar_modes, parent, false);

        return new ModesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ModesAdapter.ViewHolder holder, int position) {
        final Modes obj = getItem(position);
        String nmModes = obj.getMode();
        String imgModes = obj.getImage();


        holder.namaModes.setText(nmModes);
        Glide.with(mContext).load(RESTClientModes.URL+"/paladinswiki/img/"+obj.getImage()).into(holder.gambarModes);

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    Intent intent = new Intent(mContext.getApplicationContext(), ModesDetailActivity.class);
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
        TextView namaModes;
        ImageView gambarModes;
        public CardView card_view;

        ViewHolder(View view){
            super(view);

            card_view = (CardView) itemView.findViewById(R.id.cardViewModes);
            namaModes= (TextView) view.findViewById(R.id.namaModes);
            gambarModes = (ImageView) view.findViewById(R.id.modes_image);

        }
    }

    public ModesAdapter(RealmResults<Modes> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
        //layoutInflater = LayoutInflater.from(context);
    }
}