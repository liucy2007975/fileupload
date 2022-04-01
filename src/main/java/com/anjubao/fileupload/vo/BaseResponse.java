package com.anjubao.fileupload.vo;

import java.io.Serializable;

/**
 * 基础返回对象
 * 
 * @author william
 *
 */
public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = 7620544582929442162L;

	private int result;// 0 - 表示请求成功；非0 - 表示出现错误或异常；
	private String message = "";
	private T data;
    private long total = 0;	

	/**
	 * 设置响应的错误信息
	 * 
	 * @param result
	 *            错误信息代码
	 * @param errMsg
	 *            错误信息提示
	 */
	public void setError(int result, String errMsg) {
		this.result = result;
		this.message = errMsg;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
