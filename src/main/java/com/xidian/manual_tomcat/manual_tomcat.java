package com.xidian.manual_tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 *
 * 访问地址：http://localhost:8080/xidian/index
 *http://localhost:8080/上下文/映射地址
 *
 */
public class manual_tomcat {

    private static final int PORT = 8080;
    private static final String CONTEXT_PATH = "/xidian";
    private static final String SERVLET_NAME = "indexServlet";

    public static void main(String[] args) throws LifecycleException {

        //创建Tomcat
        Tomcat tomcat = new Tomcat();
        //设置端口
        tomcat.setPort(PORT);
        //是否自动部署
        tomcat.getHost().setAutoDeploy(false);//不用自动部署
        //创建上下文对象
        StandardContext  standardContext = new StandardContext();
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        standardContext.setPath(CONTEXT_PATH);
        tomcat.getHost().addChild(standardContext);

        tomcat.addServlet(CONTEXT_PATH,SERVLET_NAME,new IndexServlet());
        standardContext.addServletMappingDecoded("/index",SERVLET_NAME);

        //开启服务器
        tomcat.start();

        //异步进行接收请求
        tomcat.getServer().await();




    }
}
