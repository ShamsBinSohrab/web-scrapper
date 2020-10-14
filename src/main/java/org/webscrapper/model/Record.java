package org.webscrapper.model;

import static org.webscrapper.model.DataColumn.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

public class Record {

  private String recordNo;
  private String policyDate;
  private String policyNo;
  private String medicalCardNo;
  private String firstName;
  private String lastName;
  private String city;
  private String state;
  private String phone;
  private String martialStatus;
  private String gpCode;
  private String hospitalClaimDays;
  private String paidAmount;
  private String netAmount;

  public static Record recordFromRow(Row row) {
    try {
      final Record data = new Record();
      data.recordNo = getCellValue(row, RECORD_NO);
      data.policyDate = getCellValue(row, POLICY_DATE);
      data.policyNo = getCellValue(row, POLICY_NO);
      data.medicalCardNo = getCellValue(row, MEDICAL_CARD_NO);
      data.firstName = getCellValue(row, FIRST_NAME);
      data.lastName = getCellValue(row, LAST_NAME);
      data.city = getCellValue(row, CITY);
      data.state = getCellValue(row, STATE);
      data.phone = getCellValue(row, PHONE);
      data.martialStatus = getCellValue(row, MARTIAL_STATUS);
      data.gpCode = getCellValue(row, GP_CODE);
      data.hospitalClaimDays = getCellValue(row, HOSPITAL_CLAIM_DAYS);
      data.paidAmount = getCellValue(row, PAID_AMOUNT);
      data.netAmount = getCellValue(row, NET_AMOUNT);
      return data;
    } catch (RuntimeException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }

  private static String getCellValue(Row row, DataColumn column) {
    switch (row.getCell(column.ordinal()).getCellType()) {
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(row.getCell(column.ordinal()))) {
          return row.getCell(column.ordinal()).getLocalDateTimeCellValue().toLocalDate().toString();
        }
        return String.valueOf(row.getCell(column.ordinal()).getNumericCellValue());
      case STRING:
        return row.getCell(column.ordinal()).getStringCellValue();
      default:
        return "";
    }
  }

  public List<Object> print() {
    return Arrays.asList(recordNo, policyDate, policyNo, medicalCardNo, firstName, lastName, city, state, phone,
        martialStatus, gpCode, hospitalClaimDays, paidAmount, netAmount);
  }

  public String getRecordNo() {
    return recordNo;
  }

  public String getPolicyDate() {
    return policyDate;
  }

  public String getPolicyNo() {
    return policyNo;
  }

  public String getMedicalCardNo() {
    return medicalCardNo;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPhone() {
    return phone;
  }

  public String getMartialStatus() {
    return martialStatus;
  }

  public String getGpCode() {
    return gpCode;
  }

  public String getHospitalClaimDays() {
    return hospitalClaimDays;
  }

  public String getPaidAmount() {
    return paidAmount;
  }

  public String getNetAmount() {
    return netAmount;
  }
}
