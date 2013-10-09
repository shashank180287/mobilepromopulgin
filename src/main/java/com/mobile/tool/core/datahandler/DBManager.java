package com.mobile.tool.core.datahandler;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mobile.tool.core.dao.LocationDao;
import com.mobile.tool.core.dao.ServiceTypeDao;
import com.mobile.tool.core.dao.SubCategoryDao;
import com.mobile.tool.core.dao.UserLocationInfoDao;
import com.mobile.tool.core.dao.UserSettingsDao;
import com.mobile.tool.core.entity.Location;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;
import com.mobile.tool.core.entity.UserLocationInfo;
import com.mobile.tool.core.entity.UserSettingsMeasure;
import com.mobile.tool.inventory.dao.InventoryItemDao;
import com.mobile.tool.inventory.entity.ItemMeasure;
import com.mobile.tool.promo.dao.PromoServiceDao;
import com.mobile.tool.promo.dao.UserServiceRelationDao;
import com.mobile.tool.promo.entity.PromoService;
import com.mobile.tool.promo.entity.UserServiceRelation;
import com.mobile.tool.request.intr.dao.RequestProcessorDao;
import com.mobile.tool.request.intr.dao.UserRequestMeasureDao;
import com.mobile.tool.request.intr.entity.RequestProcessorDimension;
import com.mobile.tool.request.intr.entity.UserRequestMeasure;

@Component
public class DBManager {

	@Autowired
	private ServiceTypeDao serviceTypeDao;
	@Autowired
	private PromoServiceDao promoServiceDao;
	@Autowired
	private LocationDao locationDao;
	@Autowired
	private UserServiceRelationDao userServiceRelationDao;
	@Autowired
	private UserLocationInfoDao userLocationInfoDao;

	private InventoryItemDao inventoryItemDao;
	
	@Autowired
	private SubCategoryDao subCategoryDao;
	@Autowired
	private RequestProcessorDao requestProcessorDao;
	@Autowired
	private UserRequestMeasureDao userRequestMeasureDao;
	@Autowired
	private UserSettingsDao userSettingsDao;
	
	public List<ServiceType> fetchAllServiceTypes() {
		return serviceTypeDao.fetchAll();
	}
	
	public List<PromoService> findServicesByLocationAndTime(double latitude, double longitude, double radius, Date timeTillMinute){
		return promoServiceDao.findServicesByLocationTime(latitude, longitude, radius);
	}
	
	public List<PromoService> findServicesByLocationTimeAndUserPreference(double latitude, double longitude, double radius, List<String> userPrefferedServiceTypes){
		return promoServiceDao.findServicesByLocationTimeAndUserPreference(latitude, longitude, radius, userPrefferedServiceTypes);
	}

	public void updateUserLocation(String userId, Location currentLocation) {
		UserLocationInfo userLocationInfo = new UserLocationInfo();
		userLocationInfo.setUserId(userId);
		userLocationInfo.setLocation(currentLocation);
		userLocationInfo.setVisitedTs(new Timestamp(new Date().getTime()));
		userLocationInfoDao.insertOrUpdate(userLocationInfo);
	}

	public void insertOrUpdateLocation(Location location) {
		locationDao.insertOrUpdateLocation(location);
	}

	public void insertOrUpdateUserServiceRelation(UserServiceRelation userServiceRelation) {
		userServiceRelationDao.insertOrUpdate(userServiceRelation);
	}

	public List<ItemMeasure> fetchItemsByCategoryAndSubCategoryName(String serviceTypeName, String subCategoryName) {
		return inventoryItemDao.searchItemsByServiceTypeAndSubCategory(serviceTypeName, subCategoryName);
	}

	public SubCategoryDimension fetchSubCategoryByServiceTypeAndSubCategoryName(ServiceType serviceType, String subCategoryName) {
		return subCategoryDao.fetchSubCategoryByServiceTypeCodeAndSubCategoryName(serviceType.getCode(), subCategoryName);
	}

	public void addAllItemMeasures(List<ItemMeasure> itemMeasures) {
		inventoryItemDao.addAll(itemMeasures);
	}

	public RequestProcessorDimension fetchRequestProcessorByRequestType(String requestTypeName) {
		return requestProcessorDao.fetchRequestProcessorByRequestType(requestTypeName);
	}

	public void insertOrUpdateUserRequestMeasure(UserRequestMeasure userRequestMeasure) {
		userRequestMeasureDao.insertOrUpdate(userRequestMeasure);
	}

	public List<UserRequestMeasure> fetchAllUserRequestMeasureForProcessing() {
		return userRequestMeasureDao.fetchAllUserRequestsForProcessing();
	}

	public List<SubCategoryDimension> fetchAllSubCategoriesForService(String serviceTypeCode) {
		return subCategoryDao.fetchAllSubCategoriesByServiceTypeCode(serviceTypeCode);
	}

	public ItemMeasure fetchItemsByitemCode(String itemCode) {
		return inventoryItemDao.fetchItemsByitemCode(itemCode);
	}

	public void insertOrUpdateItemMeasure(ItemMeasure itemMeasure) {
		inventoryItemDao.insertOrUpdate(itemMeasure);
	}

	public void deleteItemMeasure(ItemMeasure itemMeasure) {
		inventoryItemDao.delete(itemMeasure);
	}

	public UserSettingsMeasure fetchUserSettingsMeasuresByUserId(String userId){
		return userSettingsDao.fetchUserSettingsForUser(userId);
	}

	public void insertOrUpdateUserSettingMeasure(UserSettingsMeasure userSettingsMeasure) {
		userSettingsDao.insertOrUpdate(userSettingsMeasure);
	}

	public UserRequestMeasure fetchProcessedUserRequestMeasureForUser(String userId) {
		return userRequestMeasureDao.fetchProcessedUserRequestMeasureByUserId(userId);
	}

	public List<String> fetchOrderedServiceTypeCodesListAsUserPreferrence(String userId) {
		return userServiceRelationDao.fetchOrderedServiceTypeCodesListByUserId(userId);
	}

	public void setInventoryItemDao(InventoryItemDao inventoryItemDao) {
		this.inventoryItemDao = inventoryItemDao;
	}
	
	public List<RequestProcessorDimension> fetchAllRequestProcessor(){
		return requestProcessorDao.fetchAllRequestProcessor();
	}

	public void insertOrUpdateServiceType(ServiceType serviceType) {
		serviceTypeDao.insertOrUpdate(serviceType);
	}

	public void insertOrUpdateSubCategoryDimension(SubCategoryDimension subCategoryDimension) {
		subCategoryDao.insertOrUpdate(subCategoryDimension);
	}

	public void insertOrUpdateRequestProcessorDimension(RequestProcessorDimension requestProcessorDimension) {
		requestProcessorDao.insertOrUpdate(requestProcessorDimension);
	}
}
