//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

public interface Combination {
    int getInt();

    String getString();

    void setInt(int var1) throws OutOfBounds;

    void setString(String var1) throws OutOfBounds, NumberFormatException;

    boolean equals(Combination var1);

    String toString();
}
