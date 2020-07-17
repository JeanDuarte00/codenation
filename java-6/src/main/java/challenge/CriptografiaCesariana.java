package challenge;

public class CriptografiaCesariana implements Criptografia {


    private int alfaStart = 97;
    private int alfaEnd = 122;
    private int changeFactor;


    @Override
    public String criptografar(String texto) {
        this.erroHandler(texto);
        return this.encode(texto);
        //throw new UnsupportedOperationException("esse method nao esta implementado aainda");
    }

    @Override
    public String descriptografar(String texto) {
        this.erroHandler(texto);
        return this.decode(texto);

        //throw new UnsupportedOperationException("esse method nao esta implementado aainda");
    }

    public CriptografiaCesariana() {
        this.changeFactor = 3;
    }

    private void erroHandler (String texto) {
        if (texto == null) {
            throw new NullPointerException();
        }
        if (texto.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private String encode (String phase) {
        phase = phase.toLowerCase();
        String res = "";
        for (char c : phase.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                int ascii = getASCII(c);
                c = this.forwardChar(ascii);
            }
            res += c;
        }
        return res;
    }

    private String decode (String encoded) {
        encoded = encoded.toLowerCase();
        String res = "";
        for (char c : encoded.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                int ascii = getASCII(c);
                c = this.backwardChar(ascii);
            }
            res += c;
        }
        return res;
    }

    private int getASCII (char character) {
        return (int) character;
    }

    private char forwardChar (int ascii) {
        char newChar = (char) (ascii + this.changeFactor);
        System.out.println(getASCII(newChar) );
        if(getASCII(newChar) > alfaEnd) newChar = (char) (alfaStart + ((changeFactor - (alfaEnd - ascii) ) -1));
        return newChar;
    }

    private char backwardChar (int ascii) {
        char newChar = (char) (ascii - this.changeFactor);
        System.out.println(getASCII(newChar) );
        if(getASCII(newChar) < alfaStart) newChar = (char) (alfaEnd - ((changeFactor - (ascii - alfaStart) ) - 1));
        return newChar;
    }
}
