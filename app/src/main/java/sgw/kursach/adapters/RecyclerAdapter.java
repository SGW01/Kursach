package sgw.kursach.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sgw.kursach.Candidate;
import sgw.kursach.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Candidate> list = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Candidate> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.item_recycler, parent, false);
        return new RecyclerAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    public void setList(ArrayList<Candidate> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.age)
        TextView age;

        @BindView(R.id.email)
        TextView email;

        @BindView(R.id.range1)
        TextView range1;

        @BindView(R.id.range2)
        TextView range2;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindView(Candidate candidate) {

            name.setText(candidate.getName());
            age.setText(String.valueOf(candidate.getAge()));
            email.setText(candidate.getEmail());
            range1.setText(String.valueOf(candidate.getRange1()));
            range2.setText(String.valueOf(candidate.getRange2()));

        }
    }
}
