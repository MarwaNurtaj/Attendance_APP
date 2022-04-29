package MK_Nurtaj.attendance_app;

public class Course {

    public Course() {
    }

    String crs_code = "";
    String crs_title = "";


    public Course(String crs_code, String crs_title) {
        this.crs_code = crs_code;
        this.crs_title = crs_title;
    }



    public String getCrs_code() {
        return crs_code;
    }

    public void setCrs_code(String crs_code) {
        this.crs_code = crs_code;
    }

    public String getCrs_title() {
        return crs_title;
    }

    public void setCrs_title(String crs_title) {
        this.crs_title = crs_title;
    }


}
