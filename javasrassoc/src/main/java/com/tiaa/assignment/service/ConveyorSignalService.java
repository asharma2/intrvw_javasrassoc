package com.tiaa.assignment.service;

public interface ConveyorSignalService {

	void waitForAnything() throws Exception;

	void waitForMachine() throws Exception;

	void waitForBolt() throws Exception;

	void notifyForAnything() throws Exception;

	void notifyForMachine() throws Exception;

	void notifyForBolt() throws Exception;
}
