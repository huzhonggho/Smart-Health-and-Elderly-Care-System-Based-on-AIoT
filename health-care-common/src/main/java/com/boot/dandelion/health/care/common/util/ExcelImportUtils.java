package com.boot.dandelion.health.care.common.util;

import com.boot.dandelion.health.care.common.anno.ExcelAttribute;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName ExcelImportUtils
 * @Description Excel导入工具类
 * @Author shr
 * @Date 2022/07/20
 */
public class ExcelImportUtils<T>  {

    private Class clazz;
    private Field[] fields;

    public ExcelImportUtils(Class clazz) {
        this.clazz = clazz;
        fields = clazz.getDeclaredFields();
    }


    /**
     * @Description: 基于注解读取Excel
     * @param: [is-文件上传流信息, rowIndex+起始行, cellIndex-起始单元格]
     * @return: java.util.List<T>
     * @author: shr
     * @date: 2022/07/20
     */
    public List<T> readExcel(InputStream is, int rowIndex, int cellIndex){
        List<T> list = new ArrayList<>();
        T entity = null;
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (int rowNum = rowIndex; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                entity = (T)clazz.newInstance();
                for(int j =cellIndex; j <row.getLastCellNum(); j++){
                    Cell cell = row.getCell(j);
                    for (Field field : fields) {
                        if(field.isAnnotationPresent(ExcelAttribute.class)){
                            field.setAccessible(true);
                            ExcelAttribute ea = field.getAnnotation(ExcelAttribute.class);
                            if(j==ea.sort()){
                                field.set(entity, covertAttrType(field,cell));
                            }
                        }
                    }
                }
                list.add(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @Description: 类型转换：将Cell单元格格式转换为字段类型
     * @param: [field, cell]
     * @return: java.lang.Object
     * @author: shr
     * @date: 2022/07/20
     */
    public Object covertAttrType(Field field, Cell cell) throws ParseException {
        String fieldType = field.getType().getSimpleName();
        if("String".equals(fieldType)){
            if("".equals(getCellValue(cell))){
                return null;
            }else {
                return getCellValue(cell);
            }
        }else if("Date".equals(fieldType)){
            if("".equals(getCellValue(cell))){
                return null;
            }else {
                return new SimpleDateFormat("yyyy-MM-dd").parse(getCellValue(cell));
            }
        }else if("int".equals(fieldType) || "Integer".equals(fieldType)){
            if("".equals(getCellValue(cell))){
                return null;
            }else {
                return Integer.parseInt(getCellValue(cell));
            }
        }else if("double".equals(fieldType) || "Double".equals(fieldType)){
            if("".equals(getCellValue(cell))){
                return null;
            }else {
                return Double.parseDouble(getCellValue(cell));
            }
        }else if("BigDecimal".equals(fieldType)){
            if("".equals(getCellValue(cell))){
                return null;
            }else {
                return BigDecimal.valueOf(Double.parseDouble(getCellValue(cell)));
            }
        }
        else{
            return null;
        }
    }

    /**
     * @Description: 格式转换为String
     * @param: [cell]
     * @return: java.lang.String
     * @author: shr
     * @date: 2022/07/20
     */
    public String getCellValue(Cell cell){
        if(cell == null){
            return "";
        }
        String regex = "^\\d*-\\d*-\\d*$";
        switch (cell.getCellTypeEnum()){
            case STRING:
                if(Pattern.matches(regex,cell.getStringCellValue())){
                    Date dt = DateUtils.parseSqlDateFormat(cell.getStringCellValue());
                    return new SimpleDateFormat("yyyy-MM-dd").format(dt);
                }else{
                    return cell.getRichStringCellValue().getString().trim();
                }
            case NUMERIC:
                if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
                    Date dt = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue());
                    return new SimpleDateFormat("yyyy-MM-dd").format(dt);
                }else{
                    // 防止数值变成科学计数法
                    String strCell = "";
                    Double num = cell.getNumericCellValue();
                    BigDecimal bd = new BigDecimal(num.toString());
                    if(bd!=null){
                        strCell = bd.toPlainString();
                    }
                    // 取出 浮点型 自动加的0
                    if(strCell.endsWith(".0")){
                        strCell = strCell.substring(0,strCell.indexOf("."));
                    }
                    return strCell;
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
