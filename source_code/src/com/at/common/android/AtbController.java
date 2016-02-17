package com.at.common.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

/**
 * Created by lichsword on 15/6/10.
 */
public abstract class AtbController implements AtiPageLifecycle, AtiBackPressed {

    private static final String TAG = AtbController.class.getSimpleName();
    protected Activity activity;
    protected View rootView;
    protected AtbController parentController;

    protected ProgressDialog progressDialog;

    public AtbController() {
        this(null, null, null);

        initContentView();
    }

    public AtbController(Activity activity, View rootView, AtbController parentController) {
        this.activity = activity;
        this.rootView = rootView;
        this.parentController = parentController;

        initContentView();
    }

    AtbController backPressedController = null;

    private void initContentView() {

        // init progress dialog, but not show.
        if (null != activity) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("");
            progressDialog.setMessage("Processing...");
            progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                }
            });
        } // end if

        AtaExitStrategy exitStrategy = getClass().getAnnotation(AtaExitStrategy.class);
        if (null == exitStrategy) {
            AtuLog.i(TAG, "[AT]get annotation...null");
            return;
        } // end if

        Class<?> logicClass = exitStrategy.logic();
        if (null == logicClass) {
            AtuLog.i(TAG, "[AT]get logic from annotation...failed");
            return;
        } // end if

        try {
            backPressedController = (AtbController) logicClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (null == backPressedController) {
            AtuLog.i(TAG, "[AT]newInstance ATBaseAdapter from annotation...failed");
            return;
        } // end if

    }

    public void processMessage(int messageId, Object... params) {
        onProcessMessage(messageId, params);
    }

    protected void onProcessMessage(int messageId, Object... params) {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onNewIntent(Intent intent) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onDestroy() {
        if (null != progressDialog)
            progressDialog.dismiss();
        progressDialog = null;
        this.activity = null;
        this.rootView = null;
        this.parentController = null;
    }

    /**
     * 若内部处理的 back key，则上层就不响应 back key.
     * 
     * @return true 若内部处理了back key. false otherwise.
     */
    @Override
    public boolean onBackPressed() {
        boolean handled = false;

        if (null != backPressedController) {
            handled = backPressedController.onBackPressed();
        } else {
            handled = false;
        }

        return handled;
    }
}
