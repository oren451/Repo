/*
 * Horses.cpp
 *
 *  Created on: May 19, 2016
 *      Author: orenk
 */
#include "Horses.h"

Horses::Horses(const Horses& horses) : Mammals(horses)
,mRaceType(horses.mRaceType)
{
}

Horses::Horses(const std::string& color, Animal**& offspringlist,
		const int offspringamount, const int averagelifetime,
		const int timemonthpregnancy, const int milkamountperday,
		const std::string& racetype)
: Mammals(color, offspringlist, offspringamount, averagelifetime,
		timemonthpregnancy, milkamountperday)
, mRaceType(racetype)
{
}

void Horses::print() {
	std::cout << " " << mRaceType;
}
