package com.tiaa.assignment.service;

import com.tiaa.assignment.constants.Equipments;
import com.tiaa.assignment.model.Bolt;
import com.tiaa.assignment.model.Default;
import com.tiaa.assignment.model.Machine;
import com.tiaa.assignment.model.Equipment;

/**
 * The interface will take care of creating the {@link Equipment} using
 * {@link Equipments}
 * 
 * @author atul_sharma
 *
 */
public interface EquipmentFactory {

	public static Equipment create(Equipments equipments) {
		switch (equipments) {
		case Bolt:
			return new Bolt();
		case Machine:
			return new Machine();
		default:
			return new Default();
		}
	}
}
