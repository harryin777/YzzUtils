package com.yzzutils.hub.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName Result
 * @Author yky
 * @Date 2020/12/24
 * @Version 1.0
 */
@ApiModel(value = "返回结果DTO",description = "返回结果")
public class ResultDTO {
	
	@ApiModelProperty(
			value = "请求标识",
			name = "success",
			dataType = "Boolean"
	)
	private boolean success; //是否成功
	
	@ApiModelProperty(
			value = "返回码",
			name = "code",
			dataType = "Integer"
	)
	private Integer code; //返回码
	
	@ApiModelProperty(
			value = "返回信息描述",
			name = "message",
			dataType = "String"
	)
	private String message; //返回信息
	
	@ApiModelProperty(
			value = "返回数据",
			name = "data",
			dataType = "Object"
	)
	private Object data; //返回数据
	
	
	public ResultDTO() {
	
	}
	public ResultDTO(boolean success, Integer code, String message, Object data) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public ResultDTO(boolean success, Integer code, String message) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
