package bike;
public class User {
    private static int userId; //ห้ามเปลี่ยน
    private static String officeId; //ห้ามเปลี่ยน
    private static String firstName;
    private static String lastName;
    private static String gender;
    private static String birthDate;
    private static String congenitialDisease;
    private static String email;
    private static String tel;
    private static String dept;
    private static String deptId;
    private static String positon;
    private static String imgPath;

    public static void setUserId(int userId) {
        User.userId = userId;
    }
    public static void setOfficeId(String officeId) {
        User.officeId = officeId;
    }
    public static void setFirstName(String firstName) {
        User.firstName = firstName;
    }
    public static void setLastName(String lastName) {
        User.lastName = lastName;
    }
    public static void setGender(String gender) {
        User.gender = gender;
    }
    public static void setBirthDate(String birthDate) {
        User.birthDate = birthDate;
    }
    public static void setCongenitialDisease(String congenitialDisease) {
        User.congenitialDisease = congenitialDisease;
    }
    public static void setEmail(String email) {
        User.email = email;
    }
    public static void setTel(String tel) {
        User.tel = tel;
    }
    public static void setDept(String dept) {
        User.dept = dept;
    }
    public static void setDeptId(String deptId) {
        User.deptId = deptId;
    }
    public static void setPositon(String positon) {
        User.positon = positon;
    }
    public static void setImgPath(String imgPath) {
        User.imgPath = imgPath;
    }
    public static int getUserId() {
        return userId;
    }
    public static String getOfficeId() {
        return officeId;
    }
    public static String getFirstName() {
        return firstName;
    }
    public static String getLastName() {
        return lastName;
    }
    public static String getGender() {
        return gender;
    }
    public static String getBirthDate() {
        return birthDate;
    }
    public static String getCongenitialDisease() {
        return congenitialDisease;
    }
    public static String getEmail() {
        return email;
    }
    public static String getTel() {
        return tel;
    }
    public static String getDept() {
        return dept;
    }
    public static String getDeptId() {
        return deptId;
    }
    public static String getPositon() {
        return positon;
    }
    public static String getImgPath() {
        return imgPath;
    }
}
