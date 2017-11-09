/**
 * 
 */
package br.umc.sgmed.response;

/**
 * @author Isaque Pestana
 *
 */
public class Response {
	private String status;
	private Object data;

	/**
	 * @param status
	 * @param data
	 */
	public Response(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
