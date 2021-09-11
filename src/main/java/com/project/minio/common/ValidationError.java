package com.project.minio.common;

public class ValidationError {
  private String errorMsg;
  private String fieldName;
  private String objectName;
  private String targetValue;

  public ValidationError() {}

  public ValidationError(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public ValidationError(String errorMsg, String fieldName, String objectName, String targetValue) {
    this.errorMsg = errorMsg;
    this.fieldName = fieldName;
    this.objectName = objectName;
    this.targetValue = targetValue;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getObjectName() {
    return objectName;
  }

  public void setObjectName(String objectName) {
    this.objectName = objectName;
  }

  public String getTargetValue() {
    return targetValue;
  }

  public void setTargetValue(String targetValue) {
    this.targetValue = targetValue;
  }

  @Override
  public String toString() {
    return "ValidationError{"
        + "errorMsg='"
        + errorMsg
        + '\''
        + ", fieldName='"
        + fieldName
        + '\''
        + ", objectName='"
        + objectName
        + '\''
        + ", targetValue='"
        + targetValue
        + '\''
        + '}';
  }
}
