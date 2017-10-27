package com.eclev.lawrence.easydone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button save;
    TextView todaysDate;
    RecyclerView todoList;
    EditText description, duration;
//    todoAdapter mTodoAdapter;
//    List<todo> mTodoList;
    DatabaseReference mDatabaseReference;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = (Button) findViewById(R.id.btn_save_todo);
        todoList = (RecyclerView) findViewById(R.id.rv_todo_list);
        description = (EditText) findViewById(R.id.et_todo_description);
        duration = (EditText) findViewById(R.id.et_todo_duration);
//        mTodoList = new ArrayList<>();
        todaysDate = (TextView) findViewById(R.id.tv_days_date);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Todo");

        //new DatabaseReference for the pulling of todoitems
        mReference = FirebaseDatabase.getInstance().getReference().child("Todo");


        LinearLayoutManager manager = new LinearLayoutManager(this);
        todoList.setLayoutManager(manager);
        todoList.setHasFixedSize(true);
//        mTodoAdapter = new todoAdapter(this,mTodoList);
//        todoList.setAdapter(mTodoAdapter);

        Calendar calender = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE, MMM d, ''yy");
        final String postDate = simpledateformat.format(calender.getTime());
        todaysDate.setText(postDate);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoTitle = description.getText().toString();
                String todoDuration = duration.getText().toString();

                if(!TextUtils.isEmpty(todoTitle) && !TextUtils.isEmpty(todoDuration)){
//                    todo mytodo = new todo(todoTitle,todoDuration);

                    DatabaseReference newTodo = mDatabaseReference.push();
                    newTodo.child("desc").setValue(todoTitle);
                    newTodo.child("duration").setValue(todoDuration);

//                    mTodoList.add(mytodo);
//                    mTodoAdapter.notifyDataSetChanged();
                    description.setText("");
                    duration.setText("");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<todo,todoViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<todo, todoViewHolder>(
                        todo.class,
                        R.layout.todo_list_items,
                        todoViewHolder.class,
                        mReference
                ) {
            @Override
            protected void populateViewHolder(todoViewHolder viewHolder, todo model, int position) {
                viewHolder.desc.setText(model.getDesc());
                viewHolder.dur.setText(model.getDuration());
            }
        };
        todoList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class todoViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView desc, dur;

        public todoViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            desc = itemView.findViewById(R.id.tv_title_item);
            dur = itemView.findViewById(R.id.tv_duration_item);
        }

        public void setDescription(){

        }

    }


}
