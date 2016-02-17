/******************************************************************************
*
* File name : AtaListView.java
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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AT框架-标题栏注解
 * 
 * @author lichs_000
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AtaExitStrategy {

    /**
     * 返回退出app的策略类逻辑
     * 
     * @return
     */
    public Class<?>logic();

}
