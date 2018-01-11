package com.unilife.common.ui.dbs;

/**
 * Created by nicholas on 2015/7/27.
 */
public class UMFilterContent {

    String m_key;
    String m_value;
    String m_condition;

    UMFilterContent(String key, String value, String condition)
    {
        m_key = key;
        m_value = value;
        m_condition = condition;
    }
}
