package dz.youcefmegoura.ebatata.ebatata.controllers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dz.youcefmegoura.ebatata.ebatata.R;
import dz.youcefmegoura.ebatata.ebatata.views.MainActivity;

/**
 * Created by Youcef Mégoura on 24/12/2018.
 */

public class DialogActivation extends AppCompatDialogFragment {
    private static final String TAG = "DialogActivation";

    private EditText activationCode_dialog_activation;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_activation, null);

        builder.setView(view)
                .setTitle("Code d'activation")
                .setCancelable(false)
                .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Fermer l'application
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String inputedCode = activationCode_dialog_activation.getText().toString();

                        if (checkValidationCode(inputedCode)) {
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "Votre code n'est pas valide !", Toast.LENGTH_SHORT).show();
                            //Fermer l'application
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            }, 2000);

                        }


                    }
                });

        activationCode_dialog_activation = view.findViewById(R.id.activationCode_dialog_activation);

        return builder.create();
    }

    public boolean checkValidationCode(String inputedCode) {


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = mdformat.format(calendar.getTime());

        //Activation code
        String activationCode = "ebatata" + strDate;

        String hashedActivationCode = HashClass.sha1(activationCode);

        if (!inputedCode.equals(hashedActivationCode)) {
            Log.d(TAG, "checkValidationCode: the code is invalide " + hashedActivationCode);
            return false;
        } else {
            return true;
        }
    }


}
