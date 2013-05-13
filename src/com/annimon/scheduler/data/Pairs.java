package com.annimon.scheduler.data;

import java.util.Date;

/**
 * Занятия (пары).
 * @author aNNiMON
 */
public class Pairs extends Entity {

    private Integer id;
    private short number;
    private Date timeBegin;
    private Date timeEnd;
    private short day;
    private Short week;
    private Groups group;
    private Professors professor;
    private Subjects subject;
    private Audiences audience;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public Date getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Date timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public short getDay() {
        return day;
    }

    public void setDay(short day) {
        this.day = day;
    }

    public Short getWeek() {
        return week;
    }

    public void setWeek(Short week) {
        this.week = week;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Professors getProfessor() {
        return professor;
    }

    public void setProfessor(Professors professor) {
        this.professor = professor;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    public Audiences getAudience() {
        return audience;
    }

    public void setAudience(Audiences audience) {
        this.audience = audience;
    }
}
