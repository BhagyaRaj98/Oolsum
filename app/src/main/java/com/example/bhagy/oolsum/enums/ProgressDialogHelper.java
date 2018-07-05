package com.example.bhagy.oolsum.enums;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by sabir on 17/4/18.
 */

public enum ProgressDialogHelper {
    INSTANCE;

    public ProgressDialog getIndeterminateDialog(Context ctx, String message) {
        ProgressDialog progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        return progressDialog;
    }

    public void show(ProgressDialog dialog) {
        if (dialog != null)
            dialog.show();
    }

    public void dismiss(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
