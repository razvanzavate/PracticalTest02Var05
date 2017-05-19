package ro.pub.cs.systems.eim.practicaltest02var05;

/**
 * Created by student on 19.05.2017.
 */

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private String address;
    private int port;
    private String params;
    private String type;
    private TextView resultTextView;

    private Socket socket;

    public ClientThread(int port, String params, String type, TextView resultTextView) {
        this.address = "127.0.0.1";
        this.port = port;
        this.params = params;
        this.type = type;
        this.resultTextView = resultTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                Log.e("[PracticalTest02]", "[CLIENT THREAD] Could not create socket!");
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e("[PracticalTest02]", "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(params);
            printWriter.flush();
            printWriter.println(type);
            printWriter.flush();
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                final String resultInformation = info;
                resultTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        resultTextView.setText(resultInformation);
                    }
                });
            }
        } catch (IOException ioException) {
            Log.e("[PracticalTest02]", "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (true) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e("[PracticalTest02]", "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (true) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

}