package com.eNote.eNote_core.dto;

/**
 * @author Alterranius
 */
public class DepartDataDTO {
    private String completed;
    private String failed;
    private String inWork;
    private String unsigned;

    public DepartDataDTO() {
    }

    public DepartDataDTO(String completed, String failed, String inWork, String unsigned) {
        this.completed = completed;
        this.failed = failed;
        this.inWork = inWork;
        this.unsigned = unsigned;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public String getInWork() {
        return inWork;
    }

    public void setInWork(String inWork) {
        this.inWork = inWork;
    }

    public String getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(String unsigned) {
        this.unsigned = unsigned;
    }
}
