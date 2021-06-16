package com.priv.view;

import com.priv.model.MallLearnModel;
import com.priv.model.vo.ChannelFlowVo;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author Json
 * @date 2021/6/15 11:07
 */
public class MallExcel2007View extends AbstractExcel2007View {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1.获得封装数据
        MallLearnModel data = (MallLearnModel) model.get("data");

        // 2.获得 页码、行数、数据样式
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCellStyle cellStyle = getCellStyle(workbook);

        // 2.开始渲染
        setCellStringValue(row, "总计:" + data.getTotal(), 5, cellStyle);

        List<ChannelFlowVo> vos = data.getChannelFlowVos();
        int rowNum = 2;
        for (ChannelFlowVo vo : vos) {
            // 得到的开始行
            row = getSheetRow(sheet, rowNum, rowNum);

            setCellStringValue(row, vo.getChannelName(), 0, cellStyle);
            setCellStringValue(row, vo.getOrderCode(), 1, cellStyle);
            setCellStringValue(row, vo.getFlowCode(), 2, cellStyle);
            setCellStringValue(row, vo.getTxnType(), 3, cellStyle);
            setCellStringValue(row, vo.getTxnTime(), 4, cellStyle);
            setCellStringValue(row, vo.getPayType(), 5, cellStyle);
            setCellStringValue(row, vo.getPayAmount(), 6, cellStyle);

            rowNum++;
        }


        String fileName = URLEncoder.encode("Mall模拟报表.xlsx", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    }
}
