package com.windtower.client.Controller;

import com.windtower.client.OS.ClientOS;
import com.windtower.client.UI.WindTowerModel;
import com.windtower.client.UI.WindTowerView;
import com.windtower.client.UI.interfaces.IWindTowerEngineObserver;
import com.windtower.config.client.WindTowerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * @program: windtower
 * @description:
 * @author: wangtengke
 * @create: 2018-11-26
 **/
@Slf4j
@Component
public class SimuBootInit implements IWindTowerEngineObserver {
    @Autowired
    private ClientOS clientOS;
    private WindTowerView view;
    public void start(){
        log.info("bootInit|called");
//        WindTowerProperties.getInstance().init();
        WindTowerView view = new WindTowerView();
        this.view = view;
        String windtowerID= WindTowerProperties.getInstance().getWindTowerID();
        log.info("windtowerID is [{}]",windtowerID);
        try {
            clientOS.loadSimulationBasicAndInit(windtowerID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModel(WindTowerModel model) {
        if(view != null) {
            view.updateUI(model);
        }
    }
}
