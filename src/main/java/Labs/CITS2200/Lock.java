//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public interface Lock {
    boolean open(Combination var1);

    boolean close();

    boolean changeCombo(Combination var1, Combination var2);
}
