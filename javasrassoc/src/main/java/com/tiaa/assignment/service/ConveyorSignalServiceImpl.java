package com.tiaa.assignment.service;

public class ConveyorSignalServiceImpl implements ConveyorSignalService {

	protected Object anything = new Object();
	protected Object machine = new Object();
	protected Object bolt = new Object();

	@Override
	public void waitForAnything() throws Exception {
		synchronized (anything) {
			anything.wait();
		}
	}

	@Override
	public void waitForMachine() throws Exception {
		synchronized (machine) {
			machine.wait();
		}
	}

	@Override
	public void waitForBolt() throws Exception {
		synchronized (bolt) {
			bolt.wait();
		}
	}

	@Override
	public void notifyForAnything() throws Exception {
		synchronized (anything) {
			anything.notify();
		}
	}

	@Override
	public void notifyForMachine() throws Exception {
		synchronized (machine) {
			machine.notify();
		}
	}

	@Override
	public void notifyForBolt() throws Exception {
		synchronized (bolt) {
			bolt.notify();
		}
	}

}
