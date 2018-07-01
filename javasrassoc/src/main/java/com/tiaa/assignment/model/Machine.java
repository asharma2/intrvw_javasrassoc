package com.tiaa.assignment.model;

import com.tiaa.assignment.constants.Equipments;

public class Machine implements Equipment {

	@Override
	public Equipments getEquipments() {
		return Equipments.Machine;
	}
}
