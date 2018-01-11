package com.unilife.common.serials;

public class comfifo {

	private int[] m_data;
	private int m_cap;
	private int m_rd;
	private int m_wr;
	
	comfifo(int cap)
	{
		m_cap = cap;
		m_data = new int[cap];
		for (int i=0; i<cap; i++)
			m_data[i] = 0;
	} 

	/** set fifo capacity of bytes
	 * @param cap: capacity of bytes
	 */
	public void setCapacity(int cap)
	{
		m_data = new int[cap];
		for (int i=0; i<cap; i++)
			m_data[i] = 0;
		m_cap = cap;
		m_wr = 0;
		m_rd = 0;
	}
	
	/** reset fifo buffer pointer
	 * @param : none
	 */
	public void reset()
	{
		m_rd = 0;
		m_wr = 0;
		for (int i=0; i<m_cap; i++)
			m_data[i] = 0;
	}

	/** get fifo buffer capacity
	 * @return : capacity value of bytes
	 */
	public int getCapacity()
	{
		return m_cap;
	}

	/** get fifo buffer free space
	 * @return : capacity value of bytes
	 */
	public int getFreeSpace()
	{
		if (m_wr >= m_rd)
			return m_cap - (m_wr - m_rd);
		else
			return m_rd - m_wr;
	}

	/** get fifo buffer data size
	 * @return : size of data in bytes
	 */
	public int getDataSize()
	{
		return getCapacity() - getFreeSpace();
	}

	/** read data from fifo buffer
	 * @return : actually read length
	 */
	public int read(int[] data, int size) {		
		if (size > getDataSize())
			size = getDataSize();
		for (int i=0;i<size;i++) {
			data[i] = m_data[m_rd];
			m_rd ++;
			if (m_rd >= m_cap)
				m_rd -= m_cap;
		}
		
		return size;
	}

	/** read one data from fifo buffer
	 * @return : read data value
	 */
	public int read() {
		int data = -1;
		if (getDataSize() > 0) {
			data = m_data[m_rd];
			m_rd++;
			if (m_rd >= m_cap)
				m_rd -= m_cap;
			return data;
		} else {
			return data;
		}
	}

	/** read one data from fifo buffer at special offset
	 * @return : read data value
	 */
	public int read(int offset) {
		int data = -1;
		if (getDataSize() > offset) {
			int pos = offset + m_rd;
			if (pos >= m_cap) {
				pos -= m_cap;
			}
			data = m_data[pos];
			m_rd += offset;
			return data;
		} else {
			return data;
		}
	}


	/** read data from fifo buffer
	 * @return : actually read length
	 */
	public int seek(int size) {
		if (size > getDataSize())
			size = getDataSize();
		m_rd += size;
		if (m_rd >= m_cap)
			m_rd -= m_cap;

		return size;
	}

	/** read one data from fifo buffer, but dont change rd counter
	 * @return : read data value
	 */
	public int get(int offset) {
		int data = -1;	
		if (getDataSize() > offset) {
			offset += m_rd;
			if (offset >= m_cap)
				offset -= m_cap;
			data = m_data[offset];
			return data;
		} else {
			return data;
		}
	}

	/** read data from fifo buffer, but dont change rd counter
	 * @return : read data value
	 */
	public int get(int[] buf, int offset, int size) {
		int count=0;
		while (offset < getDataSize() && count < size) {
			int pos = offset + m_rd;
			if (pos >= m_cap)
				pos -= m_cap;
			buf[count] = m_data[pos];
			offset ++;
			count ++;
		}
		return count;
	}

	/** write data to fifo buffer
	 * @return : actually read length
	 */
	public int write(int[] data, int size) {
		if (size > getFreeSpace())
			size = getFreeSpace();
		for (int i=0;i<size;i++) {
			m_data[m_wr] = data[i];
			m_wr ++;
			if (m_wr >= m_cap)
				m_wr -= m_cap;
		}
		return size;
	}

	/** write data to fifo buffer
	 * @return : actually read length
	 */
	public int write(int data) {
		if (getFreeSpace() > 0) {
			m_data[m_wr] = data;
			m_wr++;
			if (m_wr >= m_cap)
				m_wr -= m_cap;
			return 1;
		} else {
			return 0;
		}
	}

}
