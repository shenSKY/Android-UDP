package com.example.higlobal.udpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private UDPBuild udpBuild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        udpBuild = UDPBuild.getUdpBuild();
        udpBuild.setUdpReceiveCallback(new UDPBuild.OnUDPReceiveCallbackBlock() {
            @Override
            public void OnParserComplete(DatagramPacket data) {
                String strReceive = new String(data.getData(), 0, data.getLength());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                TextView receive = findViewById(R.id.receive_textView);
                receive.append(str + ':' + strReceive + '\n');
            }
        });
    }
    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.send_editText);
        String message = editText.getText().toString();
        udpBuild.sendMessage(message);

        TextView send = findViewById(R.id.send_textView);
        send.append(message + '\n');
    }
}
