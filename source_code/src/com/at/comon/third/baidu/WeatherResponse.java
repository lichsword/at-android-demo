/******************************************************************************
*
* File name : WeatherResponse.java
* Create time : 2015年9月16日
* Author : lichs_000
* Description :
* History:
* 1. Date: 2015年9月16日
* Author: lichs_000
* Modification: Create class.
*
*****************************************************************************/
package com.at.comon.third.baidu;

import java.util.List;

/**
 * @author lichs_000
 *
 */
public class WeatherResponse {
    public int error;
    public String status;
    public String date;

    public List<WeatherResult> results;

}
