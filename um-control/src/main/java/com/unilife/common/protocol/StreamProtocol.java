package com.unilife.common.protocol;

import com.unilife.common.converter.IDisplayConverter;
import com.unilife.common.entities.BitArray;
import com.unilife.common.entities.BitDefine;
import com.unilife.common.entities.UMDB;
import com.unilife.common.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2015/7/9.
 */
public abstract class StreamProtocol extends IBaseProtocol<BitDefine> {
    final static String TAG = "StreamProtocol";

    public StreamProtocol(){
        initDefines();
    }
    private int bitIndex = -1;
    private long m_framehead = 0;
    private int m_headsize = 0;
    private int m_framesize = 0;

    protected void beginOrderDefine(int start){
        bitIndex = start;
    }

    protected void addOrderDefine(int length, String keyName)
    {
        addOrderDefine(length, keyName, null);
    }

    protected void addOrderDefine(int length, String keyName, IDisplayConverter converter)
    {
        BitDefine define = new BitDefine(bitIndex, length, keyName, converter);
        bitIndex += length;
        super.addDefine(define);
    }

    protected void addDefine(int start, int length, String keyName)
    {
        addDefine(start, length, keyName, null);
    }

    protected void addDefine(int start,int length, String keyName, IDisplayConverter converter)
    {
        BitDefine define = new BitDefine(start, length, keyName, converter);
        if (bitIndex != -1)
        {
            bitIndex = start + length;
        }
        super.addDefine(define);
    }

    protected void addDefine(long head, int headsize, int framesize, long basevalue, int start, int length, String keyName)
    {
        addDefine(head, headsize, framesize, basevalue, start, length, keyName, null);
    }

    /**
     * add define
     * @param head: frame head data, MSB format
     * @param headsize: frame head size in bytes
     * @param basevalue: data base value, MSB format
     * @param start: data start in bits
     * @param length: data length in bits
     * @param keyName: key name defined in UMDB
     * @param converter: data converter
     */
    protected void addDefine(long head, int headsize, int framesize, long basevalue, int start,int length, String keyName, IDisplayConverter converter)
    {
        addDefine(head, headsize, framesize, basevalue, start, length, false, keyName, converter);
    }

    protected void addDefine(long head, int headsize, int framesize, long basevalue, int start,int length, boolean msb, String keyName, IDisplayConverter converter)
    {
        BitDefine define = new BitDefine(head, headsize, framesize, basevalue, start, length, msb, keyName, converter);

        m_framehead = head;
        m_headsize = headsize;
        m_framesize = framesize;

        if (bitIndex != -1)
        {
            bitIndex = start + length;
        }
        super.addDefine(define);
    }

    /**
     * add define
     * @param basevalue: data base value, MSB format
     * @param start: data start in bits
     * @param length: data length in bits
     * @param keyName: key name defined in UMDB
     * @param converter: data converter
     */
    protected void addDefine(long basevalue, int start,int length, String keyName, IDisplayConverter converter)
    {
        addDefine(basevalue, start, length, false, keyName, converter);
    }

    /**
     * add define
     * @param basevalue: data base value, MSB format
     * @param start: data start in bits
     * @param length: data length in bits
     * @param keyName: key name defined in UMDB
     * @param converter: data converter
     */
    protected void addDefine(long basevalue, int start,int length, boolean msb, String keyName, IDisplayConverter converter)
    {
        BitDefine define = new BitDefine(m_framehead, m_headsize, m_framesize, basevalue, start, length, msb, keyName, converter);
        if (bitIndex != -1)
        {
            bitIndex = start + length;
        }
        super.addDefine(define);
    }

    /**
     * add define
     * @param basevalue: data base value, MSB format
     * @param start: data start in bits
     * @param length: data length in bits
     * @param keyName: key name defined in UMDB
     */
    protected void addDefine(long basevalue, int start,int length, String keyName)
    {
        BitDefine define = new BitDefine(m_framehead, m_headsize, m_framesize, basevalue, start, length, keyName, null);
        if (bitIndex != -1)
        {
            bitIndex = start + length;
        }
        super.addDefine(define);
    }

    /**
     * add define
     * @param basevalue: data base value, MSB format
     * @param start: data start in bits
     * @param length: data length in bits
     * @param keyName: key name defined in UMDB
     */
    protected void addDefine(long basevalue, int start,int length, boolean msb, String keyName)
    {
        BitDefine define = new BitDefine(m_framehead, m_headsize, m_framesize, basevalue, start, length, msb, keyName, null);
        if (bitIndex != -1)
        {
            bitIndex = start + length;
        }
        super.addDefine(define);
    }

    protected void setupFrame(long framehead, int headsize, int framesize) {
        m_framehead = framehead;
        m_framesize = framesize;
        m_headsize = headsize;
    }

    public int getFrameSize() {
        return m_framesize;
    }

    public long getFrameHead() {
        return m_framehead;
    }

    protected void skipBits(int offset)
    {
        bitIndex += offset;
    }

    protected void endOrderDefine(){
        bitIndex = -1;
    }

