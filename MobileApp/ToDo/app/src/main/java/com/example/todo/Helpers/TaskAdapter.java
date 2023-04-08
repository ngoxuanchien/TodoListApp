package com.example.todo.Helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.*;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todo.DeleteTaskActivity;
import com.example.todo.EditTaskActivity;
import com.example.todo.GetTaskActivity;
import com.example.todo.R;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHoler> {

    Context context;
    private ArrayList<Task> tasks;

    @NonNull
    @NotNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHoler holder, int position) {
        Task task = tasks.get(position);
        holder.title.setText(task.getTitle());

        String description = task.getDescription();
        if (description.length() > 45) {
            description = description.substring(0, 45) + "...";
        }
        holder.description.setText(description);

        String formatedTime = DateFormat.getDateTimeInstance().format(task.getTimeCreated());
        holder.timeCreated.setText(formatedTime);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GetTaskActivity.class);
            intent.putExtra("id", task.getId());
            intent.putExtra("title", task.getTitle());
            intent.putExtra("description", task.getDescription());
            intent.putExtra("timeCreated", task.getTimeCreated());
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(view -> {
            PopupMenu menu = new PopupMenu(context, view);
            menu.getMenu().add("DELETE");
            menu.getMenu().add("EDIT");

            menu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getTitle().equals("DELETE")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Delete task");
                    builder.setMessage("Are you sure to delete this task?");
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(context, DeleteTaskActivity.class);
                            intent.putExtra("id", task.getId());
                            context.startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else if (menuItem.getTitle().equals("EDIT")) {
                    Intent intent = new Intent(context, EditTaskActivity.class);
                    intent.putExtra("id", task.getId());
                    intent.putExtra("title", task.getTitle());
                    intent.putExtra("description", task.getDescription());
                    intent.putExtra("timeCreated", task.getTimeCreated());
                    context.startActivity(intent);
                }

                return true;
            });
            menu.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class MyViewHoler extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView timeCreated;

        public MyViewHoler(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            timeCreated = itemView.findViewById(R.id.timecreated);
        }
    }

}
