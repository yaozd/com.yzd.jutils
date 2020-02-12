package com.yzd.jutils.algorithmExt.longmapper;

/**
 * @Author: yaozh
 * @Description:
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

/** A class which uniquely maps 64-bit integers to other 64-bit integers. */
public class LongMapper {
    private final Cipher enc;
    private final Cipher dec;

    /**
     * Creates a new mapper with the given key.
     *
     * @param key a key, unique to the mapping, from 4 bytes to 56 bytes long
     */
    public LongMapper(byte[] key) {
        try {
            this.enc = Cipher.getInstance("Blowfish/ECB/NoPadding");
            enc.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "Blowfish"));
            this.dec = Cipher.getInstance("Blowfish/ECB/NoPadding");
            dec.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "Blowfish"));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new UnsupportedOperationException(e);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Maps a {@code long} to another {@code long}.
     *
     * @param input an arbitrary {@code long}
     * @return another {@code long}, uniquely mapped to by {@code input}
     */
    public long map(long input) {
        final byte[] bytes = toBytes(input);
        try {
            enc.update(bytes, 0, 8, bytes, 0);
        } catch (ShortBufferException e) {
            throw new IllegalStateException(e);
        }
        return fromBytes(bytes);
    }

    /**
     * Un-maps the result of {@link #map(long)}.
     *
     * @param input a mapped {@code long}
     * @return the original {@code long}
     */
    public long unmap(long input) {
        final byte[] bytes = toBytes(input);
        try {
            dec.update(bytes, 0, 8, bytes, 0);
        } catch (ShortBufferException e) {
            throw new IllegalStateException(e);
        }
        return fromBytes(bytes);
    }

    private static long fromBytes(byte[] b) {
        return ByteBuffer.wrap(b).order(ByteOrder.BIG_ENDIAN).asLongBuffer().get();
    }

    private static byte[] toBytes(long v) {
        return ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(v).array();
    }
}