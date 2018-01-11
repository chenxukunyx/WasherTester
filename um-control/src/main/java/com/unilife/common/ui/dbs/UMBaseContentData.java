package com.unilife.common.ui.dbs;

import java.io.Serializable;

/**
 * Created by Kevin on 2015/7/21.
 */
public class UMBaseContentData implements Serializable {

    public String getPrimeKey(){
        return "";
    }

    public String getPrimeValue(){
        return "";
    }

    public Object getProperty(String name)
    {
        return null;
    }

    public void setProperty(String name, Object val)
    {
        ;
    }

    /**
     * compare content by primary key
     * @param other
     * @return: >0: this>other, =0: this==other, <0: this<other
     */
    public int compare(UMBaseContentData other)
    {
        return 0;
    }

    /**
     * compare content by special key
     * @param other
     * @return: >0: this>other, =0: this==other, <0: this<other
     */
    public int compare(UMBaseContentData other, String key)
    {
        return 0;
    }
}
