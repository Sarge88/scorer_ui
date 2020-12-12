package gkalapis.scorerui.interactor.common;

import java.util.List;

public class CommonEvent<T> {

    private int code;
    private Throwable throwable;

    private List<T> items;

    public CommonEvent() {
    }

    public CommonEvent(int code, Throwable throwable, List<T> items) {
        this.code = code;
        this.throwable = throwable;
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
