/******************************************************************************
*
* File name : AtcThread.java
* Create time : 2015年7月15日
* Author : lichs_000
* Description : TODO
* History:
* 1. Date: 2015年7月15日
* Author: lichs_000
* Modification: Create class.
*
*****************************************************************************/
package com.at.common.android;

import android.os.AsyncTask;

/**
 * @author lichs_000
 *
 */
public class AtcThread {

    private String tag;

    /**
     * 
     * @param tag
     *            日志标签
     * @param preRunnable
     *            (UI线程执行) = onPreExecute
     * @param backgroundRunnable
     *            (后台线程执行) = doInBackground
     * @param postRunnable
     *            (UI线程执行) = onPostExecute
     * @return
     */
    public boolean execute(String tag, Runnable preRunnable, Runnable backgroundRunnable, Runnable postRunnable) {
        this.tag = tag;
        boolean executed = false;
        if (null == innerTask || !innerTask.isRunning()) {
            innerTask = new InnerTask(preRunnable, backgroundRunnable, postRunnable);
            innerTask.execute();
            executed = true;
            AtuLog.i(tag, "[INFO]AtcThread...start");
        } else {
            executed = false;
            AtuLog.i(tag, "[INFO]AtcThread is running, wait to finish to call again.");
        }
        return executed;
    }

    public void onDestroy() {
        tag = null;
    }

    private InnerTask innerTask;

    class InnerTask extends AsyncTask<Void, Void, Void> {

        private boolean isRunning = false;

        private final Runnable preRunnable;
        private final Runnable backgroundRunnable;
        private final Runnable postRunnable;

        public InnerTask(Runnable preRunnable, Runnable backgroundRunnable, Runnable postRunnable) {
            this.preRunnable = preRunnable;
            this.backgroundRunnable = backgroundRunnable;
            this.postRunnable = postRunnable;
        }

        public boolean isRunning() {
            return isRunning;
        }

        @Override
        protected void onPreExecute() {
            isRunning = true;
            if (null != preRunnable) {
                preRunnable.run();
            } // end if
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (null != backgroundRunnable) {
                backgroundRunnable.run();
            } // end if
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (null != postRunnable) {
                postRunnable.run();
            } // end if
            super.onPostExecute(result);
            isRunning = false;
        }

    }
}
