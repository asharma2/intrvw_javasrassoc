package com.tiaa.assignment.workers;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.tiaa.assignment.constants.Equipments;
import com.tiaa.assignment.model.Equipment;
import com.tiaa.assignment.model.Input;

public class UnfinishedProducerTest {

	@Test
	public void unfinishedProducer() throws Exception {
		BlockingQueue<Equipment> equipments = new LinkedBlockingQueue<>();
		Input input = new Input(3, 6, 1);
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(new UnfinishedProducer(input, equipments));
		final AtomicInteger machine = new AtomicInteger(0);
		final AtomicInteger bolt = new AtomicInteger(0);
		service.submit(() -> {
			try {
				while (true) {
					Equipment equipment = equipments.take();
					if (equipment.getEquipments() == Equipments.Default) {
						break;
					}
					if (equipment.getEquipments() == Equipments.Bolt) {
						bolt.incrementAndGet();
					} else {
						machine.incrementAndGet();
					}
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		});
		service.shutdown();
		assertEquals(3, machine.get());
		assertEquals(6, bolt.get());
	}
}
