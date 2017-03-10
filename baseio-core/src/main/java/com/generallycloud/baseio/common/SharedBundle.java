/*
 * Copyright 2015-2017 GenerallyCloud.com
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.generallycloud.baseio.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class SharedBundle {

	private static SharedBundle bundle = new SharedBundle();

	public static SharedBundle instance() {
		return bundle;
	}

	private String				classPath		= null;
	private Map<String, String>	properties	= new HashMap<>();
	private Map<String, String>	fileMapping	= new HashMap<String, String>();

	public boolean getBooleanProperty(String key) {
		return getBooleanProperty(key, false);
	}

	private SharedBundle() {
	}

	public boolean getBooleanProperty(String key, boolean defaultValue) {
		String temp = properties.get(key);
		if (StringUtil.isNullOrBlank(temp)) {
			return defaultValue;
		}
		return Boolean.valueOf(temp);
	}

	public String getClassPath() {
		return classPath;
	}

	public double getDoubleProperty(String key) {
		return getDoubleProperty(key, 0);
	}

	public double getDoubleProperty(String key, double defaultValue) {
		String temp = properties.get(key);
		if (StringUtil.isNullOrBlank(temp)) {
			return defaultValue;
		}
		return Double.valueOf(temp);
	}

	public int getIntegerProperty(String key) {
		return getIntegerProperty(key, 0);
	}

	public int getIntegerProperty(String key, int defaultValue) {
		String temp = properties.get(key);
		if (StringUtil.isNullOrBlank(temp)) {
			return defaultValue;
		}
		return Integer.valueOf(temp);
	}

	public long getLongProperty(String key) {
		return getLongProperty(key, 0);
	}

	public long getLongProperty(String key, long defaultValue) {
		String temp = properties.get(key);
		if (StringUtil.isNullOrBlank(temp)) {
			return defaultValue;
		}
		return Long.valueOf(temp);
	}

	public String getProperty(String key) {
		return getProperty(key, null);
	}

	public String getProperty(String key, String defaultValue) {
		String value = properties.get(key);
		if (StringUtil.isNullOrBlank(value)) {
			return defaultValue;
		}
		return value;
	}

	public String getPropertyNoBlank(String key) throws PropertiesException {
		String value = properties.get(key);
		if (StringUtil.isNullOrBlank(value)) {
			throw new PropertiesException("property " + key + " is empty");
		}
		return value;
	}
	
	public synchronized void loadAllProperties(String file,Charset charset) throws IOException {

		if (StringUtil.isNullOrBlank(file)) {
			file = ".";
		}

		URL url = getClass().getClassLoader().getResource(file);

		if (url == null) {
			throw new IOException("file not found " + file);
		}

		loadAllProperties(url,charset);
	}

	public synchronized void loadAllProperties(String file) throws IOException {
		loadAllProperties(file, Encoding.UTF8);
	}
	
	public synchronized void loadAllProperties() throws Exception{
		loadAllProperties(Encoding.UTF8);
	}

	public synchronized void loadAllProperties(Charset charset) throws Exception {
		loadAllProperties(getURL(),charset);
	}

	private URL getURL() throws IOException {
		URL url = getClass().getClassLoader().getResource(".");
		if (url == null) {
			url = getClass().getProtectionDomain().getCodeSource().getLocation();
			if (url == null) {
				throw new IOException("no class path set");
			}
		}
		return url;
	}

	private void loadAllProperties(URL url,Charset charset) throws IOException {

		File root = new File(url.getFile());

		if (root.isFile() || root.getName().indexOf(".") != -1) {
			root = root.getParentFile();
		}

		String classPath = URLDecoder.decode(root.getCanonicalPath(), "UTF-8");

		if (classPath.endsWith(".jar") || classPath.endsWith(".jar/")) {
			root = root.getParentFile();
			classPath = URLDecoder.decode(root.getCanonicalPath(), "UTF-8");
		} else if (classPath.endsWith("test-classes") || classPath.endsWith("test-classes/")) {
			classPath += "/../classes";
		}

		setClassPath(new File(classPath).getCanonicalPath() + "/");

		root = new File(getClassPath());

		properties.clear();

		fileMapping.clear();

		loopLoadFile(root,charset);
	}

	private void loopLoadFile(File file,Charset charset) throws IOException {
		if (file.isDirectory()) {

			File[] files = file.listFiles();

			if (files == null) {
				throw new IOException("empty folder:" + file.getCanonicalPath());
			}

			for (File f : files) {

				loopLoadFile(f,charset);
			}
		} else {

			fileMapping.put(file.getName(), file.getCanonicalPath());

			if (file.getName().endsWith(".properties")) {
				Properties temp = FileUtil.readProperties(file,charset);
				putAll(properties, temp);
			}
		}
	}

	public String loadContent(String file, Charset charset) throws IOException {
		return FileUtil.readFileToString(loadFile(file), charset);
	}

	public File loadFile(String file) {
		return new File(classPath + file);
	}

	public Properties loadProperties(InputStream inputStream, Charset charset) throws IOException {
		return FileUtil.readProperties(inputStream, charset);
	}

	public Properties loadProperties(String file, Charset charset) throws IOException {

		File _file = loadFile(file);

		if (_file.exists()) {
			return loadProperties(FileUtil.openInputStream(_file), charset);
		}

		String filePath = fileMapping.get(file);

		if (filePath != null) {

			_file = new File(filePath);

			if (_file.exists()) {
				return loadProperties(FileUtil.openInputStream(_file), charset);
			}
		}

		return loadProperties(getClass().getClassLoader().getResourceAsStream(file), charset);
	}

	private void setClassPath(String classPath) {
		this.classPath = classPath.replace("\\", "/");
	}

	public void storageProperties(InputStream inputStream, Charset charset) throws IOException {
		Properties temp = loadProperties(inputStream, charset);
		putAll(properties, temp);
	}

	private void putAll(Map<String, String> target, Properties source) {
		for (Entry<Object, Object> e : source.entrySet()) {
			target.put(String.valueOf(e.getKey()), String.valueOf(e.getValue()));
		}
	}

	public void storageProperties(String file, Charset charset) throws IOException {
		Properties temp = loadProperties(file, charset);
		putAll(properties, temp);
	}

	public void clearProperties() {
		properties.clear();
	}
	
	class PropertiesException extends Exception{
		
		private static final long serialVersionUID = 1L;

		public PropertiesException() {
		}

		public PropertiesException(String message) {
			super(message);
		}

		public PropertiesException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}