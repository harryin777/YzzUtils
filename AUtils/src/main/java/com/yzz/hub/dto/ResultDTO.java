package com.yzz.hub.dto;

import com.yzz.hub.vo.StatusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Result
 * @Author yky
 * @Date 2020/12/24
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
			dataType = "Map"
	)
	private Map data = new HashMap(); //返回数据
	
	public ResultDTO(boolean success, Integer code, String message) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
	}

	public static ResultDTO success(){
		ResultDTO dto = new ResultDTO();
		dto.success = true;
		dto.code = StatusCode.success;
		dto.message = "成功";
		return dto;
	}

	public static ResultDTO fail(){
		ResultDTO dto = new ResultDTO();
		dto.success = false;
		dto.code = StatusCode.fail;
		dto.message = "失败";
		return dto;
	}

	public ResultDTO success(Boolean success){
		this.setSuccess(success);
		return this;
	}

	public ResultDTO message(String message){
		this.setMessage(message);
		return this;
	}

	public ResultDTO code(Integer code){
		this.setCode(code);
		return this;
	}

	public ResultDTO data(String key, Object value){
		this.data.put(key, value);
		return this;
	}

	public ResultDTO data(Map<String, Object> map){
		this.setData(map);
		return this;
	}

}
