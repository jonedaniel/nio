package excelexport;

import excelexport.db.DbUtil;
import excelexport.model.Block;
import excelexport.model.Csdn;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Title: ExcelExport
 * Description: 本地导出excel 工具类
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2018/9/20
 */
public class ExcelExport {

    public List<Csdn> query1() {
        return DbUtil.query("select * from csdn limit 0,10", Csdn.class, "mysql");
    }


    @Test
    public void testPoi() {
        String sql  = "select * from hdic.t_hm_resblock";

//        String sql = "select * from csdn limit 0,10";

//        exportPoi(sql, Block.class, "oracle");
    }

    static <T> void exportPoi(String sql, Class<T> clz) {
        exportPoi(sql, clz,"oracle");
    }

    static <T> void exportPoi(String sql, Class<T> clz, String dbName) {
        String path = "C:\\Users\\Administrator\\Desktop\\";
        String name = "test";
        exportPoi(sql, clz, path, name, dbName);
    }

    /**
     * 输出excel，需要自己创建model类
     *
     * @param sql  ""
     * @param clz  "model 类型"
     * @param path "路径"
     * @param name "文件名"
     * @return
     * @author zhaomenghui
     * @createDate 2018/9/20
     */
    static <T> void exportPoi(String sql, Class<T> clz, String path, String name, String dbName) {
        List<T> list = DbUtil.query(sql, clz, dbName);

        HSSFWorkbook excelWorkBook = new HSSFWorkbook();
        HSSFSheet    excelSheet    = excelWorkBook.createSheet();
        setSheetValue(list, excelSheet, clz);

        exportToDisk(excelWorkBook, path, name);
    }

    private static void exportToDisk(HSSFWorkbook excelWorkBook, String path, String name) {
        OutputStream ioFileStream = null;
        try {
            ioFileStream = new FileOutputStream(path + name + ".xls");
            excelWorkBook.write(ioFileStream);
            ioFileStream.flush();
            ioFileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T> void setSheetValue(List<T> list, HSSFSheet excelSheet, Class<T> clz) {
        try {
            BeanInfo             beanInfo            = Introspector.getBeanInfo(clz, Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            //设置表头
            HSSFRow headerRow = excelSheet.createRow(0);
            for (int i = 0; i < propertyDescriptors.length; i++) {
                HSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(propertyDescriptors[i].getName());
            }
            //插入值
            int i = 1;
            for (T t : list) {
                HSSFRow row = excelSheet.createRow(i++);
                int     j   = 0;
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    Method   readMethod = propertyDescriptor.getReadMethod();
                    Object   o          = readMethod.invoke(t);
                    HSSFCell cell       = row.createCell(j++);
                    cell.setCellValue(o.toString());
                }
            }
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIntroSequence() throws IntrospectionException {
        BeanInfo             beanInfo            = Introspector.getBeanInfo(Block.class, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor.getName());
        }
    }

}
