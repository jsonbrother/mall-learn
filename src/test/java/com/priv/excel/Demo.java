package com.priv.excel;

import com.priv.model.vo.ChannelFlowVo;
import com.priv.view.MallExcel2007View;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Json
 * @date 2021/6/15 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo {

    @Test
    public void demo() {
        MallExcel2007View view = new MallExcel2007View();
        view.setPath("E:\\tmp\\mall-file\\template\\模拟报表.xlsx");
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("data", simulationData());
    }


    /**
     * 模拟数据
     */
    private List<ChannelFlowVo> simulationData() {
        List<ChannelFlowVo> list = new ArrayList<>();

        ChannelFlowVo flowVo1 = new ChannelFlowVo();
        flowVo1.setChannelName("全球购");
        flowVo1.setOrderCode("O20210601125955111465");
        flowVo1.setFlowCode("20210601125959111470");
        flowVo1.setTxnType("pay");
        flowVo1.setTxnTime("20210601125959");
        flowVo1.setPayType("wx");
        flowVo1.setPayAmount("19900");
        list.add(flowVo1);

        ChannelFlowVo flowVo2 = new ChannelFlowVo();
        flowVo2.setChannelName("全球购");
        flowVo2.setOrderCode("O20210601133729111557");
        flowVo2.setFlowCode("20210601133729111563");
        flowVo2.setTxnType("pay");
        flowVo2.setTxnTime("20210601133729");
        flowVo2.setPayType("wx");
        flowVo2.setPayAmount("19900");
        list.add(flowVo2);

        return list;
    }
}
