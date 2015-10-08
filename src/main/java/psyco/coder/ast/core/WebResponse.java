package psyco.coder.ast.core;

import java.io.Serializable;

/**
 * Created by peng on 15/10/8.
 */
public class WebResponse implements Serializable {

    boolean isSuccess = true;
    String errorMsg = null;
    Object response;

    public static WebResponse response(Object obj) {
        WebResponse re = new WebResponse();
        re.setResponse(obj);
        return re;
    }

    public static WebResponse error(String msg) {
        WebResponse re = new WebResponse();
        re.setIsSuccess(false);
        re.setErrorMsg(msg == null ? "" : msg);
        return re;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
