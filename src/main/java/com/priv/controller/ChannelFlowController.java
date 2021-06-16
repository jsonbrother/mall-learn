package com.priv.controller;

import cn.hutool.core.bean.BeanUtil;
import com.priv.config.FileConfig;
import com.priv.facade.report.IChannelFlowFacade;
import com.priv.model.bo.ChannelFileImportBO;
import com.priv.model.dto.ChannelFileImportDTO;
import com.priv.model.MallLearnModel;
import com.priv.model.vo.ChannelFlowVo;
import com.priv.view.MallExcel2007View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Json
 * @date 2021/6/9 9:57
 */
@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelFlowController {

    private final IChannelFlowFacade iChannelFlowFacade;
    private final FileConfig fileConfig;

    @PostMapping("/import")
    @ResponseBody
    public String doImportChannelFile(@RequestBody ChannelFileImportBO channelFileImportBO) {
        ChannelFileImportDTO channelFileImportDTO = new ChannelFileImportDTO();
        BeanUtil.copyProperties(channelFileImportBO, channelFileImportDTO);
        iChannelFlowFacade.importChannelFile(channelFileImportDTO);
        return "success";
    }

    @GetMapping("/export")
    public ModelAndView doExportChannelFile() {
        MallExcel2007View view = new MallExcel2007View();
        view.setPath(fileConfig.getFilePath() + File.separator + "\\template\\模拟报表.xlsx");

        MallLearnModel dataDTO = new MallLearnModel();
        List<ChannelFlowVo> channelFlowVos = simulationData();
        dataDTO.setTotal(String.valueOf(channelFlowVos.size()));
        dataDTO.setChannelFlowVos(channelFlowVos);

        ModelAndView mav = new ModelAndView(view);
        mav.addObject("data", dataDTO);

        return mav;
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
