package com.at.common.android;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

/**
 * Grls = Global Resource Location Service. 统一资源定位服务. Created by lichsword on
 * 15/6/10.
 */
public class AtuGrls {

    /**
     * activity 跳转
     *
     * @param from
     * @param toClass
     */
    public static void jump(Activity from, Class<?> toClass) {
        try {
            from.startActivity(new Intent(from, toClass));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void jumpForResult(Activity from, Class<?> toClass, int requestCode) {
        try {
            from.startActivityForResult(new Intent(from, toClass), requestCode);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class ATGrlsBuilder {

        private Intent intent;
        private Activity from;
        private Class<?> toClass;

        public ATGrlsBuilder from(Activity from) {
            this.from = from;
            return this;
        }

        public ATGrlsBuilder to(Class<?> toClass) {
            this.toClass = toClass;
            if (null != from && null != toClass) {
                intent = new Intent(from, toClass);
            }
            return this;
        }

        public ATGrlsBuilder extra(String key, int value) {
            if (null != intent) {
                intent.putExtra(key, value);
            }
            return this;
        }

        public ATGrlsBuilder extra(String key, boolean value) {
            if (null != intent) {
                intent.putExtra(key, value);
            }
            return this;
        }

        public ATGrlsBuilder extra(String key, String value) {
            if (null != intent) {
                intent.putExtra(key, value);
            }
            return this;
        }

        public ATGrlsBuilder extra(String key, Serializable object) {
            if (null != intent) {
                intent.putExtra(key, object);
            }
            return this;
        }

        public void jump() {

            if (null == from || null == intent) {
                return;
            }

            try {
                from.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * activity 跳转，并结束当前 activity.
     *
     * @param from
     * @param toClass
     */
    public static void jumpAndFinishSelf(Activity from, Class toClass) {
        try {
            from.startActivity(new Intent(from, toClass));
            from.finish();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启activity
     *
     * @param from
     * @param toClass 推荐主 activity
     */
    public static void jumpAndRestart(Activity from, Class<? extends Activity> toClass) {

        try {
            Intent intent = new Intent(from, toClass);
            PendingIntent restartIntent = PendingIntent.getActivity(from.getApplicationContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
            // 退出程序
            AlarmManager mgr = (AlarmManager) from.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
            from.finish();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 启动至 toClass，且清除中间页<br/>
     * A->B->C->D, D->A, clean: B & C.
     *
     * @param from
     * @param toClass
     */
    public static void jumpAndrClean(Activity from, Class<Activity> toClass) {
        try {
            Intent intent = new Intent(from, toClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            from.startActivity(intent);
            from.finish();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}