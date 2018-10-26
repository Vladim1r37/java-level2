package Lesson1.Marathon;

public class Team {

    String name;
    Competitor[] team;

    public Team(String name) {
        this.name = name;
        this.team = new Competitor[4];
        this.team[0] = new Cat("Барсик");
        this.team[1] = new Human("Олег");
        this.team[2] = new Dog("Трезор");
        this.team[3] = new Cat("Мурзик");
    }

    public void membersPassedDistance() {
        System.out.println("Полосу препятствий прошли:");
        for (Competitor member : team) {
            if (member.isOnDistance())
                member.info();
        }
    }

    public void membersOfTeam() {
        for (Competitor member : team) {
            member.info();
        }
    }

    public Competitor[] getTeam() {
        return team;
    }
}
