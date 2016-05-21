/*
 * Mermaid.cpp
 *
 *  Created on: May 20, 2016
 *      Author: orenk
 */
#include "Mermaid.h"

Mermaid::Mermaid(const Mermaid& mermaid)
: Mammals(mermaid), Fish(mermaid),
  mFirstName(mermaid.mFirstName), mLastName(mermaid.mLastName)
{
}

Mermaid::Mermaid(const std::string& color, Animal**& offspringlist,
		const int offspringamount, const int averagelifetime,
		const int timemonthpregnancy, const int milkamountperday,
		const int dandruff, const int gills, const std::string& firstname,
		const std::string& lastname)
:Mammals(color, offspringlist, offspringamount, averagelifetime, timemonthpregnancy
		,milkamountperday)
,Fish(color, offspringlist, offspringamount,
		averagelifetime, dandruff, gills),
		mFirstName(firstname), mLastName(lastname)
{
}

void Mermaid::print() {
	std::cout << " " << mFirstName << " " << mLastName << std::endl;
}
