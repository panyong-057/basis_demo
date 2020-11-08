package com.example.udp_tcp_serialport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.udp_tcp_serialport.udp.CRC16;
import com.example.udp_tcp_serialport.udp.CRC16Util;
import com.example.udp_tcp_serialport.udp.HexUtil;
import com.example.udp_tcp_serialport.udp.UdpClient;

public class MainActivity extends AppCompatActivity {
    String ip;
    String port;
    byte[] data;
    String str_cmd;
    EditText et_ip, et_port, et_cmd, et_result;
    Button btn_send, btn_conect, btn_disconect;

    Handler handler =new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_ip = findViewById(R.id.et_ip);
        et_port = findViewById(R.id.et_port);
        et_cmd = findViewById(R.id.et_cmd);
        btn_send = findViewById(R.id.btn_send);
        btn_conect = findViewById(R.id.btn_conect);
        btn_disconect = findViewById(R.id.btn_disconect);
        et_result = findViewById(R.id.et_result);


        //默认ip地址
        ip = "192.168.1.191";
        port = "50000";


        ip = et_ip.getText().toString();
        port = et_port.getText().toString();

        str_cmd = "AAA01100";
        str_cmd = et_cmd.getText().toString();
        //发送数据
        data = HexUtil.hex2byte(str_cmd);

        //crc 校验
        CRC16 crc16 =new CRC16();
        crc16.update(data, 0, data.length);
        String crc =  Integer.toHexString((int)crc16.getValue());
       // String crc = Integer.toHexString(CRC16Util.calcCrc16(data, 2, data.length - 2));
        Log.e("chris", "crc :" + crc);
        str_cmd = str_cmd + crc;
        data = HexUtil.hex2byte(str_cmd);
        Log.e("chris", "str :" + str_cmd);

        UdpClient.getInstance().setOnDataReceiveListener(new UdpClient.OnDataReceiveListener() {
            @Override
            public void onConnectSuccess() {
                Log.e("chris", "onDataReceive connect success");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        et_result.setText("onConnectSuccess");
                    }
                });

            }

            @Override
            public void onConnectFail() {
                Log.e("chris", "onDataReceive connect fail");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        et_result.setText("onConnectFail");
                    }
                });

            }

            @Override
            public void onDataReceive(byte[] buffer, int size, final int requestCode) {//获取有效长度的数据
                byte[] data = new byte[size];
                System.arraycopy(buffer, 0, data, 0, size);
                final String oxValue = HexUtil.byte2hex(data);
                Log.e("chris", "onDataReceive requestCode = " + requestCode + ", content = " + oxValue);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        et_result.setText("onDataReceive requestCode = " + requestCode + ", content = " + oxValue);
                    }
                });

            }
        });


        btn_conect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UdpClient.getInstance().connect(ip, Integer.parseInt(port));
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UdpClient.getInstance().sendByteCmd(data, 1001);
            }
        });
        btn_disconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UdpClient.getInstance().disconnect();
            }
        });


    }
}
