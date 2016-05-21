/*
 * Birds.cpp
 *
 *  Created on: May 19, 2016
 *      Author: orenk
 */
#include "Birds.h"

Birds::Birds(const Birds& birds) : Animal(birds), mIncubatioTime(birds.mIncubatioTime)
{}

Birds::Birds(const std::string& color, Animal**& offspringlist,
		const int offspringamount, const int averagelifetime, const int incubationtime)
:Animal(color, offspringlist, offspringamount, averagelifetime)
, mIncubatioTime(incubationtime)
{
}

Birds::~Birds() {
}

void Birds::print() {
	std::cout << " " << mIncubatioTime;
}
