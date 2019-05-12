//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Labs.CITS2200;

import java.io.InputStream;
import java.io.OutputStream;

public interface Compressor {
    String compress(InputStream var1, OutputStream var2);

    String decompress(InputStream var1, OutputStream var2);
}
