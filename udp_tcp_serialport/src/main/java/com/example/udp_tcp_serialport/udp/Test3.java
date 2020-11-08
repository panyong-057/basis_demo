package com.example.udp_tcp_serialport.udp;

public class Test3 {
    static byte[] data;
    public static void main(String[] args) {

        //默认ip地址
        String ip = "192.168.1.191";
        String port = "50000";


       // String str ="AAA0110000";//获取棉花糖机传感器的数值信息
       // String str ="AAA01E0000";//柜内允许做糖温度误差读取
       // String str ="AAA01F0000";//柜内允许做糖湿度误差读取
        // String str ="AAA03C000105";//柜内允许做糖炉头温度误差设置
        // String str ="AAA03D000105";//柜内允许做糖湿度误差设置
        //String str ="AAA03200022200"; //炉头工作温度设置
        //String str ="AAA03F00022200"; //做糖室内湿度设置

        //调试页 ===
        //参看物料
        //报警记录  30s取一条
        //参数设置  一条指令获取所有


         String str = "AAA050000101"; //棉花糖花型下单



        //发送数据
       data = HexUtil.hex2byte(str);

        //crc 校验
        CRC16 crc16 =new CRC16();
        crc16.update(data, 0, data.length);
        String crc =  Integer.toHexString((int)crc16.getValue());
        System.out.println("crc :" + crc);
        str = str + crc;
        //aaA01100001d6a
        data = HexUtil.hex2byte(str);
        System.out.println("str :" + str);


        UdpClient.getInstance().setOnDataReceiveListener(new UdpClient.OnDataReceiveListener() {
            @Override
            public void onConnectSuccess() {
                System.out.println("onDataReceive connect success");
                //发送指令
                UdpClient.getInstance().sendByteCmd(data, 1001);
            }

            @Override
            public void onConnectFail() {
                System.out.println("onDataReceive connect fail");
            }

            @Override
            public void onDataReceive(byte[] buffer, int size, int requestCode) {//获取有效长度的数据
                byte[] data = new byte[size];
                System.arraycopy(buffer, 0, data, 0, size);
                final String oxValue = HexUtil.byte2hex(data);
                System.out.println("onDataReceive requestCode = " + requestCode + ", content = " + oxValue);
            }
        });

        //连接
        UdpClient.getInstance().connect(ip, Integer.parseInt(port));

        //断开
        //UdpClient.getInstance().disconnect();
    }


}
