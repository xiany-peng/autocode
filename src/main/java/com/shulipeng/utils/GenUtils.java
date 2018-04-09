package com.shulipeng.utils;

import com.shulipeng.config.Constant;
import com.shulipeng.domain.Column;
import com.shulipeng.domain.PluginAddr;
import com.shulipeng.domain.Table;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author pengxianyang
 * @Description: 生成代码
 * @date 2018/3/27
 */
public class GenUtils {

    public static final String CONFIG_LOCATION = "generator.properties";

    /**
     * 生成代码
     * @param table
     * @param columns
     * @param dbType
     * @param zip
     */
    public static void generatorCode(Table table, List<Column> columns,String dbType, ZipOutputStream zip) {

        //1.获取配置信息
        Configuration config = getConfig();
        //2.表和列类属性填充
        fillTableClassAttr(table,config.getString("needRemovePre"));

        boolean isNeedDate = false;//是否需要导入日期
        boolean isNeedBigDecimal = false;
        boolean isLogicDelete = false;//是否软删除
        Iterator<Column> iter = columns.iterator();
        while (iter.hasNext()){
            Column column = iter.next();
            fillColumnClassAttr(column,config);
            if("PRI".equals(column.getColumnKey()) && table.getPk() == null){
                table.setPk(column);
            }

            //判断是否有日期类型的行，
            if("Date".equals(column.getAttrType())){
                isNeedDate = true;
            }
            if("BigDecimal".equals(column.getAttrType())){
                isNeedBigDecimal = true;
            }
            if("is_delete".equalsIgnoreCase(column.getColumnName()) && "Integer".equals(column.getAttrType())){
                isLogicDelete = true;
            }

        }
        //如果没有主键，则设第一个为主键
        if(table.getPk() == null){
            table.setPk(columns.get(0));
        }
        table.setColumns(columns);

        //3.设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //4.封装模板需要的数据信息
        String module = config.getString("module",table.getClassNameSmall().toLowerCase());
        Map<String, Object> map = new HashMap<>(16);
        map.put("tableName", table.getTableName());
        map.put("comment", table.getComment());
        map.put("pk", table.getPk());
        map.put("className", table.getClassName());
        map.put("classNameSmall", table.getClassNameSmall());
        //map.put("pathName", conf.getString("package").substring(config.getString("package").lastIndexOf(".") + 1));
        map.put("columns", table.getColumns());
        map.put("package", config.getString("packageName"));
        map.put("author", config.getString("author"));
        map.put("company", config.getString("company"));
        map.put("email", config.getString("email"));
        map.put("datetime", new DateTime().toString("yyyy/MM/dd HH:mm:ss"));
        map.put("isNeedDate", isNeedDate);
        map.put("isNeedBigDecimal", isNeedBigDecimal);
        map.put("isLogicDelete", isLogicDelete);//是否含有软删标志
        map.put("batchRemove", config.getBoolean("batchRemove",true));//是否含有软删标志
        map.put("fuzzyLookup", config.getBoolean("fuzzyLookup",false));//是否需要模糊查询
        map.put("sidePagination", config.getString("sidePagination","server"));//分页方式
        map.put("fgPlugins", config.getStringArray("fgPlugins"));//前段需要插件
        map.put("fgPluginAddr", getPluginAddr());//前段所有插件地址
        map.put("module", module);//分页方式，
        VelocityContext context = new VelocityContext(map);

        //4.获取模板文件
        String fgFileType = config.getString("fgFileType",Constant.FG_FILE_TYPE_JSP);
        List<String> templates = getTemplate(dbType,fgFileType);
        for(String template : templates){
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template,"UTF-8");
            tpl.merge(context,sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, table.getClassNameSmall(), table.getClassName(),config.getString("package"),module,fgFileType )));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }


    /**
     * 获取配置文件
     * @return
     */
    private static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败:",e);
        }
    }

    /**
     * 给表格类添加属性
     * @param table
     * @param needRemovePre
     */
    private static void fillTableClassAttr(Table table, String needRemovePre) {
        String className = table.getTableName();
        if(StringUtils.isNoneBlank(needRemovePre) && className.substring(0,needRemovePre.length()).equals(needRemovePre)){
            className = className.substring(needRemovePre.length()-1,className.length());
        }

        className = toJavaStyle(className);
        table.setClassName(className);
        table.setClassNameSmall(StringUtils.uncapitalize(className));
    }

    /**
     * 给列类添加属性
     * @param column
     * @param conf
     */
    private static void fillColumnClassAttr(Column column, Configuration conf) {
        column.setAttrType(conf.getString(column.getDataType(),"unknowType"));

        String columnName = toJavaStyle(column.getColumnName());
        column.setAttrName(columnName);
        column.setAttrNameSmall(StringUtils.uncapitalize(columnName));

    }

    /**
     * 将字符串转换为java格式 eg class_name to ClassName
     * @param str
     * @return
     */
    public static String toJavaStyle(String str){
        return WordUtils.capitalizeFully(str,new char[]{'_'}).replace("_","");
    }

    /**
     * 根据数据库类型获取模板数据
     * @param dbType
     * @return
     */
    private static List<String> getTemplate(String dbType,String fgFileType) {
        List<String> templates = new ArrayList<>();
        templates.add(String.format("templates/vm/%s/domain.java.vm",dbType));
        templates.add(String.format("templates/vm/%s/dao.java.vm",dbType));
        templates.add(String.format("templates/vm/%s/mapper.xml.vm",dbType));
        templates.add(String.format("templates/vm/%s/service.java.vm",dbType));
        templates.add(String.format("templates/vm/%s/serviceImpl.java.vm",dbType));
        templates.add(String.format("templates/vm/%s/controller.java.vm",dbType));
        if(Constant.FG_FILE_TYPE_JSP.equals(fgFileType)){
            templates.add(String.format("templates/vm/%s/list.jsp.vm",dbType));
            templates.add(String.format("templates/vm/%s/edit.jsp.vm",dbType));
            templates.add(String.format("templates/vm/%s/add.jsp.vm",dbType));
        }
        return templates;
    }

    /**
     * 获取要生成的文件名
     * @param template
     * @param classNameSmall
     * @param className
     * @param module
     * @param fgFileType
     * @return
     */
    private static String getFileName(String template, String classNameSmall, String className, String packageName,String module,String fgFileType) {
        //将包路径改为真实的文件路径
        if(StringUtils.isNotBlank(packageName)){
            packageName = packageName.replace(".",File.separator);
        }
        //设置文件位置
        String basePath = "src" + File.separator + packageName + File.separator ;
        //domain
        if(template.contains("domain.java.vm")){
            return basePath  +"entity" + File.separator + module + File.separator  + className + ".java";
        }
        //dao
        if(template.contains("dao.java.vm")){
            return basePath +"dao" + File.separator + module + File.separator + className + "Mapper.java";
        }
        //service
        if(template.contains("service.java.vm")){
            return basePath +"service" + File.separator + module + File.separator + className + "Service.java";
        }
        //serviceImpl
        if(template.contains("serviceImpl.java.vm")){
            return basePath +"service" + File.separator + module + File.separator + "impl" + File.separator+ className + "ServiceImpl.java";
        }
        //controller
        if(template.contains("controller.java.vm")){
            return basePath +"controller" + File.separator + module + File.separator  + File.separator+ className + "Controller.java";
        }

        //以下需要根据框架分类
        if(Constant.FG_FILE_TYPE_JSP.equals(fgFileType)){
            //mapper
            if(template.contains("mapper.xml.vm")){
                return basePath +"mapper" + File.separator + module + File.separator + className + "Mapper.xml";
            }
            //list page
            if(template.contains("list.jsp.vm")){
                return "WebRoot" + File.separator +"WEB-INF"+ File.separator+"pages"+ File.separator + module + File.separator  +  classNameSmall + "List.jsp";
            }
            //edit page
            if(template.contains("edit.jsp.vm")){
                return "WebRoot" + File.separator +"WEB-INF"+ File.separator+"pages"+ File.separator + module + File.separator  +  classNameSmall + "Edit.jsp";
            }
            //add page
            if(template.contains("add.jsp.vm")){
                return "WebRoot" + File.separator +"WEB-INF"+ File.separator+"pages"+ File.separator + module + File.separator  +  classNameSmall + "Add.jsp";
            }
        }else{
            //mapper
            if(template.contains("mapper.xml.vm")){
                return basePath + File.separator + "main" + File.separator + packageName + File.separator +"mapper" + File.separator + classNameSmall + "Mapper.xml";
            }
        }

        return "";
    }

    /**
     * 获取所有插件的地址
     * @return
     */
    private static PluginAddr getPluginAddr() {
        try {
            return BeanUtils.propertyToBean(PluginAddr.class, new PropertiesConfiguration("addr.properties"));
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败:",e);
        }
    }


}
