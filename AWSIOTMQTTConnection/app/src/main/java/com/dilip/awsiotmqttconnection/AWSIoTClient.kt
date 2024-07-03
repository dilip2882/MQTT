package com.dilip.awsiotmqttconnection

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClient
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos
import java.security.KeyStore

class AWSIoTClient(
    private val clientEndpoint: String,
    private val clientId: String,
    private val keyStorePasswordPair: Pair<KeyStore, String>,
    private val credentialsProvider: AWSCredentialsProvider
) {

    private var mqttClient: AWSIotMqttClient? = null

    fun connect(): AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus? {
        try {
            mqttClient = AWSIotMqttClient(clientEndpoint, clientId, keyStorePasswordPair.first, keyStorePasswordPair.second)
            mqttClient?.connect(credentialsProvider)
            return AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected
        } catch (e: Exception) {
            Log.e("TAG", "Connection error:", e)
            return null
        }
    }

    fun publishString(topic: String, qos: AWSIotMqttQos, payload: String) {
        mqttClient?.publish(topic, qos, payload)
    }

    fun disconnect() {
        mqttClient?.disconnect()
    }
}
