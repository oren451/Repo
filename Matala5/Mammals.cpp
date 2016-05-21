/*
 * Mammals.cpp
 *
 *  Created on: May 16, 2016
 *      Author: orenk
 */
#include "Mammals.h"

Mammals::Mammals(const Mammals& mammals): Animal(mammals),
mTimeMonthPregnancy(mammals.mTimeMonthPregnancy),
mMilkAmountPerDay(mammals.mMilkAmountPerDay)
{
}

Mammals::Mammals(const std::string& color, Animal**& offspringlist,
		const int offspringamount, const int averagelifetime,
		const int timemonthpregnancy, const int milkamountperday)
:Animal(color, offspringlist, offspringamount, averagelifetime),
 mTimeMonthPregnancy(timemonthpregnancy)
, mMilkAmountPerDay(milkamountperday)
{
}

void Mammals::print() {
	std::cout << " " << mTimeMonthPregnancy << " " << mMilkAmountPerDay;
}
