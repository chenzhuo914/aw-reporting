// Copyright 2014 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.ads.adwords.jaxws.extensions.report.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.api.ads.adwords.jaxws.extensions.report.model.csv.annotation.CsvField;
import com.google.api.ads.adwords.jaxws.extensions.report.model.csv.annotation.CsvReport;
import com.google.api.ads.adwords.lib.jaxb.v201402.ReportDefinitionReportType;

/**
 * Specific Report class for GeoPerformanceReports
 *
 * @author markbowyer@google.com (Mark R. Bowyer)
 */
@Entity
@com.googlecode.objectify.annotation.Entity
@Table(name = "AW_ReportGeoPerformance")
@CsvReport(value = ReportDefinitionReportType.GEO_PERFORMANCE_REPORT)
public class ReportGeo extends ReportBase {

  @Column(name = "ADFORMAT")
  @CsvField(value = "Ad type", reportField = "AdFormat")
  private String adFormat;

  @Column(name = "ADGROUP_ID")
  @CsvField(value = "Ad group ID", reportField = "AdGroupId")
  private Long adGroupId;

  @Column(name = "ADGROUP_NAME", length = 255)
  @CsvField(value = "Ad group", reportField = "AdGroupName")
  private String adGroupName;

  @Column(name = "ADGROUP_STATUS", length = 32)
  @CsvField(value = "Ad group state", reportField = "AdGroupStatus")
  private String adGroupStatus;

  @Column(name = "CAMPAIGN_ID")
  @CsvField(value = "Campaign ID", reportField = "CampaignId")
  private Long campaignId;

  @Column(name = "CAMPAIGN_NAME", length = 255)
  @CsvField(value = "Campaign", reportField = "CampaignName")
  private String campaignName;

  @Column(name = "CAMPAIGN_STATUS", length = 32)
  @CsvField(value = "Campaign state", reportField = "CampaignStatus")
  private String campaignStatus;

  @Column(name = "CITY_CRITERIA_ID", length = 64)
  @CsvField(value = "City", reportField = "CityCriteriaId")
  private String cityCriteriaId;

  @Column(name = "CONVERSION_TRACKER_ID", length = 64)
  @CsvField(value = "Conversion Tracker Id", reportField = "ConversionTrackerId")
  private String conversionTrackerId;

  @Column(name = "COUNTRY_CRITERIA_ID", length = 64)
  @CsvField(value = "Country/Territory", reportField = "CountryCriteriaId")
  private String countryTerritory;

  @Column(name = "IS_TARGETING_LOCATION")
  @CsvField(value = "Is Targetable", reportField = "IsTargetingLocation")
  private String isTargetable;

  @Column(name = "LOCATION_TYPE", length = 32)
  @CsvField(value = "Location type", reportField = "LocationType")
  private String locationType;

  @Column(name = "METRO_CRITERIA_ID", length = 64)
  @CsvField(value = "Metro area", reportField = "MetroCriteriaId")
  private String metroArea;

  @Column(name = "MOST_SPECIFIC_LOCATION", length = 64)
  @CsvField(value = "Most specific location", reportField = "MostSpecificCriteriaId")
  private String mostSpecificLocation;

  @Column(name = "REGION_CRITERIA_ID", length = 64)
  @CsvField(value = "Region", reportField = "RegionCriteriaId")
  private String region;

  /**
   * Hibernate needs an empty constructor
   */
  public ReportGeo() {}

  @Override
  public void setId() {
    // Generating unique id after having accountId, campaignId and date
    this.id = "";
    if (this.getAccountId() != null) {
      this.id += this.getAccountId().toString();
    }
    if (this.getCampaignId() != null) {
      this.id += "-" + this.getCampaignId().toString();
    }
    if (this.getAdGroupId() != null) {
      this.id += "-" + this.getAdGroupId().toString();
    }

    this.id += setIdDates();

    // Geo Ids
    if (this.getCountryTerritory() != null) {
      this.id += "-" + this.getCountryTerritory().toString();
    } else {
      this.id += "-";
    }
    if (this.getRegion() != null) {
      this.id += "-" + this.getRegion().toString();
    } else {
      this.id += "-";
    }
    if (this.getMetroArea() != null) {
      this.id += "-" + this.getMetroArea().toString();
    } else {
      this.id += "-";
    }
    if (this.getCityCriteriaId() != null) {
      this.id += "-" + this.getCityCriteriaId().toString();
    } else {
      this.id += "-";
    }
    if (this.getMostSpecificLocation() != null) {
      this.id += "-" + this.getMostSpecificLocation().toString();
    } else {
      this.id += "-";
    }

    // Adding extra fields for unique ID
    if (this.getAdNetwork() != null && this.getAdNetwork().length() > 0) {
      this.id += "-" + this.getAdNetwork();
    }
    if (this.getAdNetworkPartners() != null && this.getAdNetworkPartners().length() > 0) {
      this.id += "-" + this.getAdNetworkPartners();
    }
    if (this.getDevice() != null && this.getDevice().length() > 0) {
      this.id += "-" + this.getDevice();
    }
    if (this.getClickType() != null && this.getClickType().length() > 0) {
      this.id += "-" + this.getClickType();
    }
  }

  public String getAdFormat() {
    return adFormat;
  }

  public void setAdFormat(String adFormat) {
    this.adFormat = adFormat;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getMostSpecificLocation() {
    return mostSpecificLocation;
  }

  public void setMostSpecificLocation(String mostSpecificLocation) {
    this.mostSpecificLocation = mostSpecificLocation;
  }

  public String getMetroArea() {
    return metroArea;
  }

  public void setMetroArea(String metroArea) {
    this.metroArea = metroArea;
  }

  public Long getAdGroupId() {
    return adGroupId;
  }

  public void setAdGroupId(Long adGroupId) {
    this.adGroupId = adGroupId;
  }

  public String getAdGroupName() {
    return adGroupName;
  }

  public void setAdGroupName(String adGroupName) {
    this.adGroupName = adGroupName;
  }

  public String getAdGroupStatus() {
    return adGroupStatus;
  }

  public void setAdGroupStatus(String adGroupStatus) {
    this.adGroupStatus = adGroupStatus;
  }

  public String getLocationType() {
    return locationType;
  }

  public void setLocationType(String locationType) {
    this.locationType = locationType;
  }

  public String getIsTargetable() {
    return isTargetable;
  }

  public void setIsTargetable(String isTargetable) {
    this.isTargetable = isTargetable;
  }

  public String getCountryTerritory() {
    return countryTerritory;
  }

  public void setCountryTerritory(String countryTerritory) {
    this.countryTerritory = countryTerritory;
  }

  public String getConversionTrackerId() {
    return conversionTrackerId;
  }

  public void setConversionTrackerId(String conversionTrackerId) {
    this.conversionTrackerId = conversionTrackerId;
  }

  public String getCityCriteriaId() {
    return cityCriteriaId;
  }

  public void setCityCriteriaId(String cityCriteriaId) {
    this.cityCriteriaId = cityCriteriaId;
  }

  public String getCampaignStatus() {
    return campaignStatus;
  }

  public void setCampaignStatus(String campaignStatus) {
    this.campaignStatus = campaignStatus;
  }

  public String getCampaignName() {
    return campaignName;
  }

  public void setCampaignName(String campaignName) {
    this.campaignName = campaignName;
  }

  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }
}
