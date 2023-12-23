package com.collaboration.collaboration.model;

public class Pair {
    private int empId1;
    private int empId2;
    private int projectId;
    private long daysWorked;

    public Pair() {
    }

    public Pair(int empId1, int empId2, int projectId, long daysWorked) {
        this.empId1 = empId1;
        this.empId2 = empId2;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public int getEmpId1() {
        return empId1;
    }

    public void setEmpId1(int empId1) {
        this.empId1 = empId1;
    }

    public int getEmpId2() {
        return empId2;
    }

    public void setEmpId2(int empId2) {
        this.empId2 = empId2;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public long getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(long daysWorked) {
        this.daysWorked = daysWorked;
    }



    @Override
    public String toString() {
        return String.format("Project %d: Employees %d and %d worked together for %d days",
                projectId, empId1, empId2, daysWorked);
    }
}
