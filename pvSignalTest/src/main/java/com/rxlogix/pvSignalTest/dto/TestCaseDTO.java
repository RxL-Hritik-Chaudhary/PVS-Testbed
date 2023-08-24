package com.rxlogix.pvSignalTest.dto;

public class TestCaseDTO {

	private String alertType;
	private String assignedTo;
	private String owner;
	private String products;
	private String priority;
	private String dateRangeType;
	private String dateRange;
	private String xForDateRange;
	private String startDate;
	private String endDate;
	private String versionAsOfDate;
	private String shareWith;
	private String scheduler;
	private Boolean isIntegrated = false;
	private Boolean isAdhoc;
	private String dataSource;
	private Boolean isExcludeFollowUp;
    private Boolean isDataMiningSMQ;
    private Boolean isExcludeNonValidCases;
    private Boolean isIncludeMissingCases;
    //private Boolean isApplyAlertStopList;
    private Boolean isIncludeMedicallyConfirmedCases;
    private String productType;
    private String evaluateCaseDateOn;
    private String limitCaseSeries;
    

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDateRangeType() {
		return dateRangeType;
	}

	public void setDateRangeType(String dateRangeType) {
		this.dateRangeType = dateRangeType;
	}

	public String getxForDateRange() {
		return xForDateRange;
	}

	public void setxForDateRange(String xForDateRange) {
		this.xForDateRange = xForDateRange;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getShareWith() {
		return shareWith;
	}

	public void setShareWith(String shareWith) {
		this.shareWith = shareWith;
	}

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}
	
	public Boolean getIsIntegrated() {
		return isIntegrated;
	}

	public void setIsIntegrated(Boolean isIntegrated) {
		this.isIntegrated = isIntegrated;
	}

	public void setIsAdhoc(Boolean isAdhoc) {
		this.isAdhoc = isAdhoc;
	}
	public Boolean getIsAdhoc() {
		return isAdhoc;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getDataSource() {
		return dataSource;
	}

	public Boolean getIsExcludeFollowUp() {
		return isExcludeFollowUp;
	}

	public void setIsExcludeFollowUp(Boolean isExcludeFollowUp) {
		this.isExcludeFollowUp = isExcludeFollowUp;
	}

	public Boolean getIsDataMiningSMQ() {
		return isDataMiningSMQ;
	}

	public void setIsDataMiningSMQ(Boolean isDataMiningSMQ) {
		this.isDataMiningSMQ = isDataMiningSMQ;
	}

	public Boolean getIsExcludeNonValidCases() {
		return isExcludeNonValidCases;
	}

	public void setIsExcludeNonValidCases(Boolean isExcludeNonValidCases) {
		this.isExcludeNonValidCases = isExcludeNonValidCases;
	}

	public Boolean getIsIncludeMissingCases() {
		return isIncludeMissingCases;
	}

	public void setIsIncludeMissingCases(Boolean isIncludeMissingCases) {
		this.isIncludeMissingCases = isIncludeMissingCases;
	}

	/*
	 * public Boolean getIsApplyAlertStopList() { return isApplyAlertStopList; }
	 */

	/*
	 * public void setIsApplyAlertStopList(Boolean isApplyAlertStopList) {
	 * this.isApplyAlertStopList = isApplyAlertStopList; }
	 */

	public Boolean getIsIncludeMedicallyConfirmedCases() {
		return isIncludeMedicallyConfirmedCases;
	}

	public void setIsIncludeMedicallyConfirmedCases(Boolean isIncludeMedicallyConfirmedCases) {
		this.isIncludeMedicallyConfirmedCases = isIncludeMedicallyConfirmedCases;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public String getVersionAsOfDate() {
		return versionAsOfDate;
	}

	public void setVersionAsOfDate(String versionAsOfDate) {
		this.versionAsOfDate = versionAsOfDate;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getEvaluateCaseDateOn() {
		return evaluateCaseDateOn;
	}

	public void setEvaluateCaseDateOn(String evaluateCaseDateOn) {
		this.evaluateCaseDateOn = evaluateCaseDateOn;
	}

	public String getLimitCaseSeries() {
		return limitCaseSeries;
	}

	public void setLimitCaseSeries(String limitCaseSeries) {
		this.limitCaseSeries = limitCaseSeries;
	}
	
	


}
