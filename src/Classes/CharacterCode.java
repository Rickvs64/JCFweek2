package Classes;

import java.util.BitSet;

public class CharacterCode {
    private BitSet code;
    private byte codeSize;

    public CharacterCode(BitSet code, byte codeSize) {
        this.code = (BitSet)code.clone();
        this.codeSize = codeSize;
    }

    public BitSet getCode() {
        return code;
    }

    public byte getCodeSize() {
        return codeSize;
    }
}
