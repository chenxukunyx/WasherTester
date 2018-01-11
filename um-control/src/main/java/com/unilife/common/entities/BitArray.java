package com.unilife.common.entities;

import java.util.BitSet;

/**
 * Created by Kevin on 2015/7/9.
 */
public class BitArray {
    private BitSet m_bitSet;
    private int m_nCapacity = 0;

    public BitArray(int capacity)
    {
        if ((capacity % 8) != 0)
        {
            capacity = (capacity /8 + 1) * 8;
        }
        m_nCapacity = capacity;
        m_bitSet = new BitSet(m_nCapacity);
    }

    /**
     * @param buffer
     */
    public BitArray(int[] buffer)
    {
        if (buffer == null || buffer.length == 0)
        {
            throw new IllegalArgumentException("");
        }
        fromIntArray(buffer);
    }

    private void fromIntArray(int[] intArray)
    {
        m_bitSet = new BitSet(intArray.length * 8);
        for (int i = 0; i < intArray.length; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if ((intArray[i] & (1 << j)) > 0)
                {
                    m_bitSet.set(i*8+j);
                }
            }
        }
        m_nCapacity = intArray.length * 8;
    }

    public BitArray(byte[] buffer)
    {
        if (buffer == null || buffer.length == 0)
        {
            throw new IllegalArgumentException("");
        }
        int[] intArray = new int[buffer.length];
        for (int i = 0; i < intArray.length; i++)
        {
            intArray[i] = buffer[i];
        }
        fromIntArray(intArray);
    }

    boolean checkParam(int start, int length)
    {
        if (start >= m_nCapacity || start < 0 || start+length>m_nCapacity || length < 0)
        {
            return false;
        }
        return true;
    }

    public int getInt32(int start, int length)
    {
        if (!checkParam(start,length))
        {
            throw new IllegalArgumentException(start + "," + length + " is out of capacity of the array");
        }
        int ret = 0;
        for (int iBitIndex = start + length-1; iBitIndex >= start; iBitIndex--)
        {
            if (m_bitSet.get(iBitIndex))
            {
                ret |= 1;
            }
            ret <<= 1;
        }
        ret >>= 1;
        return ret;
    }

    public long getInt64(int start, int length)
    {
        return getInt64(start, length, false);
    }

    public long getInt64(int start, int length, boolean msb)
    {
        if (!checkParam(start,length))
        {
            throw new IllegalArgumentException(start + "," + length + " is out of capacity of the array");
        }
        long val = 0;
        long ret = 0;
        long bitc = 0;

        for (int iBitIndex = start + length-1; iBitIndex >= start; iBitIndex--)
        {
            if (m_bitSet.get(iBitIndex))
            {
                val |= 1;
            }
            if (msb) {
                if ((iBitIndex&7) == 0) {
                    ret += val<<bitc;
                    val = 0;
                    bitc += 8;
                }
            } else {
                ret = val;
            }
            val <<= 1;
        }
        return ret;
    }

    public void set(int start, int length, int value)
    {
        if (!checkParam(start, length))
        {
            throw new IllegalArgumentException(start + "," + length + " is out of capacity of the array");
        }
        int valTmp = value;
        for (int iBitIndex = start; iBitIndex < start + length; iBitIndex++)
        {
            if ((valTmp&1) == 1)
            {
                m_bitSet.set(iBitIndex);
            }
            else
            {
                m_bitSet.clear(iBitIndex);
            }
            valTmp >>= 1;
        }
    }

    public int[] toIntArray()
    {
        return toIntArray(null);
    }

    public int[] toIntArray(int[] result)
    {

        int bitCount = m_bitSet.size();
        if (result == null || result.length < m_nCapacity / 8)
        {
            result = new int[m_nCapacity/8];
        }
        for (int i = 0; i < bitCount; ++i) {
            int resultIndex = i / 8;
            int bitIndex = i % 8;
            if (m_bitSet.get(i))
                result[resultIndex] |= (1 << bitIndex);
            else
                result[resultIndex] &= ~(1 << bitIndex);
        }
        return result;
    }

    public char[] toCharArray(){
        int bitCount = m_nCapacity;
        char[] result = new char[bitCount];

        for (int i = 0; i < bitCount; ++i) {
            int resultIndex = i / 8;
            int bitIndex = i % 8;
            if (m_bitSet.get(i))
                result[resultIndex] |= (1 << bitIndex);
            else
                result[resultIndex] &= ~(1 << bitIndex);
        }
        return result;
    }

    public byte[] toByteArray(){
        int bitCount = m_nCapacity;
        byte[] result = new byte[(bitCount + 7)/ 8];
        for (int i = 0; i < bitCount; ++i) {
            int resultIndex = i / 8;
            int bitIndex = i % 8;
            if (m_bitSet.get(i))
                result[resultIndex] |= (1 << bitIndex);
            else
                result[resultIndex] &= ~(1 << bitIndex);
        }
        return result;
    }
}
