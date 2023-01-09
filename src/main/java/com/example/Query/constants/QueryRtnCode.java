package com.example.Query.constants;

public enum QueryRtnCode {
	SUCCESSFUL("200", "���\ !!"), 
	CAPTION_EMPTY("400", "�п�J�ݨ��W��!!"),
	CONTENT_EMPTY("400", "�ж�J�ݨ��y�z"),
	EXISTED("400", "�ݨ��w�g�s�b!!"),
	DELETE_SUCCESSFUL("200", "�R�����\ !!"), 
	DELETE_CAPTION_EMPTY("400", "�п�J�n�R�����ݨ��D��!!"),
	DELETE_NOT_EXIST("400","�w���ۦP�ݨ��W�٦s�b"),
	CANCEL_REVISE("400","�����ק�"),
	REVISE_FAILED("400","�ק異��"),
	FAILD("400","�п�J������"),
	SEARCH_SUCCESSFUL("200","��Ƭd�ߦ��\"),
	SEARCH_FAILD("400","�d�ߥ��ѡA�нT�{��������ܵ��ƬO�_�W�L10��"),
	SEARCH_NOT_EXIST("400","�d�ߥ��ѡA�S���ŦX���ݨ�"),
	SAVE_SUCCESSFUL("200", "����x�s���\"),
	ERROR("400","�٦��D�إ����A�Ч@��")
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
