
package com.oup.integration.sps.acad.titlemaster.biblio.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"productISBN",
"statusLevel",
"fullTitle",
"subTitle",
"editionNumber",
"noOfVolumes",
"newInPaperbackFlag",
"formatDescription",
"oxfordSubjectCode",
"referenceSubject",
"additionalReferenceSubject",
"onlineProductSubjectCode",
"onlineProductSubjectDescription",
"audienceCode",
"audienceDescription",
"editorName",
"printRun",
"authorBigraphy",
"copyrightYear",
"seriesCode",
"seriesName",
"readyForPDBMAIN",
"previousEditionPubDate1",
"previousEditionPubDate2",
"previousEditionPubDate3",
"institutionalAccess",
"retailMarketApproval",
"previewOrSearch",
"workID",
"productRelationCode",
"memberOfOnlineProductCode"
})
public class StarTitleMasterDataBiblioUS81 {

/**
* Product ISBN
* <p>
* 
* 
*/
@JsonProperty("productISBN")
private String productISBN = "";
/**
* statusLevel
* <p>
* 
* 
*/
@JsonProperty("statusLevel")
private Double statusLevel;
/**
* fullTitle
* <p>
* 
* 
*/
@JsonProperty("fullTitle")
private String fullTitle = "";
/**
* subTitle
* <p>
* 
* 
*/
@JsonProperty("subTitle")
private String subTitle = "";
/**
* editionNumber
* <p>
* 
* 
*/
@JsonProperty("editionNumber")
private String editionNumber;
/**
* noOfVolumes
* <p>
* 
* 
*/
@JsonProperty("noOfVolumes")
private Double noOfVolumes;
/**
* newInPaperbackFlag
* <p>
* 
* 
*/
@JsonProperty("newInPaperbackFlag")
private Boolean newInPaperbackFlag;
/**
* formatDescription
* <p>
* 
* 
*/
@JsonProperty("formatDescription")
private String formatDescription = "";
/**
* oxfordSubjectCode
* <p>
* 
* 
*/
@JsonProperty("oxfordSubjectCode")
private String oxfordSubjectCode = "";
/**
* referenceSubject
* <p>
* 
* 
*/
@JsonProperty("referenceSubject")
private String referenceSubject = "";
/**
* additionalReferenceSubject
* <p>
* 
* 
*/
@JsonProperty("additionalReferenceSubject")
private String additionalReferenceSubject = "";
/**
* onlineProductSubjectCode
* <p>
* 
* 
*/
@JsonProperty("onlineProductSubjectCode")
private String onlineProductSubjectCode = "";
/**
* onlineProductSubjectDescription
* <p>
* 
* 
*/
@JsonProperty("onlineProductSubjectDescription")
private String onlineProductSubjectDescription = "";
/**
* audienceCode
* <p>
* 
* 
*/
@JsonProperty("audienceCode")
private String audienceCode = "";
/**
* audienceDescription
* <p>
* 
* 
*/
@JsonProperty("audienceDescription")
private String audienceDescription = "";
/**
* editorName
* <p>
* 
* 
*/
@JsonProperty("editorName")
private String editorName = "";
/**
* printRun
* <p>
* 
* 
*/
@JsonProperty("printRun")
private Double printRun;
/**
* authorBigraphy
* <p>
* 
* 
*/
@JsonProperty("authorBigraphy")
private String authorBigraphy = "";
/**
* copyrightYear
* <p>
* 
* 
*/
@JsonProperty("copyrightYear")
private Double copyrightYear;
/**
* seriesCode
* <p>
* 
* 
*/
@JsonProperty("seriesCode")
private String seriesCode = "";
/**
* seriesName
* <p>
* 
* 
*/
@JsonProperty("seriesName")
private String seriesName = "";
/**
* readyForPDBMAIN
* <p>
* 
* 
*/
@JsonProperty("readyForPDBMAIN")
private Boolean readyForPDBMAIN;
/**
* previousEditionPubDate1 : MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate1")
private String previousEditionPubDate1 = "";
/**
* previousEditionPubDate2: MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate2")
private String previousEditionPubDate2 = "";
/**
* previousEditionPubDate3: MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate3")
private String previousEditionPubDate3 = "";
/**
* institutionalAccess
* <p>
* 
* 
*/
@JsonProperty("institutionalAccess")
private String institutionalAccess = "";
/**
* retailMarketApproval
* <p>
* 
* 
*/
@JsonProperty("retailMarketApproval")
private String retailMarketApproval;
/**
* previewOrSearch
* <p>
* 
* 
*/
@JsonProperty("previewOrSearch")
private String previewOrSearch;
/**
* workID
* <p>
* 
* 
*/
@JsonProperty("workID")
private String workID = "";
/**
* productRelationCode
* <p>
* 
* 
*/
@JsonProperty("productRelationCode")
private String productRelationCode = "";
/**
* memberOfOnlineProductCode
* <p>
* 
* 
*/
@JsonProperty("memberOfOnlineProductCode")
private String memberOfOnlineProductCode = "";
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* Product ISBN
* <p>
* 
* 
*/
@JsonProperty("productISBN")
public String getProductISBN() {
return productISBN;
}

/**
* Product ISBN
* <p>
* 
* 
*/
@JsonProperty("productISBN")
public void setProductISBN(String productISBN) {
this.productISBN = productISBN;
}

/**
* statusLevel
* <p>
* 
* 
*/
@JsonProperty("statusLevel")
public Double getStatusLevel() {
return statusLevel;
}

/**
* statusLevel
* <p>
* 
* 
*/
@JsonProperty("statusLevel")
public void setStatusLevel(Double statusLevel) {
this.statusLevel = statusLevel;
}

/**
* fullTitle
* <p>
* 
* 
*/
@JsonProperty("fullTitle")
public String getFullTitle() {
return fullTitle;
}

/**
* fullTitle
* <p>
* 
* 
*/
@JsonProperty("fullTitle")
public void setFullTitle(String fullTitle) {
this.fullTitle = fullTitle;
}

/**
* subTitle
* <p>
* 
* 
*/
@JsonProperty("subTitle")
public String getSubTitle() {
return subTitle;
}

/**
* subTitle
* <p>
* 
* 
*/
@JsonProperty("subTitle")
public void setSubTitle(String subTitle) {
this.subTitle = subTitle;
}

/**
* editionNumber
* <p>
* 
* 
*/
@JsonProperty("editionNumber")
public String getEditionNumber() {
return editionNumber;
}

/**
* editionNumber
* <p>
* 
* 
*/
@JsonProperty("editionNumber")
public void setEditionNumber(String editionNumber) {
this.editionNumber = editionNumber;
}

/**
* noOfVolumes
* <p>
* 
* 
*/
@JsonProperty("noOfVolumes")
public Double getNoOfVolumes() {
return noOfVolumes;
}

/**
* noOfVolumes
* <p>
* 
* 
*/
@JsonProperty("noOfVolumes")
public void setNoOfVolumes(Double noOfVolumes) {
this.noOfVolumes = noOfVolumes;
}

/**
* newInPaperbackFlag
* <p>
* 
* 
*/
@JsonProperty("newInPaperbackFlag")
public Boolean getNewInPaperbackFlag() {
return newInPaperbackFlag;
}

/**
* newInPaperbackFlag
* <p>
* 
* 
*/
@JsonProperty("newInPaperbackFlag")
public void setNewInPaperbackFlag(Boolean newInPaperbackFlag) {
this.newInPaperbackFlag = newInPaperbackFlag;
}

/**
* formatDescription
* <p>
* 
* 
*/
@JsonProperty("formatDescription")
public String getFormatDescription() {
return formatDescription;
}

/**
* formatDescription
* <p>
* 
* 
*/
@JsonProperty("formatDescription")
public void setFormatDescription(String formatDescription) {
this.formatDescription = formatDescription;
}

/**
* oxfordSubjectCode
* <p>
* 
* 
*/
@JsonProperty("oxfordSubjectCode")
public String getOxfordSubjectCode() {
return oxfordSubjectCode;
}

/**
* oxfordSubjectCode
* <p>
* 
* 
*/
@JsonProperty("oxfordSubjectCode")
public void setOxfordSubjectCode(String oxfordSubjectCode) {
this.oxfordSubjectCode = oxfordSubjectCode;
}

/**
* referenceSubject
* <p>
* 
* 
*/
@JsonProperty("referenceSubject")
public String getReferenceSubject() {
return referenceSubject;
}

/**
* referenceSubject
* <p>
* 
* 
*/
@JsonProperty("referenceSubject")
public void setReferenceSubject(String referenceSubject) {
this.referenceSubject = referenceSubject;
}

/**
* additionalReferenceSubject
* <p>
* 
* 
*/
@JsonProperty("additionalReferenceSubject")
public String getAdditionalReferenceSubject() {
return additionalReferenceSubject;
}

/**
* additionalReferenceSubject
* <p>
* 
* 
*/
@JsonProperty("additionalReferenceSubject")
public void setAdditionalReferenceSubject(String additionalReferenceSubject) {
this.additionalReferenceSubject = additionalReferenceSubject;
}

/**
* onlineProductSubjectCode
* <p>
* 
* 
*/
@JsonProperty("onlineProductSubjectCode")
public String getOnlineProductSubjectCode() {
return onlineProductSubjectCode;
}

/**
* onlineProductSubjectCode
* <p>
* 
* 
*/
@JsonProperty("onlineProductSubjectCode")
public void setOnlineProductSubjectCode(String onlineProductSubjectCode) {
this.onlineProductSubjectCode = onlineProductSubjectCode;
}

/**
* onlineProductSubjectDescription
* <p>
* 
* 
*/
@JsonProperty("onlineProductSubjectDescription")
public String getOnlineProductSubjectDescription() {
return onlineProductSubjectDescription;
}

/**
* onlineProductSubjectDescription
* <p>
* 
* 
*/
@JsonProperty("onlineProductSubjectDescription")
public void setOnlineProductSubjectDescription(String onlineProductSubjectDescription) {
this.onlineProductSubjectDescription = onlineProductSubjectDescription;
}

/**
* audienceCode
* <p>
* 
* 
*/
@JsonProperty("audienceCode")
public String getAudienceCode() {
return audienceCode;
}

/**
* audienceCode
* <p>
* 
* 
*/
@JsonProperty("audienceCode")
public void setAudienceCode(String audienceCode) {
this.audienceCode = audienceCode;
}

/**
* audienceDescription
* <p>
* 
* 
*/
@JsonProperty("audienceDescription")
public String getAudienceDescription() {
return audienceDescription;
}

/**
* audienceDescription
* <p>
* 
* 
*/
@JsonProperty("audienceDescription")
public void setAudienceDescription(String audienceDescription) {
this.audienceDescription = audienceDescription;
}

/**
* editorName
* <p>
* 
* 
*/
@JsonProperty("editorName")
public String getEditorName() {
return editorName;
}

/**
* editorName
* <p>
* 
* 
*/
@JsonProperty("editorName")
public void setEditorName(String editorName) {
this.editorName = editorName;
}

/**
* printRun
* <p>
* 
* 
*/
@JsonProperty("printRun")
public Double getPrintRun() {
return printRun;
}

/**
* printRun
* <p>
* 
* 
*/
@JsonProperty("printRun")
public void setPrintRun(Double printRun) {
this.printRun = printRun;
}

/**
* authorBigraphy
* <p>
* 
* 
*/
@JsonProperty("authorBigraphy")
public String getAuthorBigraphy() {
return authorBigraphy;
}

/**
* authorBigraphy
* <p>
* 
* 
*/
@JsonProperty("authorBigraphy")
public void setAuthorBigraphy(String authorBigraphy) {
this.authorBigraphy = authorBigraphy;
}

/**
* copyrightYear
* <p>
* 
* 
*/
@JsonProperty("copyrightYear")
public Double getCopyrightYear() {
return copyrightYear;
}

/**
* copyrightYear
* <p>
* 
* 
*/
@JsonProperty("copyrightYear")
public void setCopyrightYear(Double copyrightYear) {
this.copyrightYear = copyrightYear;
}

/**
* seriesCode
* <p>
* 
* 
*/
@JsonProperty("seriesCode")
public String getSeriesCode() {
return seriesCode;
}

/**
* seriesCode
* <p>
* 
* 
*/
@JsonProperty("seriesCode")
public void setSeriesCode(String seriesCode) {
this.seriesCode = seriesCode;
}

/**
* seriesName
* <p>
* 
* 
*/
@JsonProperty("seriesName")
public String getSeriesName() {
return seriesName;
}

/**
* seriesName
* <p>
* 
* 
*/
@JsonProperty("seriesName")
public void setSeriesName(String seriesName) {
this.seriesName = seriesName;
}

/**
* readyForPDBMAIN
* <p>
* 
* 
*/
@JsonProperty("readyForPDBMAIN")
public Boolean getReadyForPDBMAIN() {
return readyForPDBMAIN;
}

/**
* readyForPDBMAIN
* <p>
* 
* 
*/
@JsonProperty("readyForPDBMAIN")
public void setReadyForPDBMAIN(Boolean readyForPDBMAIN) {
this.readyForPDBMAIN = readyForPDBMAIN;
}

/**
* previousEditionPubDate1 : MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate1")
public String getPreviousEditionPubDate1() {
return previousEditionPubDate1;
}

/**
* previousEditionPubDate1 : MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate1")
public void setPreviousEditionPubDate1(String previousEditionPubDate1) {
this.previousEditionPubDate1 = previousEditionPubDate1;
}

/**
* previousEditionPubDate2: MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate2")
public String getPreviousEditionPubDate2() {
return previousEditionPubDate2;
}

/**
* previousEditionPubDate2: MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate2")
public void setPreviousEditionPubDate2(String previousEditionPubDate2) {
this.previousEditionPubDate2 = previousEditionPubDate2;
}

/**
* previousEditionPubDate3: MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate3")
public String getPreviousEditionPubDate3() {
return previousEditionPubDate3;
}

/**
* previousEditionPubDate3: MM/DD/YYYY
* <p>
* 
* 
*/
@JsonProperty("previousEditionPubDate3")
public void setPreviousEditionPubDate3(String previousEditionPubDate3) {
this.previousEditionPubDate3 = previousEditionPubDate3;
}

/**
* institutionalAccess
* <p>
* 
* 
*/
@JsonProperty("institutionalAccess")
public String getInstitutionalAccess() {
return institutionalAccess;
}

/**
* institutionalAccess
* <p>
* 
* 
*/
@JsonProperty("institutionalAccess")
public void setInstitutionalAccess(String institutionalAccess) {
this.institutionalAccess = institutionalAccess;
}

/**
* retailMarketApproval
* <p>
* 
* 
*/
@JsonProperty("retailMarketApproval")
public String getRetailMarketApproval() {
return retailMarketApproval;
}

/**
* retailMarketApproval
* <p>
* 
* 
*/
@JsonProperty("retailMarketApproval")
public void setRetailMarketApproval(String retailMarketApproval) {
this.retailMarketApproval = retailMarketApproval;
}

/**
* previewOrSearch
* <p>
* 
* 
*/
@JsonProperty("previewOrSearch")
public String getPreviewOrSearch() {
return previewOrSearch;
}

/**
* previewOrSearch
* <p>
* 
* 
*/
@JsonProperty("previewOrSearch")
public void setPreviewOrSearch(String previewOrSearch) {
this.previewOrSearch = previewOrSearch;
}

/**
* workID
* <p>
* 
* 
*/
@JsonProperty("workID")
public String getWorkID() {
return workID;
}

/**
* workID
* <p>
* 
* 
*/
@JsonProperty("workID")
public void setWorkID(String workID) {
this.workID = workID;
}

/**
* productRelationCode
* <p>
* 
* 
*/
@JsonProperty("productRelationCode")
public String getProductRelationCode() {
return productRelationCode;
}

/**
* productRelationCode
* <p>
* 
* 
*/
@JsonProperty("productRelationCode")
public void setProductRelationCode(String productRelationCode) {
this.productRelationCode = productRelationCode;
}

/**
* memberOfOnlineProductCode
* <p>
* 
* 
*/
@JsonProperty("memberOfOnlineProductCode")
public String getMemberOfOnlineProductCode() {
return memberOfOnlineProductCode;
}

/**
* memberOfOnlineProductCode
* <p>
* 
* 
*/
@JsonProperty("memberOfOnlineProductCode")
public void setMemberOfOnlineProductCode(String memberOfOnlineProductCode) {
this.memberOfOnlineProductCode = memberOfOnlineProductCode;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}