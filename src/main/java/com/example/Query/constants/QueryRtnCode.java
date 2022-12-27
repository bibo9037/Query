package com.example.Query.constants;

public enum QueryRtnCode {
	SUCCESSFUL("200", "成功 !!"), 
	CAPTION_EMPTY("400", "請輸入問卷名稱!!"),
	CONTENT_EMPTY("400", "請填入問卷描述"),
	EXISTED("400", "問卷已經存在!!"),
	DELETE_SUCCESSFUL("200", "刪除成功 !!"), 
	DELETE_CAPTION_EMPTY("400", "請輸入要刪除的問卷題目!!"),
	DELETE_NOT_EXIST("400","問卷不存在"),
	CANCEL_REVISE("400","取消修改"),
	REVISE_FAILED("400","修改失敗")
	;

	private String code;

	private String message;

	private QueryRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
