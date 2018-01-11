package com.unilife.common.serials;

import com.mediatek.factorymode.serial.jniSERIAL;

public class SerialTransmitter {

	private int m_port_format = 0;// 0:lsb, 1:msb
	private boolean m_simulate = false;
	jniSERIAL comPort = null;

	private int bit_swap(int val)
	{
		int rval = ((val&1)<<7) + ((val>>7)&1) + ((val&2)<<5) + ((val>>5)&2) + ((val&4)<<3) + ((val>>3)&4) + ((val&8)<<1) + ((val>>1)&8);
		return rval;
	}
	
	public int write(int[] data, int size) {
		// TODO Auto-generated method stub
		int ret = size;
		if (m_simulate || comPort == null) {
			return ret;
		} else {
			int wsz;
			while (size > 0) {
				if (size > comPort.wr_data.length)
					wsz = comPort.wr_data.length;
				else
					wsz = size;

				for (int i = 0; i < wsz; i++) {
					if (m_port_format == 0)
						comPort.wr_data[i] = data[i+ret-size];
					else
						comPort.wr_data[i] = bit_swap(data[i+ret-size]);
				}
				wsz = comPort.serial_write(comPort, wsz);
				size -= wsz;
			}
		}
		return ret;
	}

	public int read(int[] data, int size) {
		// TODO Auto-generated method stub
		int ret = size;
		if (m_simulate || comPort == null) {
			;
		} else {
			ret = comPort.serial_read(comPort);
			for (int i=0;i<ret;i++)
				data[i] = comPort.rd_data[i];
		}

		return ret;
	}

	public int init(int bautrate, int bits, int parity, int stop, int format, boolean simulate) {
		// TODO Auto-generated method stub

		char cparity;

		if (parity == 1)
			cparity = 'o';
		else if (parity == 2)
			cparity = 'e';
		else
			cparity = 0;

		m_port_format = format;
		m_simulate = simulate;
		if (!simulate) {
			comPort = new jniSERIAL();
			comPort.init(comPort, bautrate, bits, (char)cparity, stop);
		}

		return 0;
	}

	public int deinit() {
		// TODO Auto-generated method stub
		if (!m_simulate) {
			comPort.exit();
			comPort = null;
		}
		return 0;
	}


}
