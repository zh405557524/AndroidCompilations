package com.soul.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
/**
 * 短信监听
 */
public class SmsReceiver extends BroadcastReceiver {
    public SmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("收到短信到来的广播了");
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            Object pdu = pdus[i];
            SmsMessage fromPdu = SmsMessage.createFromPdu((byte[]) pdu);
            String messageBody = fromPdu.getMessageBody();
            String originatingAddress = fromPdu.getOriginatingAddress();
            System.out.println("originatingAddress" + originatingAddress);
            System.out.println("messageBody" + messageBody);
        }

    }
}
