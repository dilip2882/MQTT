package com.dilip.awsiotmqtt;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String clientEndpoint = "<prefix>-ats.iot.<region>.amazonaws.com";   // use value returned by describe-endpoint --endpoint-type "iot:Data-ATS"
        String clientId = "SoilMoistureSensor";                              // replace with your own client ID. Use unique client IDs for concurrent connections.
        String certificateFile = "<certificate file>";                       // X.509 based certificate file
        String privateKeyFile = "<private key file>";                        // PKCS#1 or PKCS#8 PEM encoded private key file

// SampleUtil.java and its dependency PrivateKeyReader.java can be copied from the sample source code.
// Alternatively, you could load key store directly from a file - see the example included in this README.
        SampleUtil.KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile);
        AWSIotMqttClient client = new AWSIotMqttClient(clientEndpoint, clientId, pair.keyStore, pair.keyPassword);

// optional parameters can be set before connect()
        try {
            client.connect();
        } catch (AWSIotException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(this, "Connected!", Toast.LENGTH_SHORT).show();
        Log.d("MQTT", "\"Connected!");
    }
}