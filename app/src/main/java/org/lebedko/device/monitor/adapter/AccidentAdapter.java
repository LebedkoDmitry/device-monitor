package org.lebedko.device.monitor.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import org.lebedko.device.monitor.DetailsFragment;
import org.lebedko.device.monitor.R;
import org.lebedko.device.monitor.dto.Accident;

public class AccidentAdapter extends RecyclerView.Adapter<AccidentAdapter.AccidentViewHolder> {


    private static final String TAG = "AccidentAdapter";

    private List<Accident> accidentList = new ArrayList<>();

    private final static int ACCIDENT_OK = 1;
    private final static int ACCIDENT_ERROR = 2;

    public class AccidentViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public TextView name, context, accidentType, accident;
        public ImageView imageView;

        public AccidentViewHolder(View view) {
            super(view);
            this.view = view;
            name = (TextView) view.findViewById(R.id.deviceName);
            context = (TextView) view.findViewById(R.id.receiveTime);
            accidentType = (TextView) view.findViewById(R.id.accidentType);
            accident = (TextView) view.findViewById(R.id.accident);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }

    public class AdvertiseViewHolder extends AccidentViewHolder {
        public AdvertiseViewHolder(View view) {
            super(view);
        }
    }

    public void setData(List<Accident> accidentList) {
        this.accidentList = accidentList;
    }

    @Override
    public AccidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accident_item, parent, false);
        if (viewType == ACCIDENT_ERROR) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.accident_error_item, parent, false);
        }
        return new AccidentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccidentViewHolder holder, int position) {
        final Accident accident = accidentList.get(position);
        holder.name.setText(accident.getDeviceName());
        holder.context.setText(accident.getReceiveTime());
        holder.accidentType.setText(accident.getAccidentType());
        holder.accident.setText(accident.getAccident());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick");
                Log.i(TAG, accident.toString());

                DetailsFragment detailsFragment = DetailsFragment.newInstance(accident);
                FragmentManager fragmentManager = ((AppCompatActivity)v.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main, detailsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });
        Context appContext = holder.view.getContext();
        Picasso.with(appContext).load(
                appContext.getResources().getString(R.string.host)
                        + ':'
                        + appContext.getResources().getInteger(R.integer.port)
                        + appContext.getResources().getString(R.string.resources)
                        + accident.getAccidentSeverity()
                        + ".png")
                .into(holder.imageView);
    }

    @Override
    public int getItemViewType(int position) {
        if (accidentList.get(position).getAccidentSeverity() == 2) {
            return ACCIDENT_ERROR;
        }
        return ACCIDENT_OK;
    }

    @Override
    public int getItemCount() {
        return accidentList.size();
    }
}
