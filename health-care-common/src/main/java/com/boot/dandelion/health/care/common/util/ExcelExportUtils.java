package com.boot.dandelion.health.care.common.util;

import com.boot.dandelion.health.care.common.anno.ExcelAttribute;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ExcelExportUtils
 * @Description 基于模板导出数据
 * @Author shr
 * @Date 2022/07/20
 */
public class ExcelExportUtils<T> {

    /**写入数据的起始行*/
    private int rowIndex;

    /**需要提取的样式所在行号*/
    private int styleIndex;

    /**对象字节码*/
    private Class clazz;

    /**对象中所有属性*/
    private Field[] fields;

    public ExcelExportUtils(Class clazz, int rowIndex, int styleIndex){
        this.clazz = clazz;
        this.rowIndex = rowIndex;
        this.styleIndex = styleIndex;
        fields = clazz.getDeclaredFields();
    }

    public void export(HttpServletResponse response, InputStream is, List<T> objs, String fileName)throws Exception{

        XSSFWorkbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        CellStyle[] styles = getTemplateStyles(sheet.getRow(styleIndex));
        AtomicInteger dataAi = new AtomicInteger(rowIndex);
        for (T t : objs) {
            Row row = sheet.createRow(dataAi.getAndIncrement());
            for(int i=0; i<styles.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(styles[i]);
                for (Field field : fields) {
                    if (field.isAnnotationPresent(ExcelAttribute.class)) {
                        field.setAccessible(true);
                        ExcelAttribute ea = field.getAnnotation(ExcelAttribute.class);
                        if (i == ea.sort()) {
                            if(field.get(t)!=null){
                                String dateType = field.getType().getSimpleName();
                                if(!"Date".equals(dateType)){
                                    cell.setCellValue(field.get(t).toString());
                                }else {
                                    cell.setCellValue(DateUtils.sdfTimeFormat((Date) field.get(t)));
                                }
                            }
                        }
                    }
                }
            }
        }
        fileName = URLEncoder.encode(fileName,"UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition","attachment;filename="+new String(fileName.getBytes(), "UTF-8"));
        response.setHeader("filename",fileName);
        workbook.write(response.getOutputStream());
    }

    /**
     * @Description: 获取row对应的Cell样式数组
     * @param: [row]
     * @return: org.apache.poi.ss.usermodel.CellStyle[]
     * @author: shr
     * @date: 2022/07/20
     */
    public CellStyle[] getTemplateStyles(Row row){
        CellStyle[] styles = new CellStyle[row.getLastCellNum()];
        for(int i=0; i<row.getLastCellNum(); i++){
            styles[i] = row.getCell(i).getCellStyle();
        }
        return styles;
    }

}
