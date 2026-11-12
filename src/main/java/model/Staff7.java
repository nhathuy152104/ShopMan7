package model;

public class Staff7 extends Member7 {
    private static final long serialVersionUID = 1L;
    private String staffCode;
    private String position;

    public Staff7() { super(); }

    public String getStaffCode() { return staffCode; }
    public void setStaffCode(String staffCode) { this.staffCode = staffCode; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}