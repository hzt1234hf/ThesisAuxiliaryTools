package com.paper.auxiliary.config;

import com.paper.auxiliary.entity.manager.Setting;
import com.paper.auxiliary.service.Manage.ManageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PerfAnalyseConfig  implements InitializingBean {

    @Autowired
    private ManageService manageService;

    private Map<Integer,Boolean> settingMap;


    PerfAnalyseConfig()
    {
        settingMap = new HashMap<Integer,Boolean>();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Setting> settings = manageService.getSettingAll();
        if(settings == null)
        {
            System.out.println("Error");
            return ;
        }
        for(Setting iter : settings)
        {
            settingMap.put(iter.getSetId(),iter.getSetValue());
        }
    }

    @Override
    public String toString() {
        return "PerfAnalyseConfig{" +
                "settingMap=" + settingMap +
                '}';
    }

    public Boolean getMapValue(Integer id) {
        return settingMap.get(id);
    }
    public Boolean changeMapValue(Integer id)
    {
        return settingMap.put(id,!settingMap.get(id));
    }
    public Boolean setMapValue(Integer id, Boolean value)
    {
        return settingMap.put(id,value);
    }
}
