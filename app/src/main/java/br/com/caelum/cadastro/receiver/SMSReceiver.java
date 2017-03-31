package br.com.caelum.cadastro.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android7060 on 31/03/17.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Chegou um sms!!!", Toast.LENGTH_LONG).show();
        Bundle bundle = intent.getExtras();
        Object[] mensagens = (Object[]) bundle.get("pdus");
        byte[] mensagem = (byte[]) mensagens[0];
        String formato = (String) bundle.get("format");
        SmsMessage sms = SmsMessage.createFromPdu(mensagem, formato);
        AlunoDao dao = new AlunoDao(context);

        if (dao.isAluno(sms.getDisplayOriginatingAddress()))
        Toast.makeText(context, "Chegou um sms de " + sms.getDisplayOriginatingAddress(), Toast.LENGTH_LONG).show();
    }
}
