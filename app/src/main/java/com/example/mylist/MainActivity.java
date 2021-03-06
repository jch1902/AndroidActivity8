package com.example.mylist;

import android.app.LauncherActivity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView list = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.list = (RecyclerView)findViewById(R.id.am_list);
        TextListAdapter adapter = new TextListAdapter();
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter.setItem(generateItems());
//        adapter.setItem(
//                Arrays.asList(
//                        "Item 1",
//                        "Item 2",
//                        "Item 3"
//
//                )
//        );
    }
    private List<ListItemData> generateItems(){
        List<ListItemData> items = new ArrayList<>();

        for(int i = 0; i<10; i++){
            ListItemData itemData = new ListItemData(
                    "title" + String.valueOf(i),
                    "subtitle" + String.valueOf(i),
                    Color.rgb(
                            new Random().nextInt(255),
                            new Random().nextInt(255),
                            new Random().nextInt(255)
                    ));
            items.add(itemData);
        }

        return items;
    }
    private class ListItemData{
        private String title;
        private String subtitle;
        private int backgroundColor;

        public ListItemData(String title, String subtitle, int backgroundColor){
            this.title = title;
            this.subtitle = subtitle;
            this.backgroundColor = backgroundColor;


        }
        public String getTitle(){
            return title;
        }
        public String getSubtitle(){
            return subtitle;
        }
        public int getbackgroundColor(){
            return backgroundColor;
        }
    }
    private static class TextListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<ListItemData> items = new ArrayList();

        public void setItem(List<ListItemData> items){
            this.items = items;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
            View layoutView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.list_item_text_2,viewGroup, false);
            return new TextViewHolder(layoutView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if(viewHolder instanceof TextViewHolder){
                TextViewHolder textViewHolder = ((TextViewHolder) viewHolder);
                ListItemData item = items.get(position);
                textViewHolder.setText(item.getTitle());
                textViewHolder.setBackgroundColor(item.getbackgroundColor());
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
        public int getItemNewType(int position){
            return 0;
        }
    }
    private static class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.lit_text);
        }
        public void setText(String text){
            textView.setText(text);
        }
        public void setBackgroundColor(int color){
            itemView.setBackgroundColor(color);
        }
    }
}
