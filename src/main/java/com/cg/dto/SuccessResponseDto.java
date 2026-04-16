package com.cg.dto;

public class SuccessResponseDto {
	private String msg;

	private Integer id;

	public SuccessResponseDto(String msg) {
		this.msg = msg;
	}

	public SuccessResponseDto(String msg, Integer id) {
		super();
		this.msg = msg;
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