    public BitDefine[] getDefines(){
        List<BitDefine> lst = getDefineLst();
        BitDefine[] ret = new BitDefine[lst.size()];
        ret = lst.toArray(ret);
        return ret;
    }

    public UMDB toUMDB(Object obj)
    {
        if (obj instanceof byte[])
        {
            return toUMDB((byte[])obj);
        }
        if (obj instanceof int[])
        {
            return toUMDB((int[])obj);
        }

        return null;
    }

    public UMDB toUMDB(int[] buffer)
    {
        BitArray bitArray = new BitArray(buffer);
        UMDB db = new UMDB();
        for (BitDefine bf : getDefines())
        {
            if (bf.getHeadSize() != 0 && bf.getFrameHead() != bitArray.getInt64(0, bf.getHeadSize(), true)) {
                continue;
            }

            long val = bitArray.getInt64(bf.getStart(), bf.getLength(), bf.getMSB());
            val -= bf.getBaseValue();
            String strVal = val + "";
            if (bf.getConverter() != null)
            {
                strVal = bf.getConverter().toDisplay(val);
            }
            db.addValue(bf.getUMDBKey(), strVal);
        }
        return db;
    }

    public UMDB toUMDB(byte[] buffer)
    {
        BitArray bitArray = new BitArray(buffer);
        UMDB db = new UMDB();

        for (BitDefine bf : getDefines())
        {
            if (bf.getHeadSize() != 0 && bf.getFrameHead() != bitArray.getInt64(0, bf.getHeadSize(), true)) {
                continue;
            }
            long val = bitArray.getInt64(bf.getStart(), bf.getLength(), bf.getMSB());
            val -= bf.getBaseValue();
            String strVal = val + "";
            if (bf.getConverter() != null)
            {
                strVal = bf.getConverter().toDisplay(val);
            }
            db.addValue(bf.getUMDBKey(), strVal);
        }
        return db;
    }

    public boolean toUMDB(int[] buffer, int length, UMDB db)
    {
        if (db == null) {
            throw new IllegalArgumentException();
        }
        BitArray bitArray = new BitArray(buffer);
        BitDefine[] bitDefines = getDefines();
        for (BitDefine bf : bitDefines)
        {
            if (bf.getHeadSize() != 0 && (bf.getFrameSize() != (length*8) || bf.getFrameHead() != bitArray.getInt64(0, bf.getHeadSize(), true))) {
                continue;
            }

            long val = bitArray.getInt64(bf.getStart(), bf.getLength(), bf.getMSB());
            String strVal = val + "";
            val -= bf.getBaseValue();
            if (bf.getConverter() != null)
            {
                strVal = bf.getConverter().toDisplay(val);
            }
            db.addValue(bf.getUMDBKey(), strVal);
        }
        return true;
    }

    public int getBufferLength(){
        sortDefine();
        BitDefine bitDefine = (BitDefine)getDefineLst().get(getDefineLst().size() - 1);
        return bitDefine.getStart() + bitDefine.getLength();
    }

    public char[] toCharBuffer(UMDB db)
    {
        long framehead = -1;
        int headsize = 0;
        BitArray bitArray = new BitArray(getBufferLength());
        for (BitDefine bf : getDefines())
        {
            if (db.containValue(bf.getUMDBKey())) {
                long nVal = 0;
                String val = db.getValue(bf.getUMDBKey());
                if (bf.getConverter() != null)
                {
                    nVal = bf.getConverter().toValue(val);
                }
                else
                {
                    nVal = StringUtils.parseLong(val);
                }
                nVal += bf.getBaseValue();
                if (bf.getHeadSize() != 0) {
                    headsize = bf.getHeadSize();
                    framehead = bf.getFrameHead();
                }
                bitArray.set(bf.getStart(), bf.getLength(), (int)nVal);
            }

        }
        char[] buf = bitArray.toCharArray();
        if (headsize != 0) {
            longToCharArrayMSB(buf, 0, headsize, framehead);
        }
        return buf;
    }

    public byte[] toByteBuffer(UMDB db)
    {
        long framehead = -1;
        int headsize = 0;
        BitArray bitArray = new BitArray(getBufferLength());
        for (BitDefine bf : getDefines())
        {
            if (db.containValue(bf.getUMDBKey())) {
                long nVal = 0;
                String val = db.getValue(bf.getUMDBKey());
                if (bf.getConverter() != null)
                {
                    nVal = bf.getConverter().toValue(val);
                }
                else
                {
                    nVal = StringUtils.parseLong(val);
                }
                nVal += bf.getBaseValue();
                if (bf.getHeadSize() != 0) {
                    headsize = bf.getHeadSize();
                    framehead = bf.getFrameHead();
                }
                bitArray.set(bf.getStart(), bf.getLength(), (int)nVal);
            }

        }
        byte[] buf = bitArray.toByteArray();
        if (headsize != 0) {
            longToByteArrayMSB(buf, 0, headsize, framehead);
        }
        return buf;
    }

