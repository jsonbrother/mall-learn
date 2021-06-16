package com.priv.view;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * Excel2007(.xlsx)视图类
 *
 * @author Json
 * @date 2021/6/15 10:54
 */
public abstract class AbstractExcel2007View extends AbstractView {

    private static final Logger logger = LoggerFactory.getLogger(AbstractExcel2007View.class);
    private static final String CONTENT_TYPE = "application/octet-stream";
    private String path;

    AbstractExcel2007View() {
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XSSFWorkbook workbook;
        if (this.path != null) {
            workbook = new XSSFWorkbook(new FileInputStream(new File(this.path)));
            logger.debug("Create Excel Workbook from " + this.getPath());
        } else {
            workbook = new XSSFWorkbook();
            logger.debug("Created Excel Workbook from scratch");
        }

        // 建立excel文件 数据渲染
        buildExcelDocument(model, workbook, request, response);

        // excel文件导出
        response.setContentType(getContentType());
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * @param model    数据模型
     * @param workbook 工作簿
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception 异常信息
     */
    protected abstract void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception;

    protected void setHeadValue(XSSFRow row, String[] head) {
        XSSFCellStyle style = getHeadStyle(row.getSheet().getWorkbook());
        int startColIndex = 0;
        for (String temp : head) {
            setCellStringValue(row, temp, startColIndex++, style);
        }
    }

    protected void setCellStringValue(XSSFRow row, String value, int colIndex, XSSFCellStyle style) {
        if (value == null) {
            value = "";
        }
        XSSFCell cell = row.getCell(colIndex) == null ? row.createCell(colIndex, CellType.STRING) : row.getCell(colIndex);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    protected String verifyStr(String str) {
        if (str == null || "".equals(str)) {
            return "0";
        }
        return str;
    }

    protected XSSFRow getSheetRow(XSSFSheet sheet, int rowNum) {
        int lastRowNum = sheet.getLastRowNum();
        sheet.shiftRows(rowNum, lastRowNum + 1, 1);
        // 返回得到的开始行
        return sheet.createRow((short) (rowNum));
    }

    XSSFRow getSheetRow(XSSFSheet sheet, int rowNum, int lastRowNum) {
        sheet.shiftRows(rowNum, lastRowNum + 1, 1);
        // 返回得到的开始行
        return sheet.createRow((short) (rowNum));
    }

    private XSSFCellStyle getHeadStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();

        // 设置单元格基础样式
        getBaseCellStyle(cellStyle);

        // 设置单元格填充的颜色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

        // 设置字体样式
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setBold(false);
        cellStyle.setFont(font);
        return cellStyle;
    }

    XSSFCellStyle getCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置单元格基础样式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 设置字体样式
        XSSFFont font = workbook.createFont();
        font.setFontName("等线");
        font.setFontHeightInPoints((short) 11);
        font.setBold(false);
        cellStyle.setFont(font);
        return cellStyle;
    }

    protected XSSFCellStyle getCellStyle(XSSFWorkbook workbook, short index) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置单元格基础样式
        getBaseCellStyle(cellStyle);

        // 设置单元格填充的颜色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(index);

        // 设置字体样式
        XSSFFont font = workbook.createFont();
        font.setFontName("等线");
        font.setFontHeightInPoints((short) 11);
        font.setBold(false);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 单元格通用样式
     */
    private void getBaseCellStyle(XSSFCellStyle cellStyle) {
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    private String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
