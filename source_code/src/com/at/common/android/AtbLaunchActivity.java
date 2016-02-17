/******************************************************************************
*
* File name : AtbLaunchActivity.java
* Create time : 2015年7月27日
* Author : lichs_000
* Description :
* History:
* 1. Date: 2015年7月27日
* Author: lichs_000
* Modification: Create class.
*
*****************************************************************************/
package com.at.common.android;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * @author lichs_000
 *
 */
public abstract class AtbLaunchActivity extends FragmentActivity {

    /**
     * 希望执行重启
     */
    public static final String KEY_HOPE_RESTART = "hope_restart";

    @Override
    protected void onNewIntent(Intent intent) {
        boolean hopeRestart = intent.getBooleanExtra(KEY_HOPE_RESTART, false);
        if (hopeRestart) {
            restartLauncher();
        } else {
            // do nothing.
        }
        super.onNewIntent(intent);
    }

    /**
     * 重启自身 activity
     */
    protected abstract void restartLauncher();
}
