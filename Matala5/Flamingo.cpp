/*
 * Flamingo.cpp
 *
 *  Created on: May 19, 2016
 *      Author: orenk
 */
#include "Flamingo.h"

Flamingo::Flamingo(const Flamingo& flamingo)
: Birds(flamingo)
,mAverageHeight(flamingo.mAverageHeight)
{
}

Flamingo::Flamingo(const std::string& color,
		Animal**& offspringlist, const int offspringamount,
		const int averagelifetime, const int incubationtime,
		const int averageheight)
:Birds(color, offspringlist, offspringamount, averagelifetime, incubationtime)
, mAverageHeight(averageheight)
{
}

void Flamingo::print() {
	std::cout << " " << mAverageHeight << std::endl;
}
