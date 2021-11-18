package com.davronxolboyev.app.shifrlashlar.model;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davronxolboyev.app.shifrlashlar.R;
import com.davronxolboyev.app.shifrlashlar.SecondActivity;
import com.davronxolboyev.app.shifrlashlar.ThirdActivity;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MethodsAdapter extends RecyclerView.Adapter<MethodsAdapter.MethodsViewHolder> {


    Context context;
    List<Methods> methodsList;
    int lposition = -1;
    public MethodsAdapter(Context context, List<Methods> methodsList) {
        this.context = context;
        this.methodsList = methodsList;
    }

    @NonNull
    @Override
    public MethodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.text_item, parent, false);

        return new MethodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MethodsAdapter.MethodsViewHolder holder, int position) {

        holder.button.setText(methodsList.get(position).getName());

        setAnimations(holder.itemView, position);

        holder.button.setOnClickListener(v -> {
            Log.d("CLICK", "bosildi!");
            Intent intent = new Intent(context, ThirdActivity.class);
            intent.putExtra("ID", methodsList.get(position).getId());
            intent.putExtra("Usul", methodsList.get(position).getName());
            context.startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation((SecondActivity)context).toBundle());

        });
    }

    private void setAnimations(View itemView, int position) {

        if (lposition < position) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lposition = position;
        }

    }

    @Override
    public int getItemCount() {
        return methodsList.size();
    }

    public static class MethodsViewHolder extends RecyclerView.ViewHolder {

        MaterialButton button;

        public MethodsViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.textButton);
        }
    }
}
