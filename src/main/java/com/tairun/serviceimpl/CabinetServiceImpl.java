package com.tairun.serviceimpl;

import com.tairun.dao.CabinetMapper;
import com.tairun.model.Cabinet;
import com.tairun.service.CabinetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
    @Service("cabinetService")
    public class CabinetServiceImpl implements CabinetService{
        @Resource
        private CabinetMapper cabinetMapper;
    public List<Cabinet> selectAll() {
        return cabinetMapper.selectAll();
    }
    public int updateCabinet(int cabid) {return cabinetMapper.updateCabinet(cabid);
    }
    public CabinetMapper getCabinetMapper() {
        return cabinetMapper;
    }

    public void setCabinetMapper(CabinetMapper cabinetMapper) {
        this.cabinetMapper = cabinetMapper;
    }
}
