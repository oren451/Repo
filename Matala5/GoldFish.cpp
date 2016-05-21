/*
 * GoldFish.cpp
 *
 *  Created on: May 20, 2016
 *      Author: orenk
 */
#include "GoldFish.h"

GoldFish::GoldFish(const GoldFish& goldfish)
: Mammals(goldfish), Fish(goldfish),
  mAverageWeight(goldfish.mAverageWeight), mAverageLength(goldfish.mAverageLength)
{
}

GoldFish::GoldFish(const std::string& color, Animal**& offspringlist,
		const int offspringamount, const int averagelifetime,
		const int timemonthpregnancy,
		const int milkamountperday, const int dandruff, const int gills,
		const int averageweight, const int averagelength)
:Mammals(color, offspringlist, offspringamount, averagelifetime, timemonthpregnancy
		,milkamountperday)
,Fish(color, offspringlist, offspringamount,
		averagelifetime, dandruff, gills),
		mAverageWeight(averageweight), mAverageLength(averagelength)
{
}

void GoldFish::print() {
	std::cout << " " << mAverageWeight << " " << mAverageLength << std::endl;
}
