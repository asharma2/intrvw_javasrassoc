package com.tiaa.assignment.workers;

import java.util.Queue;

import com.tiaa.assignment.constants.Equipments;
import com.tiaa.assignment.model.Equipment;

public interface EquipmentWorker extends Runnable {

	boolean isRunning();

	void stopQuielty();

	default boolean isEligibleQuantity(Queue<Equipment> equipments) {
		return equipments.stream().filter(e -> e.getEquipments() == Equipments.Bolt).count() > 2
		        && equipments.stream().filter(e -> e.getEquipments() == Equipments.Machine).count() > 1;
	}
}
