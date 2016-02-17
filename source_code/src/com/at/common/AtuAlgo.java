/******************************************************************************
*
* File name : AtuAlgo.java
* Create time : 2015年9月2日
* Author : lichs_000
* Description :
* History:
* 1. Date: 2015年9月2日
* Author: lichs_000
* Modification: Create class.
*
*****************************************************************************/
package com.at.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lichs_000
 *
 */
public class AtuAlgo {
    /**
     * String to md5 to String.
     * 
     * @param string
     * @return null if failed.
     */
    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }
}
