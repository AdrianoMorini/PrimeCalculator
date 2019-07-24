package it.ticandtac.primecalculator;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thekhaeng.pushdownanim.PushDownAnim;


import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class Fragment_crono extends Fragment {

    DataBaseHelper myDb;


    public static Context getCronoCntxt() {
        return cronoCntxt;
    }

    private static Context cronoCntxt;

    private Button clcCrono;


    private RecyclerView resVDB;
    private CronoAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crono, container, false);

        cronoCntxt = view.getContext();

        this.resVDB = view.findViewById(R.id.listCrono);
        this.clcCrono = view.findViewById(R.id.cleanCrono);
        myDb = new DataBaseHelper(MainActivity.getMainCntxt());


        layoutManager = new LinearLayoutManager(MainActivity.getMainCntxt());
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);

        resVDB.setLayoutManager(layoutManager);
        resVDB.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new CronoAdapter(myDb.getAllData());
        resVDB.setAdapter(mAdapter);





        PushDownAnim.setPushDownAnimTo(clcCrono).setScale( MODE_SCALE, 0.95f  ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Fragment_calc.isOnOff()){
                    Fragment_calc.getBeepSound().start();
                }
                myDb.delete();
                mAdapter.setCursor(myDb.getAllData());
                mAdapter.notifyDataSetChanged();



            }
        });




        return view;
    }


}
