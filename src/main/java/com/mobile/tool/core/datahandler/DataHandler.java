package com.mobile.tool.core.datahandler;

import static com.mobile.tool.core.util.Constant.AMQ_MESSAGE_FIELD_NAME;
import static com.mobile.tool.core.util.Constant.AMQ_MESSAGE_LISTENER_FIELD_NAME;
import static com.mobile.tool.core.util.Constant.AMQ_MESSAGE_LISTENER_LOCATIONHANLDER;
import static com.mobile.tool.core.util.Constant.AMQ_MESSAGE_LISTENER_USERLOCATIONHANLDER;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.mobile.tool.core.entity.Location;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;
import com.mobile.tool.core.entity.UserSettingsMeasure;
import com.mobile.tool.inventory.entity.ItemMeasure;
import com.mobile.tool.promo.entity.PromoService;
import com.mobile.tool.promo.model.UserDetails;
import com.mobile.tool.promo.utility.PromoConstant;
import com.mobile.tool.request.intr.entity.RequestProcessorDimension;
import com.mobile.tool.request.intr.entity.UserRequestMeasure;

@Component
public class DataHandler {

	@Autowired
	private DBManager dbManager;
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public List<PromoService> fetchServicesByLocationAndTime(double latitude, double longitude, double radius, Date timeTillMinute){
		return dbManager.findServicesByLocationAndTime(latitude, longitude, radius, timeTillMinute);
	}
	
	public List<PromoService> fetchServicesByLocationTimeAndUserPreference(double latitude, double longitude, double radius, List<String> userPrefferedServiceTypes){
		return dbManager.findServicesByLocationTimeAndUserPreference(latitude, longitude, radius, userPrefferedServiceTypes);
	}

	public UserDetails fetchUserDetailsByUserId(String userId) {
		return DataStorage.fetchUserDetailsByUserId(userId);
	}
	
