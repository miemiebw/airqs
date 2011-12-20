/**
 * 
 */
package com.github.airqs.test;

import org.mybatis.guice.XMLMyBatisModule;

import com.github.airqs.crawl.AirFinder;
import com.github.airqs.manager.AirManager;
import com.github.airqs.repo.CityHourRepo;
import com.github.airqs.repo.CityRepo;
import com.github.airqs.repo.StationHourRepo;
import com.github.airqs.repo.StationRepo;
import com.github.glue.mvc.guice.MvcModule;
import com.github.glue.mvc.view.ViewResolver;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Eric
 *
 */
public class BaseTestRunner {

	protected static Injector injector = Guice.createInjector(new AbstractModule() {
		
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
	},new MvcModule() {
		
		@Override
		public ViewResolver[] getViewResolvers() {
			return new ViewResolver[]{};
		}
		
		@Override
		public String[] getActionPackages() {
			return new String[]{"com.github.airqs.web.action"};
		}
	});
	
	public BaseTestRunner(){
		injector.injectMembers(this);
	}
}
