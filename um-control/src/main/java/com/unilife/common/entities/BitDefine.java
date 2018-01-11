package com.unilife.common.entities;

import com.unilife.common.converter.IDisplayConverter;

/**
 * Created by nicholasyu on 7/8/15.
 */
public class BitDefine extends EntityDefine {
    private int m_start;
    private int m_length;
    private long m_framehead;
    private int m_headsize;
    private int m_framesize;
    private long m_baseValue;
    private boolean m_msb;

    public BitDefine(int start, int length, String name)
    {
        this(start, length, name, null);
    }

    public BitDefine(int start, int length, String name, IDisplayConverter converter)
    {
        this(start, length, name, false, converter);
    }

    public BitDefine(int start, int length, String name, boolean msb, IDisplayConverter converter)
    {
        super(name, converter);
        m_start = start;
        m_length = length;
        m_framehead = -1;
        m_headsize = 0;
        m_baseValue = 0;
        m_framesize = 0;
        m_msb = msb;
    }

    public BitDefine(long framehead, int headsize, int framesize, long basevalue, int start, int length, String name)
    {
        this(framehead, headsize, framesize, basevalue, start, length, false, name, null);
    }

    public BitDefine(long framehead, int headsize, int framesize, long basevalue, int start, int length, String name, IDisplayConverter converter)
    {
        this(framehead, headsize, framesize, basevalue, start, length, false, name, converter);
    }

    public BitDefine(long framehead, int headsize, int framesize, long basevalue, int start, int length, boolean msb, String name, IDisplayConverter converter)
    {
        super(name, converter);
        m_start = start;
        m_length = length;
        m_framehead = framehead;
        m_headsize = headsize;
        m_framesize = framesize;
        m_baseValue = basevalue;
        m_msb = msb;
    }

    /**
     * get start offset
     * @return
     */
    public int getStart()
    {
        return m_start;
    }

    /**
     * get end offset
     * @return
     */
    public int getLength()
    {
        return m_length;
    }

    /**
     * get frame head
     * @return
     */
    public long getFrameHead()
    {
        return m_framehead;
    }

    /**
     * get frame head size
     * @return
     */
    public int getHeadSize()
    {
        return m_headsize;
    }

    /**
     * get frame total size
     * @return
     */
    public int getFrameSize()
    {
        return m_framesize;
    }

    /**
     * get frame value
     * @return
     */
    public long getBaseValue()
    {
        return m_baseValue;
    }

    /**
     * get frame msb flag
     * @return
     */
    public boolean getMSB()
    {
        return m_msb;
    }

    @Override
    public int compareTo(EntityDefine another) {
        if (another instanceof BitDefine)
        {
            return getStart() - ((BitDefine)another).getStart();
        }
        return -1;
    }
}
