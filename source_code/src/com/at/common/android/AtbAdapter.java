/******************************************************************************
*
* File name : ATBaseAdapter.java
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

import java.io.Serializable;
import java.util.List;

import android.widget.BaseAdapter;

/**
 * @author lichs_000
 *
 */
public abstract class AtbAdapter extends BaseAdapter {

    private List<ATDataType> dataList;

    public AtbAdapter() {
        super();
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public final int getCount() {
        if (null != dataList) {
            return dataList.size();
        } else {
            return 0;
        }
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public final ATDataType getItem(int position) {
        if (null != dataList) {
            return dataList.get(position);
        } else {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public final long getItemId(int position) {
        return position;
    }

    public final List<ATDataType> getDataList() {
        return dataList;
    }

    public final void setDataList(List<ATDataType> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public static class ATDataType implements Serializable {

        private static final long serialVersionUID = 1L;

        public int type;// 多种视觉类型（如：分组列表；一二三列单元格；图文混排等）
        public Object data;

        public ATDataType() {
        }

        public ATDataType(int type, Object data) {
            this.type = type;
            this.data = data;
        }
    }

}
