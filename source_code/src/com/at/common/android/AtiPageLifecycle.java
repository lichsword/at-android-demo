/******************************************************************************
*
* File name : AtiViewLifecycle.java
* Create time : 2015年8月4日
* Author : lichs_000
* Description :
* History:
* 1. Date: 2015年8月4日
* Author: lichs_000
* Modification: Create class.
*
*****************************************************************************/
package com.at.common.android;

import android.content.Intent;

/**
 * @author lichs_000
 *
 */
public interface AtiPageLifecycle extends AtiLifecycle {

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onNewIntent(Intent intent);

    public void onActivityResult(int requestCode, int resultCode, Intent data);

}