	public Location fetchLocationByLatLong(double lat, double lon) {
		return DataStorage.fetchLocationByLatLong(lat, lon);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ServiceType> fetchServiceTypeCodesByUserPreferrence(UserDetails userDetails) {
		List<String> serviceTypeCodeList = DataStorage.fetchServiceTypeCodesByUserPreferrence(userDetails.getUserId());
		if(serviceTypeCodeList==null || serviceTypeCodeList.isEmpty()){
			UserSettingsMeasure userSettingsMeasure= dbManager.fetchUserSettingsMeasuresByUserId(userDetails.getUserId());
			if(userSettingsMeasure!=null){
				serviceTypeCodeList = Arrays.asList(userSettingsMeasure.getServiceTypeCodes().split(","));
			}else{
				Collection serviceTypesCol = DataStorage.fetchAllServiceTypes();
				List<String> serviceTypeNames = (List<String>) CollectionUtils.collect(serviceTypesCol, new Transformer() {
					@Override
					public Object transform(Object serviceType) {
						if(serviceType instanceof ServiceType)
							return ((ServiceType) serviceType).getCode();
						return null;
					}
				});
				serviceTypeCodeList.addAll(serviceTypeNames);
				userSettingsMeasure = new UserSettingsMeasure();
				userSettingsMeasure.setUserId(userDetails.getUserId());
				userSettingsMeasure.setServiceTypeCodes(serviceTypesCol.toString());
				userSettingsMeasure.setModifiedAt(new Timestamp(new Date().getTime()));
				dbManager.insertOrUpdateUserSettingMeasure(userSettingsMeasure);
			}
			DataStorage.storeServiceTypeCodesByUserPreferrence(userDetails.getUserId(), userSettingsMeasure.getServiceTypeCodes());
		}
		
		List<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
			for (String serviceTypeCode : serviceTypeCodeList) {
				serviceTypeList.add(DataStorage.fetchServiceTypeByCode(serviceTypeCode));
			}
		return serviceTypeList;
	}

	public ServiceType fetchServiceTypeByCode(String servicetypeCode) {
		return DataStorage.fetchServiceTypeByCode(servicetypeCode);
	}

	public void storeLocationInCacheAndReportDb(Location location) {
		publishMessageInQueue(AMQ_MESSAGE_LISTENER_LOCATIONHANLDER, location.toJSONString());
		DataStorage.storeLocationInCache(location);
	}

	public void storeUserDetailsInCacheAndReportDb(UserDetails userDetails) {
		publishMessageInQueue(AMQ_MESSAGE_LISTENER_USERLOCATIONHANLDER, userDetails.toJSONString());
		DataStorage.storeUserDetailsInCache(userDetails);
	}
	
	public void storeUserLastVisitInformationInReportDb(String userId, String userLastVisitsInfo) throws JSONException {
		JSONObject obejct = new JSONObject();
		obejct.put("userId", userId);
		obejct.put("userVisitInfos", new JSONArray(userLastVisitsInfo));
		publishMessageInQueue(PromoConstant.AMQ_MESSAGE_LISTENER_USERVISITHANDLER, obejct.toString());
	}

	private void publishMessageInQueue(final String listenerName, final Object message){
		jmsTemplate.send(new MessageCreator() {
        public Message createMessage(Session session) throws JMSException {
        	MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString(AMQ_MESSAGE_LISTENER_FIELD_NAME, listenerName);
            mapMessage.setObject(AMQ_MESSAGE_FIELD_NAME, message);
            return mapMessage;
          }
        });
	}

	public List<ItemMeasure> fetchItemsByCategoryAndSubCategoryName(String serviceTypeName, String subCategoryName) {
		return dbManager.fetchItemsByCategoryAndSubCategoryName(serviceTypeName, subCategoryName);
	}

	public SubCategoryDimension fetchSubCategoryByServiceTypeAndSubCategoryName(ServiceType serviceType, String subCategoryName) {
		return dbManager.fetchSubCategoryByServiceTypeAndSubCategoryName(serviceType, subCategoryName);
	}

	public void addAllItemMeasures(List<ItemMeasure> itemMeasures) {
		dbManager.addAllItemMeasures(itemMeasures);		
	}

	public Collection<ServiceType> fetchAllServiceTypes() {
		return DataStorage.fetchAllServiceTypes();
	}

	public RequestProcessorDimension fetchRequestProcessorByRequestType(String requestTypeName) {
		return dbManager.fetchRequestProcessorByRequestType(requestTypeName);
	}

	public void insertOrUpdateUserRequestMeasure(UserRequestMeasure userRequestMeasure) {
		dbManager.insertOrUpdateUserRequestMeasure(userRequestMeasure);
	}

	public List<UserRequestMeasure> fetchAllUserRequestMeasureForProcessing() {
		return dbManager.fetchAllUserRequestMeasureForProcessing();
	}

	public List<SubCategoryDimension> fetchAllSubCategoriesForService(String serviceTypeCode) {
		return dbManager.fetchAllSubCategoriesForService(serviceTypeCode);
	}

	public ItemMeasure fetchItemsByitemCode(String itemCode) {
		return dbManager.fetchItemsByitemCode(itemCode);
	}

	public void insertOrUpdateItemMeasure(ItemMeasure itemMeasure) {
		dbManager.insertOrUpdateItemMeasure(itemMeasure);
	}

	public void deleteItemMeasure(ItemMeasure itemMeasure) {
		dbManager.deleteItemMeasure(itemMeasure);
	}

	public void updateUserPreferrencesByUserId(String userId, String serviceTypeCodes) {
		DataStorage.storeServiceTypeCodesByUserPreferrence(userId, serviceTypeCodes);
		UserSettingsMeasure userSettingsMeasure= dbManager.fetchUserSettingsMeasuresByUserId(userId);
		if(userSettingsMeasure == null){
			userSettingsMeasure = new UserSettingsMeasure();
			userSettingsMeasure.setUserId(userId);
			userSettingsMeasure.setModifiedAt(new Timestamp(new Date().getTime()));
		}
		userSettingsMeasure.setServiceTypeCodes(serviceTypeCodes);
		dbManager.insertOrUpdateUserSettingMeasure(userSettingsMeasure);
	}

	public UserRequestMeasure fetchProcessedUserRequestMeasureForUser(String userId) {
		return dbManager.fetchProcessedUserRequestMeasureForUser(userId);
	}

	public UserSettingsMeasure fetchUserSettingsMeasureForUser(String userId) {
		return dbManager.fetchUserSettingsMeasuresByUserId(userId);
	}

	public List<String> fetchOrderedServiceTypeCodesListAsUserPreferrence(String userId) {
		return dbManager.fetchOrderedServiceTypeCodesListAsUserPreferrence(userId);
	}
	
	public List<RequestProcessorDimension> fetchAllRequestProcessor(){
		return dbManager.fetchAllRequestProcessor();
	}

	public void addServiceTypeInReportDBandCache(ServiceType serviceType) {
		dbManager.insertOrUpdateServiceType(serviceType);
		DataStorage.addServiceTypeInCache(serviceType);
		
	}

	public void insertSubCategoryDimension(SubCategoryDimension subCategoryDimension) {
		dbManager.insertOrUpdateSubCategoryDimension(subCategoryDimension);
	}

	public void insertOrUpdateRequestProcessorDimension(RequestProcessorDimension requestProcessorDimension) {
		dbManager.insertOrUpdateRequestProcessorDimension(requestProcessorDimension);
	}
}
