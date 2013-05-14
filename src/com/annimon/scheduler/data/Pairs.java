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
    //private Groups group;
    private Integer professorId;
    //private Professors professor;
    private Integer subjectId;
    //private Subjects subject;
    private Integer audienceId;
    //private Audiences audience;
    
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    /*public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }*/

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }
    
    /*public Professors getProfessor() {
        return professor;
    }

    public void setProfessor(Professors professor) {
        this.professor = professor;
    }*/
    
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    
    /*public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }*/

    public Integer getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(Integer audienceId) {
        this.audienceId = audienceId;
    }
    
    /*public Audiences getAudience() {
    return audience;
    }
    public void setAudience(Audiences audience) {
    this.audience = audience;
    }*/
    @Override
    public String toString() {
        return "Pairs{" + "number=" + number + ", timeBegin=" + timeBegin + ", timeEnd=" + timeEnd + ", day=" + day + ", week=" + week + ", groupId=" + groupId + ", professorId=" + professorId + ", subjectId=" + subjectId + ", audienceId=" + audienceId + '}';
    }
    
}
