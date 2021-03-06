package org.androidtown.seobang_term_project.ui.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidtown.seobang_term_project.R;
import org.androidtown.seobang_term_project.ui.recipe.RecipePreviewActivity;

import java.util.ArrayList;

/**
 * @When:
 * This adapter called when user views or modifies the listView
 *
 * @Function:
 * store historyList into ArrayList and call it, so user can views each item
 *
 * @Technique:
 * override BaseAdapter
 */

public class HistoryAdapter extends BaseAdapter {
    private ArrayList<HistoryList> history = new ArrayList<HistoryList>();
    private Context context;

    boolean flag=false;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return history.size();
    }

    // ** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //postion = ListView의 위치      /   첫번째면 position = 0
        final int pos = position;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_history, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.historyWebView);
        final TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView Context = (TextView) convertView.findViewById(R.id.context);

        final HistoryList listViewItem = history.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        Glide.with(parent).load(listViewItem.getImg()).thumbnail(0.5f).into(image);
//        image.loadUrl(listViewItem.getImg());
        title.setText(listViewItem.getTitle());
        Context.setText(listViewItem.getContext());


        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipePreviewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("RecipeName", title.getText().toString());
                bundle.putString("selectedRecipe", listViewItem.getCode());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, HistoryEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("RecipeName", title.getText().toString());
                intent.putExtras(bundle);
                context.startActivity(intent);
                return true;
            }
        });

        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return history.get(position);
    }

    // 데이터값 넣어줌
    public void addHistory(String URL, String title, String desc, String code) {
        HistoryList item = new HistoryList();

        item.setImg(URL);
        item.setTitle(title);
        item.setContext(desc);
        item.setCode(code);
        history.add(item);
    }
}
