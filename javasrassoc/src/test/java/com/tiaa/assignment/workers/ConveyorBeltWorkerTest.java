package com.tiaa.assignment.workers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.tiaa.assignment.model.Equipment;
import com.tiaa.assignment.model.Input;
import com.tiaa.assignment.service.ConveyorSignalService;
import com.tiaa.assignment.service.ConveyorSignalServiceImpl;

public class ConveyorBeltWorkerTest {

	@Test
	public void unfinishedProducer() throws Exception {
		BlockingQueue<Equipment> equipments = new LinkedBlockingQueue<>();
		Input input = new Input(3, 6, 1);
		ConveyorSignalService conveyorSignalService = new ConveyorSignalServiceImpl();
		ExecutorService service = Executors.newFixedThreadPool(4);
		service.submit(new UnfinishedProducer(input, equipments));
		
		service.submit(new ConveyorBeltWorker(equipments, conveyorSignalService));
		service.submit(new ConveyorBeltWorker(equipments, conveyorSignalService));
		service.submit(new ConveyorBeltWorker(equipments, conveyorSignalService));

		service.shutdown();
		service.awaitTermination(5, TimeUnit.MINUTES);
	}
}
