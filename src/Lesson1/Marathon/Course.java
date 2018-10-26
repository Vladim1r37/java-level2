package Lesson1.Marathon;

public class Course {

    Barrier[] barriers;

    public Course() {
        this.barriers = new Barrier[3];
        this.barriers[0] = new Cross(100);
        this.barriers[1] = new Wall(10);
        this.barriers[2] = new Water(3);
    }

    public void passCourse(Team team) {
        for (Competitor member : team.getTeam()) {
            for (Barrier barrier : barriers) {
                barrier.doIt(member);
            }
        }
    }
}
