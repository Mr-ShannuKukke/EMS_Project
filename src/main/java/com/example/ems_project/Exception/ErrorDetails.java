package com.example.ems_project.Exception;

import lombok.Getter;

import java.time.LocalDateTime;

public class ErrorDetails {

    @Getter
    private LocalDateTime timeStamp;
    @Getter
    private int status;
    @Getter
    private String error;
    @Getter
    private String message;
    @Getter
    private String path;

    public ErrorDetails(LocalDateTime timeStamp, int status, String error, String message, String path){
        this.timeStamp=timeStamp;
        this.status=status;
        this.error=error;
        this.message=message;
        this.path=path;
    }

}
