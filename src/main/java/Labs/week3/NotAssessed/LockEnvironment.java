package Labs.week3.NotAssessed;

public class LockEnvironment {
    public static void main(String[] args) {
        Lock myLock;
        myLock = new LockString(421, true);
        myLock.close();
        myLock.changeCombo(321, 777);
        if (myLock.open(777)) {
            System.out.println("Hey, Im in!");
        } else {
            System.out.println("Hey, Im locked out!");
        }
    }
}
