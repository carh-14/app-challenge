package com.app.challenge.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.io.InputStream;

public class ReadConfigProperties {
	
	String propFileName = "config.properties";
	InputStream inputStream;
	
		private String appiumServer;
		private String appPath;
		private String deviceName;
		private String keyboardReset;
		private String keyboardUnicode;
		private String bsServer;
		private String bsUsername;
		private String bsAccessKey;
		private String bsAppServer;
		private String bsAppUrl;

		private String apiPath;
		
		public String getAppiumServer() {
			return appiumServer;
		}

		public String getAppPath() {
			return appPath;
		}

		public void setAppPath(String appPath) {
			this.appPath = appPath;
		}

		public String getDeviceName() {
			return deviceName;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public String getKeyboardReset() {
			return keyboardReset;
		}

		public void setKeyboardReset(String keyboardReset) {
			this.keyboardReset = keyboardReset;
		}

		public String getKeyboardUnicode() {
			return keyboardUnicode;
		}

		public void setKeyboardUnicode(String keyboardUnicode) {
			this.keyboardUnicode = keyboardUnicode;
		}

		public void setAppiumServer(String app) {
			this.appiumServer = app;
		}
		

		public String getApiPath() {
			return apiPath;
		}

		public void setApiPath(String apiPath) {
			this.apiPath = apiPath;
		}
		
		public String getBsServer() {
			return bsServer;
		}

		public void setBsServer(String bsServer) {
			this.bsServer = bsServer;
		}

		public String getBsUsername() {
			return bsUsername;
		}

		public void setBsUsername(String bsUsername) {
			this.bsUsername = bsUsername;
		}

		public String getBsAccessKey() {
			return bsAccessKey;
		}

		public void setBsAccessKey(String bsAccessKey) {
			this.bsAccessKey = bsAccessKey;
		}
		
		public String getBsAppServer() {
			return bsAppServer;
		}

		public void setBsAppServer() {
			String basePath = "http://"+getBsUsername()+":"+getBsAccessKey()+"@"+getBsServer()+"/wd/hub";
			System.out.println("Appium server " + basePath );
			this.bsAppServer = basePath;
		}
		
		public String getBsAppUrl() {
			return bsAppUrl;
		}

		public void setBsAppUrl(String bsAppUrl) {
			this.bsAppUrl = bsAppUrl;
		}
		
		public String getFile(String file) {
			File testFile = new File(file);
		    String currentPath = testFile.getAbsolutePath();
		    return currentPath;
		}
		
		public void getPropertyValues() throws IOException {
			try {
				Properties prop = new Properties();
				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	 
				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
	 
				// get the properties
				setAppPath(getFile(prop.getProperty("app_path")));
				setAppiumServer(prop.getProperty("appium_server_local"));
				setDeviceName(prop.getProperty("device_name"));
				setKeyboardReset(prop.getProperty("keyboard_reset"));
				setKeyboardUnicode(prop.getProperty("keyboard_unicode"));
				setBsUsername(prop.getProperty("bs_username"));
				setBsServer(prop.getProperty("bs_server"));
				setBsAccessKey(prop.getProperty("bs_access_key"));
				setBsAppUrl(prop.getProperty("bs_app_url"));
				setBsAppServer();
				
				System.out.printf("Environment properties: [App Path: %s, Appium Server: %s, Device Name: %s, Keyboard Reset: %s, KeyBoaard Unicode: %s]",bsAppServer,appiumServer,deviceName,keyboardReset,keyboardUnicode);
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			} finally {
				inputStream.close();
			}
		}
		
		public void getPropertyValuesAPI() throws IOException {
			try {
				Properties prop = new Properties();
				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	 
				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
	 
				// get the properties
				setApiPath(prop.getProperty("api_test"));
				
				System.out.printf("Environment properties: [Api Test Path: %s]", apiPath);
				
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			} finally {
				inputStream.close();
			}
		}
}
