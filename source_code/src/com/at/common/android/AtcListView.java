/******************************************************************************
*
* File name : AtcListView.java
* Create time : 2015年7月7日
* Author : lichs_000
* Description : TODO
* History:
* 1. Date: 2015年7月7日
* Author: lichs_000
* Modification: Create class.
*
*****************************************************************************/
package com.at.common.android;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

/**
 * <p>
 * Example:
 * </p>
 * <code>
 * "@AtaListView(adapter = TestAtDemoAdapter.class, enablePtr = false, viewResId = R.id.atListView)"</br>
 * class FooController extends AtcListView {</br>
 * ...</br>
 * }</br>
 * </code>
 * 
 * @author lichs_000
 */
public class AtcListView extends AtbController {

    private static final String TAG = AtcListView.class.getSimpleName();

    protected ListView listView;
    protected AtbAdapter listAdapter;

    public AtcListView(Activity activity, View view, AtbController parentController) {
        super(activity, view, parentController);
        View contentView = activity.findViewById(android.R.id.content);
        if (null == contentView) {
            AtuLog.i(TAG, "[AT]found root view...failed");
            return;
        } // end if

        AtaListView ataListView = getClass().getAnnotation(AtaListView.class);
        if (null == ataListView) {
            AtuLog.i(TAG, "[AT]getAnnotation...failed");
            return;
        } // end if

        int resId = ataListView.viewResId();
        if (0 == resId) {
            AtuLog.i(TAG, "[AT]get listView resId from annotation...failed");
            return;
        } // end if

        listView = (ListView) contentView.findViewById(resId);

        Class<?> adapterClass = ataListView.adapter();
        if (null == adapterClass) {
            AtuLog.i(TAG, "[AT]get adapter from annotation...failed");
            return;
        } // end if

        try {
            listAdapter = (AtbAdapter) adapterClass.newInstance();
            listView.setAdapter(listAdapter);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (null == listAdapter) {
            AtuLog.i(TAG, "[AT]newInstance ATBaseAdapter from annotation...failed");
            return;
        } // end if

    }

    @Override
    public void onDestroy() {
        listAdapter = null;
        listView = null;
    }
}
