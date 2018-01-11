package com.unilife.common.entities;

/**
 * Created by Kevin on 2015/7/16.
 */
public class UMCharSequence {
    private int m_capacity = 0;
    private byte[] m_buffer = null;
    public UMCharSequence()
    {
        this(0);
    }
    public UMCharSequence(int capacity)
    {
        resize(capacity);
    }


    public UMCharSequence(char[] buffer)
    {
        resize(buffer.length);

        System.arraycopy(buffer, 0, buffer, 0, buffer.length);
    }

    protected void resize(int newSize)
    {
        if (m_capacity < newSize)
        {
            byte[] newBuffer = new byte[newSize];
            if (m_buffer != null)
            {
                System.arraycopy(m_buffer, 0, newBuffer, 0, m_capacity);
            }

            m_capacity = newSize;
            m_buffer = newBuffer;
        }
    }


    private boolean checkParam(int offset, int length, boolean allowResize)
    {
        if (offset < 0 || length <= 0)
        {
            return false;
        }
        if (offset + length > m_capacity)
        {
            if (!allowResize)
            {
                return false;
            }
            else
            {
                resize(offset + length);
            }
        }
        return true;
    }

    public static byte[] longToArray(long value){
        byte[] buffer = new byte[8];

        for (int i = 0; i < 8; i++)
        {
            buffer[7-i] = (byte)(value & 0xff);
            value >>= 8;
        }
        return buffer;
    }

    public static long arrayToLong(char[] array)
    {
        long value = 0;
        for (int i = array.length - 1; i >= Math.max(0, array.length - 8); i--)
        {
            long tmpVal = array[i];
            value |= (tmpVal << i);
        }
        return value;
    }

    public void setValue(byte[] buffer, int offset, int length)
    {
        if (!checkParam(offset, length, true))
        {
            throw new IllegalArgumentException("param for UMCharSequence.setValue Illegal");
        }
        int srcPos = 0;
        if (buffer.length > length)
        {
            srcPos = buffer.length - length;
        }
        System.arraycopy(buffer, srcPos, m_buffer, offset, length);
    }

    public void setValue(long value, int offset, int length)
    {
        if (!checkParam(offset, length, true))
        {
            throw new IllegalArgumentException("param for UMCharSequence.setValue Illegal");
        }

        if (length > 8)
        {
            throw new IllegalArgumentException("param for UMCharSequence.setValue Illegal");
        }
        byte[] buffer = longToArray(value);
        setValue(buffer, offset, length);
    }

    public void setValue(String str, int offset, int length)
    {
        if (!checkParam(offset, length, true))
        {
            throw new IllegalArgumentException("param for UMCharSequence.setValue Illegal");
        }
        byte[] buffer = str.getBytes();
        setValue(buffer, offset, length);
    }

    public void setValue(byte c, int offset)
    {
        if (!checkParam(offset, 1, true))
        {
            throw new IllegalArgumentException("param for UMCharSequence.setValue Illegal");
        }
        m_buffer[offset] = c;
    }

    public byte[] getByteArray(int offset, int length)
    {
        if (!checkParam(offset, length, false))
        {
            throw new IllegalArgumentException("param for UMCharSequence.getLong Illegal");
        }
        byte[] buffer = new byte[length];
        System.arraycopy(m_buffer, offset, buffer, 0, length);
        return buffer;
    }

    public byte[] getArray(){
        return m_buffer;
    }

    public char[] getArray(int offset, int length)
    {
        if (!checkParam(offset, length, false))
        {
            throw new IllegalArgumentException("param for UMCharSequence.getLong Illegal");
        }
        char[] buffer = new char[length];
        System.arraycopy(m_buffer, offset, buffer, 0, length);
        return buffer;
    }

    public long getLong(int offset, int length)
    {
        if (!checkParam(offset, length, false))
        {
            throw new IllegalArgumentException("param for UMCharSequence.getLong Illegal");
        }

        char[] buffer = getArray(offset, length);
        return arrayToLong(buffer);
    }


    public byte get(int pos)
    {
        if (!checkParam(pos, 1, false))
        {
            throw new IllegalArgumentException("param for UMCharSequence.getLong Illegal");
        }

        return m_buffer[pos];
    }

    public int getLength(){
        return m_capacity;
    }

}
