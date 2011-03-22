package com.ultrawise.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	private Logger logger;
	private static Log log;

	private Log() {
		String filePath = this.getClass().getResource("/").getPath();
		// 找到log4j.properties配置文件所在的目录(已经创建好)
		filePath = filePath.replace("bin", "src");
		logger = Logger.getLogger(this.getClass());
		// loger所需的配置文件路径
		PropertyConfigurator.configure(filePath + "log4j.properties");
	}

	/**
	 * 取得日志对象
	 * 
	 * @return 日志对象
	 */
	public static Log getInstance() {
		if (log == null) {
			log = new Log();
		}
		return log;
	}

	/**
	 * 取得apache.log4j的Logger对象
	 * 
	 * @return Logger对象
	 */
	public Logger getLogger() {
		return logger;
	}
}
