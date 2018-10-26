package Lesson1.Marathon;

public class MainCross {
    public static void main(String[] args) {
            Course c = new Course();
            Team team = new Team("Троеборцы");
            c.passCourse(team);
            team.membersPassedDistance();
    }
}
