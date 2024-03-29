package it.ticandtac.primecalculator.DB;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.ticandtac.primecalculator.OtherFunction.FunctionEnum;
import it.ticandtac.primecalculator.R;

public class CronoAdapter extends RecyclerView.Adapter<CronoAdapter.CronoViewHolder> {
    private Cursor cursor;
    private Context context;

    public static class CronoViewHolder extends RecyclerView.ViewHolder{

        private TextView funct_;
        private TextView resul_;


        public CronoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.funct_ = itemView.findViewById(R.id.func_);
            this.resul_ = itemView.findViewById(R.id.res_);
        }

        public TextView getFunct_() {
            return funct_;
        }

        public TextView getResul_() {
            return resul_;
        }
    }

    public CronoAdapter(Cursor cursor, Context context){
        this.cursor = cursor;
        this.context = context;
    }

    @NonNull
    @Override
    public CronoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View res_cont = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_content,viewGroup,false);
        return new CronoViewHolder(res_cont);
    }

    @Override
    public void onBindViewHolder(@NonNull CronoViewHolder cronoViewHolder, int i) {
        this.cursor.moveToPosition(i);
        try{
            cronoViewHolder.getFunct_().setText("N = " + cursor.getString(1));
            if (FunctionEnum.valueOf(cursor.getString(3))== FunctionEnum.ISPRIME){
                cronoViewHolder.getResul_().setText(cursor.getString(2));
            } else{
                cronoViewHolder.getResul_().setText(this.context.getString(FunctionEnum.valueOf(cursor.getString(3)).getFunctionID()) + cursor.getString(2));
            }

        }catch (Exception exc){
            System.out.println(exc);

        }
    }

    @Override
    public int getItemCount() {
        if(cursor==null)
            return 0;
        return cursor.getCount();

    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

}
