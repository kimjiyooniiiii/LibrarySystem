package com.exclaimation.librarysystem.controller;

public class MemberForm {
    private String id;
    private String pw;

    public String getName() {
        return id;
    }

    public void setName(String id) {
        this.id = id;
    }
    public String getPw() { return pw; }
    public void setPw(String pw) { this.pw = pw; }
}
