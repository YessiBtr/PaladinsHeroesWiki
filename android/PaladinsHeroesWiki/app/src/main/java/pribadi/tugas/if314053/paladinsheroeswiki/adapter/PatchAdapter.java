package pribadi.tugas.if314053.paladinsheroeswiki.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import pribadi.tugas.if314053.paladinsheroeswiki.PatchDetailActivity;
import pribadi.tugas.if314053.paladinsheroeswiki.R;
import pribadi.tugas.if314053.paladinsheroeswiki.model.Patch;

/**
 * Created by Satya on 17-May-17.
 */

public class PatchAdapter extends RealmRecyclerViewAdapter<Patch, PatchAdapter.ViewHolder> {
    Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daftar_patch, parent, false);

        return new PatchAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Patch obj = getItem(position);
        String nmJudul = obj.getJudul();

        holder.namaJudul.setText(nmJudul);

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    Intent intent = new Intent(mContext.getApplicationContext(), PatchDetailActivity.class);
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
        TextView namaJudul;
        public CardView card_view;

        ViewHolder(View view){
            super(view);

            card_view = (CardView) itemView.findViewById(R.id.cardViewPatch);
            namaJudul = (TextView) view.findViewById(R.id.namaJudul);

        }
    }

    public PatchAdapter(RealmResults<Patch> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
        //layoutInflater = LayoutInflater.from(context);
    }
}

