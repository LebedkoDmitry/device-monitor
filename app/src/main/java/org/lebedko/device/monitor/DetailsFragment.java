package org.lebedko.device.monitor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lebedko.device.monitor.dto.Accident;

/**
 * Created by Администратор on 08.05.2017.
 */

public class DetailsFragment extends Fragment {

    private static final String ACCIDENT_KEY = "accident_key";

    private static final String TAG = "DetailsFragment";

    public static DetailsFragment newInstance(Accident accident) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACCIDENT_KEY, accident);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        Accident accident = (Accident) getArguments().getSerializable(ACCIDENT_KEY);
        TextView detailsNameTextView = (TextView) view.findViewById(R.id.detailsName);
        detailsNameTextView.setText(accident.getDeviceName());
        TextView detailsAccidentTypeTextView = (TextView) view.findViewById(R.id.detailsAccidentType);
        detailsAccidentTypeTextView.setText(accident.getAccidentType());
        TextView detailsDescriptionTextView = (TextView) view.findViewById(R.id.detailsDescription);
        detailsDescriptionTextView.setText(accident.getAccident());
        TextView detailsEventTimeTextView = (TextView) view.findViewById(R.id.detailsEventTime);
        detailsEventTimeTextView.setText(accident.getReceiveTime());

        ImageView imageView = (ImageView) view.findViewById(R.id.detailsImageView);
        Context appContext = view.getContext();
        Picasso.with(appContext).load(
                appContext.getResources().getString(R.string.host)
                        + ':'
                        + appContext.getResources().getInteger(R.integer.port)
                        + appContext.getResources().getString(R.string.resources)
                        + accident.getAccidentSeverity()
                        + ".png")
                .into(imageView);
        return view;
    }

}
