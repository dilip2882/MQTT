package com.dilip.awsiotmqttconnection

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos

class MainActivity : ComponentActivity() {

    private lateinit var awsIotClient: AWSIoTClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ... (your Jetpack Compose UI content here)

        val clientEndpoint = "<prefix>-ats.iot.us-west-2.amazonaws.com"
        val clientId = "sdk-java-23"
        val certificateFile = "athing.cert.pem"
        val privateKeyFile = "athing.private.key"

        // Optional: Implement custom logic for credentials provider
        val credentialsProvider = // Your credentials provider

        // Load keystore and password
        val keyStorePasswordPair = KeyStoreUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile)

        awsIotClient = AWSIoTClient(clientEndpoint, clientId, keyStorePasswordPair, credentialsProvider)

        // Connect to AWS IoT
        val connectionStatus = awsIotClient.connect()
        if (connectionStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected) {
            Log.d("TAG", "Connected to AWS IoT")
            val topic = "sdk/test/java"
            val payload = ""// Your payload data (e.g., JSON string)
                awsIotClient.publishString(topic, AWSIotMqttQos.QOS0, payload)
            Log.d("TAG", "Message sent")
            // ... (other functionality in your activity)
        } else {
            Log.e("TAG", "Connection error")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        awsIotClient.disconnect()
    }
}
