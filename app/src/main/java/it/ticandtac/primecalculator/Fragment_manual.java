package it.ticandtac.primecalculator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_manual extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manual, container, false);

        TextView titleMan = view.findViewById(R.id.titleMan);

        Typeface custom_font = Typeface.createFromAsset(MainActivity.getMainCntxt().getAssets(),  "fonts/Calculator.ttf");
        titleMan.setTypeface(custom_font);






        return view;
    }
}
