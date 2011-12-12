package com.github.airqs.crawl;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Singleton;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Singleton
public class AirFinder {

	
	String param = "{\"customParams\":\"\",\"expectCount\":null,\"hasGeometry\":false,\"highlight\":null,\"queryAllLayer\":false,\"queryLayers\":[{\"groupClause\":\"\",\"layerId\":0,\"layerName\":\"CityStation_ORG1@China400\",\"returnFields\":[\"CHINESE_CH\",\"NAME\",\"SO2实时\",\"NO2实时\",\"PM10实时\",\"API\",\"DATATIME\"],\"sortClause\":\"order by SHOWINDEX\",\"whereClause\":\"\",\"relQueryTableInfos\":null}],\"networkType\":0,\"returnFields\":null,\"startRecord\":0,\"whereClause\":\"\",\"returnCenterAndBounds\":true,\"returnShape\":true}";
	String url = "http://58.68.130.147/IS/AjaxDemo/query.ashx?map=China&method=QueryBySql&trackingLayerIndex=-1&userID=%22c9765d36-26c6-4fea-bca5-94153914ca33%22&queryParam=";
	public List<AirCityHour> findCityHour() {
		List<AirCityHour> hours = Lists.newArrayList();
		
		String responseBody = find(param);
		
		if(!Strings.isNullOrEmpty(responseBody)){
			JSONObject jsonObject = JSON.parseObject(responseBody);
			JSONArray recordsets = jsonObject.getJSONArray("recordsets");
			JSONObject recordset = recordsets.getJSONObject(0);
			JSONArray records = recordset.getJSONArray("records");
			for (int i = 0; i < records.size(); i++) {
				AirCityHour hour = new AirCityHour();
				JSONObject record = records.getJSONObject(i);
				JSONArray fieldValues = record.getJSONArray("fieldValues");
				hour.setProvinceName(fieldValues.getString(0));
				hour.setCityName(fieldValues.getString(1));
				hour.setSo2(fieldValues.getFloat(2));
				hour.setNo2(fieldValues.getFloat(3));
				hour.setPm10(fieldValues.getFloat(4));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H");
				try {
					hour.setReportTime(format.parse(fieldValues.getString(6)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				hours.add(hour);
			}
		}
		
		
		return hours;
	}
	
	private String find(String param){
		HttpClient httpclient = new DefaultHttpClient();
		String responseBody = null;
		try {
	        HttpGet httpget = new HttpGet(url + URLEncoder.encode(param, "utf-8"));  
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = httpclient.execute(httpget, responseHandler);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return responseBody;
	}

}
