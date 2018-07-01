package com.tiaa.assignment.workers;

import java.util.concurrent.BlockingQueue;

import com.tiaa.assignment.constants.Equipments;
import com.tiaa.assignment.model.Equipment;
import com.tiaa.assignment.model.Input;
import com.tiaa.assignment.service.EquipmentFactory;

public class UnfinishedProducer implements Runnable {

	protected BlockingQueue<Equipment> equipments;
	protected Input input;

	public UnfinishedProducer(Input input, BlockingQueue<Equipment> equipments) {
		this.equipments = equipments;
		this.input = input;
	}

	@Override
	public void run() {
		try {
			boolean bolt = true;
			while (input.bolt > 0 && input.machine > 0) {
				if (bolt) {
					equipments.put(EquipmentFactory.create(Equipments.Bolt));
					input.bolt--;
				} else {
					equipments.put(EquipmentFactory.create(Equipments.Machine));
					input.machine--;
				}
				bolt = !bolt;
			}
			while (input.bolt > 0) {
				equipments.put(EquipmentFactory.create(Equipments.Bolt));
				input.bolt--;
			}

			while (input.machine > 0) {
				equipments.put(EquipmentFactory.create(Equipments.Machine));
				input.machine--;
			}
			equipments.put(EquipmentFactory.create(Equipments.Default));
		} catch (Exception e) {
			System.err.println("Exception while producing the equipment: " + e.getMessage());
		}
	}

}
