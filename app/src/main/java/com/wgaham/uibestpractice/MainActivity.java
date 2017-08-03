package com.wgaham.uibestpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();//初始化
        inputText = (EditText) findViewById(R.id.inout_text);
        send = (Button) findViewById(R.id.send_button);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if (!content.isEmpty()) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);//当有新消息时，刷新RecyclerView的显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);//定位到RecyclerView的最后一行
                    inputText.setText(null);//清空内容
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg01 = new Msg("Hello Guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg01);
        Msg msg02 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg02);
        Msg msg03 = new Msg("This is Mary. Nice to meet you.", Msg.TYPE_RECEIVED);
        msgList.add(msg03);
    }

}

