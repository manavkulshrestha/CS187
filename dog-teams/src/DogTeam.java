public class DogTeam {

    private LLDogNode head;

    public DogTeam(Dog dog) {
        head = new LLDogNode(dog, null);
    }

    public void printTeam() {
        LLDogNode cur = head;
        int dogNumber = 1;

        System.out.println("----------------");
        while (cur != null) {
            System.out.println(dogNumber + ". " + cur.getContents().getName() +
                    ", " + cur.getContents().getWeight());
            cur = cur.getLink();
            dogNumber += 1;
        }
    }


    public static void main(String[] args) {

        DogTeam team = new DogTeam(new Dog("dog1", 60));
        team.printTeam();
        System.out.println("weightDiff: " + team.weightDiff());

        team.insertTail(new Dog("dog0",  5));
        team.insertHead(new Dog("dog2",  90));
        team.printTeam();
        System.out.println("weightDiff: " + team.weightDiff());

        team.insertHead(new Dog("dog3",  7));
        team.insertAfter(new Dog("dog4",  100), "dog2");
        team.printTeam();

        team.insertTail(new Dog("dog10", 205));
        team.insertAfter(new Dog("dog9", 75), "dog10");

        team.printTeam();
        System.out.println("weightDiff: " + team.weightDiff());

    }

    public void insertHead(Dog dog) {
        this.head = new LLDogNode(dog, this.head);
    }

    public void insertTail(Dog dog) {
        LLDogNode iterator = this.head;

        while(iterator.getLink() != null)
            iterator = iterator.getLink();

        iterator.setLink(new LLDogNode(dog, null));
    }

    public double weightDiff() {
        double max=this.head.getContents().getWeight(), min=max;

        for(LLDogNode iterator = this.head.getLink(); iterator != null; iterator = iterator.getLink()) {
            double dogWeight = iterator.getContents().getWeight();
            max = (dogWeight > max) ? dogWeight : max;
            min = (dogWeight < min) ? dogWeight : min;
        }

        return max-min;
    }

    public void insertAfter(Dog dog, String dogName) {
        LLDogNode iterator = this.head;
        while(iterator != null) {
            if(iterator.getContents().getName().equals(dogName)) {
                iterator.setLink(new LLDogNode(dog, iterator.getLink()));
                break;
            }
            iterator = iterator.getLink();
        }
    }
}
