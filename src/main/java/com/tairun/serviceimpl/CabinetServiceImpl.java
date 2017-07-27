package com.tairun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tairun.dao.CabinetMapper;
import com.tairun.model.Cabinet;
import com.tairun.model.CabinetExample;
import com.tairun.service.CabinetService;
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
    public List<Cabinet> selectAll(Byte status,Integer page,Integer rows) {
        CabinetExample cabinetExample = new CabinetExample();
        CabinetExample.Criteria criteria = cabinetExample.createCriteria();
        if (status >= 0) {
            criteria.andStatusEqualTo(status);
        } else if (status < 0) {
            List<Cabinet> list = cabinetMapper.selectByExample(cabinetExample);
        }
        PageHelper.startPage(page, rows);
        List<Cabinet> list = cabinetMapper.selectByExample(cabinetExample);
        //PageUtil pageUtil=new PageUtil();
        //pageUtil.setRows(list);
        PageInfo pageInfo = new PageInfo(list);
        //pageUtil.setTotal(pageInfo.getTotal());
        System.out.print(pageInfo.getTotal());
        System.out.print(rows);
        return list;
    }

    public int updateCabinet(Cabinet cabinet) {
        CabinetExample cabinetExample = new CabinetExample();
        CabinetExample.Criteria criteria=cabinetExample.createCriteria();
        int num=cabinetMapper.updateByPrimaryKey(cabinet);
        return num;
    }

   /* public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        CabinetService cabinetService = (CabinetService) applicationContext.getBean("cabinetServiceImpl");
        System.out.println(cabinetService.selectAll().get(0).getBeginDate());
    }*/
}
