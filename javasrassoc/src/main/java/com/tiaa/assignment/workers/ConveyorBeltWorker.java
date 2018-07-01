package com.tiaa.assignment.workers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.tiaa.assignment.constants.Equipments;
import com.tiaa.assignment.model.Equipment;
import com.tiaa.assignment.service.ConveyorSignalService;

public class ConveyorBeltWorker implements EquipmentWorker {

	protected boolean running = true;
	protected BlockingQueue<Equipment> unfinishedGoods;
	protected ConveyorSignalService conveyorSignalService;
	protected Queue<Equipment> unfinishedEquipments = new LinkedList<>();

	public ConveyorBeltWorker(BlockingQueue<Equipment> unfinishedGoods, ConveyorSignalService conveyorSignalService) {
		this.unfinishedGoods = unfinishedGoods;
		this.conveyorSignalService = conveyorSignalService;
	}

	@Override
	public void run() {
		try {
			while (isRunning()) {
				Equipment eq = unfinishedGoods.take();
				System.out.println("Thread# " + Thread.currentThread().getName() + " , Eq: " + eq.getEquipments());
				if (eq.getEquipments() == Equipments.Default) {
					unfinishedGoods.put(eq);
					stopQuielty();
					continue;
				}

				if (eq.getEquipments() == Equipments.Bolt) {
					unfinishedEquipments.offer(eq);
					conveyorSignalService.notifyForBolt();
					conveyorSignalService.waitForMachine();
				}

				if (eq.getEquipments() == Equipments.Machine) {
					unfinishedEquipments.offer(eq);
					conveyorSignalService.notifyForMachine();
					conveyorSignalService.waitForBolt();
				}

				if (isEligibleQuantity(unfinishedEquipments)) {
					System.out.println(unfinishedEquipments.poll());
					System.out.println(unfinishedEquipments.poll());
					System.out.println(unfinishedEquipments.poll());
					TimeUnit.SECONDS.sleep(60);
				}

			}
		} catch (Exception e) {
			System.err.println("Exception while picking the unfinished good from the belt" + e);
		}
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void stopQuielty() {
		running = false;
	}

}
