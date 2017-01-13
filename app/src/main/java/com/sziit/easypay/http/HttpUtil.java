package com.sziit.easypay.http;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jing1578 on 2016/12/8.
 */

public class HttpUtil {


    public static String httpGet(String url) {
        System.err.println("uri>>>>>>>>>>>>>" + url);
        HttpURLConnection conn = null;
        String responseData = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET"); // get或者post必须得全大写
            conn.setConnectTimeout(12000); // 连接的超时时间
            conn.setReadTimeout(6000); // 读数据的超时时间

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                responseData = getStringFromInputStream(is);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect(); // 关闭连接
            }
        }
        return responseData;
    }

    public static String sendHttpRequest(String address) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public static void sendHttpRequest(final String address, final
    HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
//                    httpURLConnection.setRequestMethod("POST");
//                    DataOutputStream dataOutputStream=new DataOutputStream(httpURLConnection.getOutputStream());
//                    dataOutputStream.writeBytes("username=admin&password=123456");
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        // 回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 根据流返回一个字符串信息
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;

        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        is.close();

        String html = baos.toString(); // 把流中的数据转换成字符串, 采用的编码是: utf-8
        baos.close();
        return html;
    }

    private void FileUtils(InputStream is) {//下载文件处理
        final String path = Environment.getExternalStorageDirectory() + "/zhiyapicture";
        File file = new File(path);
        if (file.exists()) {
//            deleteDir(path);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            FileOutputStream stream = new FileOutputStream(path + "/" + System.currentTimeMillis() + ".png");
//            bit.compress(Bitmap.CompressFormat.PNG, 90, stream);
            int temp = 0;
            byte[] buffer = new byte[1024];
            while ((temp = is.read(buffer)) != -1) {
                stream.write(buffer, 0, temp);
            }
            stream.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public interface HttpCallbackListener {
        void onFinish(String response);//子线程,处理ui异步消息处理机制

        void onError(Exception e);//子线程,处理ui异步消息处理机制
    }

}
