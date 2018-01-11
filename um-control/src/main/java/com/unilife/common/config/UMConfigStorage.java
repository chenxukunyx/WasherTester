package com.unilife.common.config;

import android.os.Environment;

/**
 * Created by nicholas on 2015/7/27.
 */
public class UMConfigStorage {

    /* module cache data path */
    static String m_module_cache_path = Environment.getExternalStorageDirectory().getPath();

    /**
     * get module cache file folder
     * @return: folder path
     */
    public static String getModuleCacheFolder()
    {
        return m_module_cache_path;
    }

    /**
     * set module cache file folder
     * @param path
     */
    public void setModuleCacheFolder(String path)
    {
        m_module_cache_path = path;
    }

}
