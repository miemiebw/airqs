/**
 * 
 */
package com.github.airqs.base;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源
 * @author Eric.Lee
 *
 */
public class C3P0DataSourceFactory implements DataSourceFactory {
	 private ComboPooledDataSource dataSource = null; 
	/* (non-Javadoc)
	 * @see org.apache.ibatis.datasource.DataSourceFactory#setProperties(java.util.Properties)
	 */
	@Override
	public void setProperties(Properties properties) {
		this.dataSource = new ComboPooledDataSource(); 
		this.dataSource.setUser(properties.getProperty("username"));
        this.dataSource.setPassword(properties.getProperty("password"));
        this.dataSource.setJdbcUrl(properties.getProperty("url"));
        try {
            this.dataSource.setDriverClass(properties.getProperty("driver"));
        } catch (PropertyVetoException e) {
        	e.printStackTrace();
        }
        this.dataSource.setInitialPoolSize(10);
        this.dataSource.setMaxPoolSize(100);
        this.dataSource.setMaxStatements(1000);
        this.dataSource.setMaxStatementsPerConnection(100);
        this.dataSource.setMaxIdleTime(30);
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.datasource.DataSourceFactory#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

}
