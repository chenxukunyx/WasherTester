package com.miracle.um_base_common.model;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.miracle.um_base_common.entity.TesterEntity;
import com.miracle.um_base_common.util.SharedpreferencesUtil;
import com.unilife.common.UmInit;

/**
 * Created by 万启林 on 2015/7/29.
 */
public class ConfigInfoModel extends BaseModel {
    private final String CACHE_KEY = "ConfigInfoModel";

    public ConfigInfoModel() {
        super();
    }

    public void saveConfig(TesterEntity config) {
        if(config != null) {
            String json = JSON.toJSONString(config);
            SharedpreferencesUtil.putString(UmInit.getInstance().getContext(), null, CACHE_KEY, json);
        }
    }

    public TesterEntity loadConfig() {
        String json = SharedpreferencesUtil.getString(UmInit.getInstance().getContext(), null, CACHE_KEY);
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
