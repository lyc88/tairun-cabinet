package com.tairun.server.utils;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取conf.properties
 */
public class DataPropertiesUtil {
    private static Properties properties;
    private static Logger logger = LoggerFactory.getLogger(DataPropertiesUtil.class);

    static{
        InputStream is = null;
        try {
            if(properties == null) {
                properties = new Properties();
                is = DataPropertiesUtil.class.getResourceAsStream("/fileurl.properties");
                properties.load(is);
            }
        } catch (Exception e) {
            logger.error("读取[fileurl.properties]配置文件失败");
        }  finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static String getPropsAsString(String key) {
        return properties.getProperty(key);
    }
}
