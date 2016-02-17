/******************************************************************************
*
* File name : AtcSimpleHttp.java
* Create time : 2015年8月18日
* Author : lichs_000
* Description :
* History:
* 1. Date: 2015年8月18日
* Author: lichs_000
* Modification: Create class.
*
*****************************************************************************/
package com.at.common.http;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.at.common.android.AtuLog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

/**
 * @author lichs_000
 *
 */
public class AtcSimpleHttpClient {

	private static final String TAG = AtcSimpleHttpClient.class.getSimpleName();

	/**
	 * Download specified url directly and save it to local path.
	 * 
	 * @param url
	 *            the url of the file.
	 * @param saveToFile
	 *            the file to save to.
	 * @return true if success, false otherise.
	 */
	public static Bitmap downloadImage(final String url, final File saveToFile) {
		Bitmap bitmap = null;
		if (TextUtils.isEmpty(url)) {
			return null;
		}
		if (null == saveToFile) {
			throw new IllegalArgumentException("saveToFile may not be null.");
		}
		if (saveToFile.exists()) {
			saveToFile.delete();
		}

		try {
			ATHttpClient httpClient = new ATHttpClient();
			ATHttpResponse response = httpClient.get(url);
			int statusCode = response.getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				InputStream is = response.asStream();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buffer = new byte[0xFFFF];
				for (int len; (len = is.read(buffer)) != -1;) {
					os.write(buffer, 0, len);
				}
				os.flush();
				byte[] bytes = os.toByteArray();

				try {
					FileOutputStream fos = new FileOutputStream(saveToFile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					bos.write(bytes, 0, bytes.length);
					bos.close();
					fos.close();
				} catch (Exception e) {
					AtuLog.i(TAG, e.getMessage());
				}

				bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;

	}

}
