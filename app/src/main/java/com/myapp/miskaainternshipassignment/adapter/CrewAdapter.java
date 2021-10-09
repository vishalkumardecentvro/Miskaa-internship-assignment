package com.myapp.miskaainternshipassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.miskaainternshipassignment.Crew;
import com.myapp.miskaainternshipassignment.R;
import com.myapp.miskaainternshipassignment.databinding.RvCrewBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {
  private List<Crew> crewList = new ArrayList<>();
  private Context context;

  public CrewAdapter(Context context) {
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(RvCrewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.populate(position);

  }

  @Override
  public int getItemCount() {
    return crewList.size();
  }

  public void setCrewList(List<Crew> crewList) {
    this.crewList = crewList;
    notifyDataSetChanged();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private RvCrewBinding binding;

    public ViewHolder(@NonNull RvCrewBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void populate(int position) {
      if (crewList.get(position).getStatus().equals("active"))
        binding.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
      else
        binding.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.red));

      binding.tvStatus.setText(crewList.get(position).getStatus());
      binding.tvName.setText(crewList.get(position).getName());
      binding.tvAgency.setText(crewList.get(position).getAgency());
      binding.tvHyperLink.setText(crewList.get(position).getWikipedia());
      Picasso.get().load(crewList.get(position).getImage()).into(binding.ivCrewImage);
    }
  }
}