    public int[] toBuffer(UMDB db)
    {
        long framehead = -1;
        int headsize = 0;
        BitArray bitArray = new BitArray(getBufferLength());
        for (BitDefine bf : getDefines())
        {
            if (db.containValue(bf.getUMDBKey())) {
                long nVal = 0;
                String val = db.getValue(bf.getUMDBKey());
                if (bf.getConverter() != null)
                {
                    nVal = bf.getConverter().toValue(val);
                }
                else
                {
                    nVal = StringUtils.parseLong(val);
                }
                nVal += bf.getBaseValue();
                if (bf.getHeadSize() != 0) {
                    headsize = bf.getHeadSize();
                    framehead = bf.getFrameHead();
                }
                bitArray.set(bf.getStart(), bf.getLength(), (int)nVal);
            }
        }
        int[] buf = bitArray.toIntArray();
        if (headsize != 0) {
            longToIntArrayMSB(buf, 0, headsize, framehead);
        }
        return buf;
    }

    public int toBuffer(UMDB db, int[] data)
    {
        Map<String, Integer> frames = new HashMap<>();
        int doffset = 0;
        int addr = 0;

        for (BitDefine bf : getDefines())
        {
            if (db.containValue(bf.getUMDBKey())) {
                long nVal = 0;
                String val = db.getValue(bf.getUMDBKey());
                if (bf.getConverter() != null) {
                    nVal = bf.getConverter().toValue(val);
                } else {
                    nVal = StringUtils.parseLong(val);
                }
                nVal += bf.getBaseValue();
                if (bf.getHeadSize() != 0) {
                    String keyval = String.valueOf(bf.getFrameHead()) + String.valueOf(bf.getBaseValue());
                    if (frames.containsKey(keyval)) {
                        addr = frames.get(keyval);
                    } else {
                        frames.put(keyval, doffset);
                        addr = doffset;
                        doffset += bf.getFrameSize() / 8;
                    }
                    longToIntArrayMSB(data, addr, bf.getHeadSize()/8, bf.getFrameHead());
                }
                int start = bf.getStart();
                int length = bf.getLength();
                while (length > 0) {
                    int id = start / 8 + addr;
                    int offset = start % 8;
                    int len = length;
                    if (offset + len > 8)
                        len = 8 - offset;
                    length -= len;
                    start += len;
                    if (id >= data.length)
                        break;
                    if (len < 8) {
                        int mask = 0xff >> (8 - len) << offset;
                        data[id] = (data[id] & (~mask)) + (int) ((nVal << offset) & 0xff);
                    } else {
                        data[id] = (int)(nVal >> length) & 0xff;
                    }
                }
            }
        }
        frames.clear();
        return doffset;
    }

    public int toBuffer(UMDB db, int[] data, long framehead)
    {
        Map<String, Integer> frames = new HashMap<>();
        int doffset = 0;
        int addr = 0;

        for (BitDefine bf : getDefines())
        {
            if (db.containValue(bf.getUMDBKey())) {
                long nVal = 0;
                String val = db.getValue(bf.getUMDBKey());
                if (bf.getConverter() != null) {
                    nVal = bf.getConverter().toValue(val);
                } else {
                    nVal = StringUtils.parseLong(val);
                }
                if (bf.getFrameHead() != framehead) {
                    continue;
                }
                nVal += bf.getBaseValue();
                if (bf.getHeadSize() != 0) {
                    String keyval = String.valueOf(bf.getFrameHead()) + String.valueOf(bf.getBaseValue());
                    if (frames.containsKey(keyval)) {
                        addr = frames.get(keyval);
                    } else {
                        frames.put(keyval, doffset);
                        addr = doffset;
                        doffset += bf.getFrameSize() / 8;
                    }
                    longToIntArrayMSB(data, addr, bf.getHeadSize()/8, bf.getFrameHead());
                }
                int start = bf.getStart();
                int length = bf.getLength();
                while (length > 0) {
                    int id = start / 8 + addr;
                    int offset = start % 8;
                    int len = length;
                    if (offset + len > 8)
                        len = 8 - offset;
                    length -= len;
                    start += len;
                    if (id >= data.length)
                        break;
                    if (len < 8) {
                        int mask = 0xff >> (8 - len) << offset;
                        data[id] = (data[id] & (~mask)) + (int) ((nVal << offset) & 0xff);
                    } else {
                        data[id] = (int)(nVal >> length) & 0xff;
                    }
                }
            }
        }
        frames.clear();
        return doffset;
    }

    void longToCharArrayMSB(char[] data, int offset, int size, long lval)
    {
        while (size > 0) {
            size--;
            data[size+offset] = (char)(lval&0xff);
            lval >>= 8;
        }
    }

    void longToByteArrayMSB(byte[] data, int offset, int size, long lval)
    {
        while (size > 0) {
            size--;
            data[size+offset] = (byte)(lval&0xff);
            lval >>= 8;
        }
    }

    void longToIntArrayMSB(int[] data, int offset, int size, long lval)
    {
        while (size > 0) {
            size--;
            data[size+offset] = (int)(lval&0xff);
            lval >>= 8;
        }
    }

}
