package com.unilife.variety.model;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.unilife.variety.entity.TesterEntity;
import com.unilife.variety.utils.SharedpreferencesUtil;

/**
 * Created by 万启林 on 2015/7/29.
 */
public class ConfigInfoModel extends BaseModel {
    private final String CACHE_KEY = "ConfigInfoModel";

    public ConfigInfoModel() {
        super();
    }

    public void saveConfig(Context context, TesterEntity config) {
        if(config != null) {
            String json = JSON.toJSONString(config);
            SharedpreferencesUtil.putString(context, null, CACHE_KEY, json);
        }
    }

    public TesterEntity loadConfig(Context context) {
        String json = SharedpreferencesUtil.getString(context, null, CACHE_KEY);
        TesterEntity config = null;
        if(!TextUtils.isEmpty(json)) {
            try{
                config = JSON.parseObject(json, TesterEntity.class);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return config;
    }
}
