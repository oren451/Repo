/*
 * Fish.cpp
 *
 *  Created on: May 19, 2016
 *      Author: orenk
 */
#include "Fish.h"

Fish::Fish(const Fish& fish) : Animal(fish)
,mDandruffAmount(fish.mDandruffAmount)
,mGillsAmount(fish.mGillsAmount)
{
}

Fish::Fish(const std::string& color, Animal**& offspringlist,
		const int offspringamount, const int averagelifetime, const int dandruff, const int gills)
: Animal(color, offspringlist, offspringamount, averagelifetime)
, mDandruffAmount(dandruff)
, mGillsAmount(gills)
{
}

void Fish::print() {
	std::cout << " " << mDandruffAmount << " " << mGillsAmount;
}
