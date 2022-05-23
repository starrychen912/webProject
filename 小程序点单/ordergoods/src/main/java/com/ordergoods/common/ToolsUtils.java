package com.ordergoods.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ordergoods.entity.SysUser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jianggc at 2020/2/19.
 */
public class ToolsUtils {

    private static  final Logger logger= LoggerFactory.getLogger(ToolsUtils.class);

    //封装返回结果--针对layui table
    public static Map wrapperPageInfo(IPage listPage){
        Map<String, Object> pageInfo = new HashMap<String, Object>();//
        pageInfo.put("pageSize", listPage.getSize());//每页大小
        pageInfo.put("pageNum", listPage.getCurrent());//当前第几页
        pageInfo.put("pages", listPage.getPages());//总页数
        pageInfo.put("rows", listPage.getRecords());//查询的记录列表
        pageInfo.put("total", listPage.getTotal());//总记录数
        return pageInfo;
    }
    //封装返回结果
    public static Map wrapperResult(IPage listPage,String listCode){
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageSize", listPage.getSize());
        pageInfo.put("pageNum", listPage.getCurrent());
        pageInfo.put("pages", listPage.getPages());
        pageInfo.put("total", listPage.getTotal());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(listCode, listPage.getRecords());
        resultMap.put("pageInfo", pageInfo);
        return resultMap;
    }

    /**
     * 从Excel中获取单元格数据，返回格式为String
     * @param cell
     * @return
     */
    public static String getValFromCell(Cell cell){
        if(cell==null){
            return null;
        }
        CellType cellTypeEnum = cell.getCellType();
        switch (cellTypeEnum) {
            case NUMERIC:return String.valueOf(cell.getNumericCellValue());
            case STRING:return cell.getStringCellValue();
            case BOOLEAN:return String.valueOf(cell.getBooleanCellValue());
            case ERROR:return String.valueOf(cell.getErrorCellValue());
            case FORMULA:
            case _NONE:
            case BLANK:return null;
            default:
                return null;
        }
    }

    //从cell获取日期类型值
    public static String getDateCellVal(Cell cell,String pattern){
        if(cell==null){
            return null;
        }
        Date value = cell.getDateCellValue();
        return DateUtils.format(value,pattern);
    }

    public static SysUser getLoginUser(HttpSession session){
        Object user = session.getAttribute("user");

        return user==null?null:(SysUser) user;
    }

    /**
     * 获取登录用户ID
     * @param session
     * @return
     */
    public static Long getLoginUserId(HttpSession session){
        Object user = session.getAttribute("user");
        if(user==null){
           return  null;
        }else{
            SysUser loginUser= (SysUser) user;
            return loginUser.getId();
        }
    }

    /**
     * 获取登录用户名称
     * @param session
     * @return
     */
    public static String getLoginUserName(HttpSession session){
        Object user = session.getAttribute("user");
        if(user==null){
           return null;
        }else{
            SysUser loginUser= (SysUser) user;
            return loginUser.getName();
        }
    }

    //驼峰转下划线
    public static String camelToUnderline(String fieldName){
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<fieldName.length();i++){
            char c = fieldName.charAt(i);
            //65-90是大写A到大写Z 97到122是小写a到小写z。
            if(i!=0&&i!=fieldName.length()-1&&c>=65&&c<=90){
                //说明是大写且非首末字母则需要转驼峰
                char lowerCase = Character.toLowerCase(c);
                stringBuffer.append("_").append(lowerCase);
            }else{
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }

    //对象转map且字段转换为下划线格式
    public static Map<String,Object> convertObjToMap(Object obj){
        if(obj == null){
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();

        List excludeFields=new ArrayList();
        excludeFields.add("serialVersionUID");
       //只取属性字段
       for (Field declaredField : obj.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                String fieldName = declaredField.getName();
                if(!excludeFields.contains(fieldName)){
                    map.put(camelToUnderline(fieldName), declaredField.get(obj));
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(),e);
            }
        }
//        //只取含有get方法的
//        List<String> listName = new ArrayList<>();
//        for (Field declaredField : obj.getClass().getDeclaredFields()) {
//            listName.add(declaredField.getName());
//        }
//        for (Method declaredMethod : obj.getClass().getDeclaredMethods()) {
//            if (declaredMethod.getName().startsWith("get")) {
//                String name = (String.valueOf(declaredMethod.getName().charAt(3)).toLowerCase())+declaredMethod.getName().substring(4);
//                try {
//                    if(listName.contains(name)){
//                        map.put(name, declaredMethod.invoke(obj));
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return map;
    }
    //对象转map且字段转换为下划线格式
    public static Map<String,Object> convertObjToMap(Object obj,Boolean isNull){
        if(obj == null){
            return Collections.emptyMap();
        }
        if(isNull==null){
            isNull=true;
        }
        Map<String, Object> map = new HashMap<>();

        List excludeFields=new ArrayList();
        excludeFields.add("serialVersionUID");
       //只取属性字段
       for (Field declaredField : obj.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                String fieldName = declaredField.getName();
                if(!excludeFields.contains(fieldName)){
                    Object value = declaredField.get(obj);
                    if(!isNull){
                        if(value!=null){
                            map.put(camelToUnderline(fieldName),value );
                        }
                    }else{
                        map.put(camelToUnderline(fieldName),value );
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return map;
    }

    /**
     * collection 是否为空
     * @param collection
     * @return true:is empty ;false:not empty
     */
    public static boolean isEmpty(Collection collection){
       return !isNotEmpty(collection);
    }
    /**
     * collection 是否不为空
     * @param collection
     * @return true:is empty ;false:not empty
     */
    public static boolean isNotEmpty(Collection collection){
        if(collection==null||collection.isEmpty()){
            return false;
        }else{
            return  true;
        }
    }

    //返回订单号
    public static  String createOrderNum(){
        String yyyyMMddHHmmss = DateUtils.format(new Date(), "yyyyMMddHHmmss");
        int i = new Random().nextInt(1000) + 1000;
        return "M"+yyyyMMddHHmmss+i;
    }

    /**
     * 将百分数转换为BigDecimal
     * @param str e.g:10%
     * @return 0.1
     */
    public static BigDecimal stringToBigDecimal(String str){
        if(str==null){
            logger.debug("str is null");
            return null;
        }
        if(str.equals("0")||str.equals("0%")){
            return new BigDecimal(0);
        }
        //左闭右开
        String substring = str.substring(0, str.length() - 1);
        BigDecimal bigDecimal=new BigDecimal(substring);
        BigDecimal divide = bigDecimal.divide(new BigDecimal(100));
        logger.debug(str+" 转换后的结果为："+divide.doubleValue());
        return divide;
    }

    /**
     * 动态获取object的field值
     * @param Object
     * @param field
     * @return
     */
    public static String getValueFromObj(Object obj, String field) {
        for (Field declaredField : obj.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                String fieldName = declaredField.getName();
                if(fieldName.equals(field)){
                    return String.valueOf(declaredField.get(obj));
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(),e);
                return null;
            }
        }
        return null;
    }
}
