/******************************************************************************
*
* File name : AtaListView.java
* Create time : 2015年7月7日
* Author : lichs_000
* Description : 
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
 * AT框架-列表注解
 * 
 * @author lichs_000
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AtaListView {

    /**
     * 绑定视图资源Id
     * 
     * @return
     */
    public int viewResId();

    /**
     * 绑定适配器
     * 
     * @return
     */
    public Class<?>adapter();

    /**
     * 开启 Pull To Refresh.
     * 
     * @return
     */
    public boolean enablePtr();

}
