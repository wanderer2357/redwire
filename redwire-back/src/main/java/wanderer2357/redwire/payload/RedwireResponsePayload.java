package wanderer2357.redwire.payload;

import org.springframework.http.HttpStatus;

public class RedwireResponsePayload<T> {
	
	private HttpStatus httpStatus;
	private String log;
	private T data;
	
	public RedwireResponsePayload (HttpStatus httpStatus, String log, T data) {
		this.httpStatus = httpStatus;
		this.log = log;
		this.data = data;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
