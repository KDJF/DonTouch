package pro.utils;

import java.io.Serializable;

public class RequestProtocol implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String req;
	private Object obj;

	public RequestProtocol() {
		// TODO Auto-generated constructor stub
		req = null;
		obj = null;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
