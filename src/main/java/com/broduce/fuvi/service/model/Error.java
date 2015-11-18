package com.broduce.fuvi.service.model;

import java.io.Serializable;

public class Error implements Serializable {

	public static final int NEEDLE_NOT_FOUND_ERROR = 1;
	public static final int READ_BAD_SECTOR_ERROR = 2;
	public static final int VOLUME_UNREADABLE_ERROR = 3;
	public static final int READ_GENERAL_ERROR = 4;
	public static final int WRITE_BAD_SECTOR_ERROR = 5;
	public static final int VOLUME_NOT_SUFFICIENT_ERROR = 6;
	public static final int VOLUME_UNWRITABLE_ERROR = 7;
	public static final int WRITE_GENERAL_ERROR = 8;
	public static final int HDD_WRITE_BUSY_ERROR = 9;
	public static final int NEEDLE_DATA_VERIFIED_FAILED_ERROR = 10;
	public static final int NEEDLE_DELETED_ERROR = 11;
	public static final int VOLUME_NOT_FOUND_ERROR = 12;
	public static final int NEEDLE_DATA_TRANSFER_FAILED_ERROR = 13;
	public static final int BAD_REQUEST_ERROR = 14;
	public static final int META_DB_ERROR = 15;

	public static final int UNKNOWN_ERROR = 100;

	public static final int NEEDLE_DELETED_RESPONSE_CODE = 410;
	public static final int UNKNOWN_ERROR_RESPONSE_CODE = 500;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3984767275158810688L;
	private int code;
	private String message;

	public Error() {

	}

	public Error(int code, String message) {
		setCode(code);
		setMessage(message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
