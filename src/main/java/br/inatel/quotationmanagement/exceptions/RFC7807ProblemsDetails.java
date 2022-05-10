package br.inatel.quotationmanagement.exceptions;

public class RFC7807ProblemsDetails {
    private String type = "about:blank";
    private String title;
    private int status;
    private String detail;
    private String instance = "about:blank";

    public RFC7807ProblemsDetails(String title, int status, String detail) {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    public RFC7807ProblemsDetails(String type, String title, int status, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
