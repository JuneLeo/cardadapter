package com.cardadapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cardlibrary.adapter.CardAdapter;
import com.cardlibrary.base.BaseCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rv_card)
    RecyclerView mRvCard;

    CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRvCard.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CardAdapter();
        adapter.setOnItemClick(new CardAdapter.OnItemClick() {
            @Override
            public void OnClick(BaseCard baseCard, int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnLongClick(BaseCard baseCard, int position) {

            }
        });
        mRvCard.setAdapter(adapter);
        initData();
    }

    private void initData() {
        List<BaseCard> card = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            HumorCard user = new HumorCard("糗事" + i);
            card.add(user);
        }

        for (int i = 0; i < 7; i++) {
            UserCard user = new UserCard("大白" + i);
            card.add(user);
        }

        adapter.addAll(card,true);
        adapter.notifyDataSetChanged();
    }
}
