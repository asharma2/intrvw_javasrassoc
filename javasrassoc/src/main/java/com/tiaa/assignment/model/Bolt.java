package com.tiaa.assignment.model;

import com.tiaa.assignment.constants.Equipments;

public class Bolt implements Equipment {

	@Override
	public Equipments getEquipments() {
		return Equipments.Bolt;
	}
}
