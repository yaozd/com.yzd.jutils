package com.yzd.jutils.exception;

import java.io.IOException;
import java.io.Writer;

/***
 * UnsafeStringWriter取自：dubbo
 */
public class UnsafeStringWriter extends Writer {
    private StringBuilder mBuffer;

    public UnsafeStringWriter() {
        this.lock = this.mBuffer = new StringBuilder();
    }

    public UnsafeStringWriter(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative buffer size");
        } else {
            this.lock = this.mBuffer = new StringBuilder();
        }
    }

    public void write(int c) {
        this.mBuffer.append((char)c);
    }

    public void write(char[] cs) throws IOException {
        this.mBuffer.append(cs, 0, cs.length);
    }

    public void write(char[] cs, int off, int len) throws IOException {
        if (off >= 0 && off <= cs.length && len >= 0 && off + len <= cs.length && off + len >= 0) {
            if (len > 0) {
                this.mBuffer.append(cs, off, len);
            }

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void write(String str) {
        this.mBuffer.append(str);
    }

    public void write(String str, int off, int len) {
        this.mBuffer.append(str.substring(off, off + len));
    }

    public Writer append(CharSequence csq) {
        if (csq == null) {
            this.write("null");
        } else {
            this.write(csq.toString());
        }

        return this;
    }

    public Writer append(CharSequence csq, int start, int end) {
        CharSequence cs = csq == null ? "null" : csq;
        this.write(((CharSequence)cs).subSequence(start, end).toString());
        return this;
    }

    public Writer append(char c) {
        this.mBuffer.append(c);
        return this;
    }

    public void close() {
    }

    public void flush() {
    }

    public String toString() {
        return this.mBuffer.toString();
    }
}

