package com.he.train.common.exception;


public enum BusinessExceptionEnum {

    MEMBER_MOBILE_EXIST("Mobile number already registered"),
    MEMBER_MOBILE_NOT_EXIST("Please obtain the SMS verification code first"),
    MEMBER_MOBILE_CODE_ERROR("Incorrect SMS verification code"),

    BUSINESS_STATION_NAME_UNIQUE_ERROR("Station name already exists"),
    BUSINESS_TRAIN_CODE_UNIQUE_ERROR("Train code already exists"),
    BUSINESS_TRAIN_STATION_INDEX_UNIQUE_ERROR("Station sequence for the same train already exists"),
    BUSINESS_TRAIN_STATION_NAME_UNIQUE_ERROR("Station name for the same train already exists"),
    BUSINESS_TRAIN_CARRIAGE_INDEX_UNIQUE_ERROR("Carriage number for the same train already exists"),

    CONFIRM_ORDER_TICKET_COUNT_ERROR("Insufficient tickets available"),
    CONFIRM_ORDER_EXCEPTION("Server is busy, please try again later"),
    CONFIRM_ORDER_LOCK_FAIL("Too many users are trying to book tickets, please try again later"),
    CONFIRM_ORDER_FLOW_EXCEPTION("Too many users are attempting to book tickets, please try again later"),
    CONFIRM_ORDER_SK_TOKEN_FAIL("Too many users are attempting to book tickets, please try again in 5 seconds"),
            ;

    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                "} " + super.toString();
    }
}

