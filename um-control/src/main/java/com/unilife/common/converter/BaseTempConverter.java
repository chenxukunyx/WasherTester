package com.unilife.common.converter;

/**
 * Created by nicholas on 2015/7/14.
 */
public class BaseTempConverter {

    private float m_unit = 1.0f;
    private float m_start = 0.0f;

    public BaseTempConverter(float unit, float start)
    {
        m_unit = unit;
        m_start = start;
    }

    /**
     * set converter parameters
     * @param unit
     * @param start
     */
    public void setConverter(float unit, float start)
    {
        m_unit = unit;
        m_start = start;
    }

    /**
     * convert input temp to output
     * @param input
     * @return
     */
    public float tempConvert(float input, float unit, float start)
    {
        return input*unit + start;
    }

    /**
     * convert input temp to output
     * @param input
     * @return
     */
    public float tempConvert(float input)
    {
        return input*m_unit + m_start;
    }

    /**
     * convert input temp to output
     * @param input
     * @return
     */
    public float tempConvertReverse(float input, float unit, float start)
    {
        return (input - start)/unit;
    }

    /**
     * convert input temp to output
     * @param input
     * @return
     */
    public float tempConvertReverse(float input)
    {
        return (input - m_start)/m_unit;
    }
}
