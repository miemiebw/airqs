/**
 * 
 */
package com.github.airqs.web;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.mybatis.guice.XMLMyBatisModule;

import com.github.airqs.crawl.AirFinder;
import com.github.airqs.manager.AirManager;
import com.github.airqs.repo.CityHourRepo;
import com.github.airqs.repo.CityRepo;
import com.github.airqs.repo.StationHourRepo;
import com.github.airqs.repo.StationRepo;
import com.github.airqs.schedule.DataCrawlTask;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Eric
 *
 */
public class GuiceConfigListener implements ServletContextListener {
	
	public static final	String INJECTOR = Injector.class.getName();
	private ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(1);  
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext servletContext = arg0.getServletContext();
		Injector injector = Guice.createInjector(new AbstractModule() {
			
			@Override
			protected void configure() {
				bind(CityRepo.class);
				bind(StationRepo.class);
				bind(CityHourRepo.class);
				bind(StationHourRepo.class);
				bind(AirManager.class);
				bind(AirFinder.class);
			}
		},new XMLMyBatisModule() {
			
			@Override
			protected void initialize() {
				setEnvironmentId("airqs-datasource");
				setClassPathResource("mybatis-config.xml");
			}
		});
		
		servletContext.setAttribute(INJECTOR, injector);
		
		scheduExec.scheduleWithFixedDelay(injector.getInstance(DataCrawlTask.class), 5, 15 * 60, TimeUnit.SECONDS);
	}
	

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}


}
