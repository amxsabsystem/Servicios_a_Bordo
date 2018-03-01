package com.aeromexico.pab.web.dto;

/**
 *
 * @author Alfredo Estrada
 */
public class VersionInfo {

	private String apiVersion;	
	private String implementationVersion;
	
	private String gitSha1API;
	private String timeStampAPI;
	
	private String gitSha1Imp;
	private String timeStampImp;

    public VersionInfo() {
        this.apiVersion = "-";	
        this.implementationVersion ="-";

        this.gitSha1API = "-";
        this.timeStampAPI = "-";

        this.gitSha1Imp = "-";
        this.timeStampImp ="-";
    }
    
    /**
     * @return the apiVersion
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion the apiVersion to set
     */
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * @return the implementationVersion
     */
    public String getImplementationVersion() {
        return implementationVersion;
    }

    /**
     * @param implementationVersion the implementationVersion to set
     */
    public void setImplementationVersion(String implementationVersion) {
        this.implementationVersion = implementationVersion;
    }

    /**
     * @return the gitSha1API
     */
    public String getGitSha1API() {
        return gitSha1API;
    }

    /**
     * @param gitSha1API the gitSha1API to set
     */
    public void setGitSha1API(String gitSha1API) {
        this.gitSha1API = gitSha1API;
    }

    /**
     * @return the timeStampAPI
     */
    public String getTimeStampAPI() {
        return timeStampAPI;
    }

    /**
     * @param timeStampAPI the timeStampAPI to set
     */
    public void setTimeStampAPI(String timeStampAPI) {
        this.timeStampAPI = timeStampAPI;
    }

    /**
     * @return the gitSha1Imp
     */
    public String getGitSha1Imp() {
        return gitSha1Imp;
    }

    /**
     * @param gitSha1Imp the gitSha1Imp to set
     */
    public void setGitSha1Imp(String gitSha1Imp) {
        this.gitSha1Imp = gitSha1Imp;
    }

    /**
     * @return the timeStampImp
     */
    public String getTimeStampImp() {
        return timeStampImp;
    }

    /**
     * @param timeStampImp the timeStampImp to set
     */
    public void setTimeStampImp(String timeStampImp) {
        this.timeStampImp = timeStampImp;
    }
    
}
