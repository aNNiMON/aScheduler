package com.annimon.scheduler.data;

import java.util.Date;

/**
 * Занятия (пары).
 * @author aNNiMON
 */
public class Pairs extends Entity {

    private short number;
    private Date timeBegin;
    private Date timeEnd;
    private short day;
    private Short week;
    private Integer groupId;
    private Integer professorId;
    private Integer subjectId;
    private Integer audienceId;
    
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
    
    public void setWeek(short week, boolean wasNull) {
        if (wasNull) this.week = null;
        else this.week = week;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }
    
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    
    public Integer getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(Integer audienceId) {
        this.audienceId = audienceId;
    }
    
    @Override
    public String toString() {
        return "Pairs{" + "number=" + number + ", timeBegin=" + timeBegin + ", timeEnd=" + timeEnd + ", day=" + day + ", week=" + week + ", groupId=" + groupId + ", professorId=" + professorId + ", subjectId=" + subjectId + ", audienceId=" + audienceId + '}';
    }

}
