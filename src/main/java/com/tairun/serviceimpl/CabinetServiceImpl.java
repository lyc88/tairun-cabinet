package com.tairun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.CabinetMapper;
import com.tairun.model.Cabinet;
import com.tairun.model.CabinetExample;
import com.tairun.service.CabinetService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cyt on 2017/7/18.
 */
@Service
public class CabinetServiceImpl implements CabinetService {
    @Resource
    private CabinetMapper cabinetMapper;

    /**
     * 查询自提柜信息
     * @return
     */
    public List<Cabinet> selectAll(Byte status) {
        CabinetExample cabinetExample = new CabinetExample();
        CabinetExample.Criteria criteria = cabinetExample.createCriteria();
        if (status >= 0) {
            criteria.andStatusEqualTo(status);
        } else if (status < 0) {
            List<Cabinet> list = cabinetMapper.selectByExample(cabinetExample);
        }
        PageHelper.startPage(1, 5);
        List<Cabinet> list = cabinetMapper.selectByExample(cabinetExample);
        PageInfo pageInfo = new PageInfo(list);
        return list;
    }

    public int updateCabinet(int cabid) {
        CabinetExample cabinetExample = new CabinetExample();
        CabinetExample.Criteria criteria=cabinetExample.createCriteria();
        // int num=criteria
        return 0;
    }

   /* public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        CabinetService cabinetService = (CabinetService) applicationContext.getBean("cabinetServiceImpl");
        System.out.println(cabinetService.selectAll().get(0).getBeginDate());
    }*/
}
