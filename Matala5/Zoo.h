/*
 * Zoo.h
 *
 *  Created on: May 21, 2016
 *      Author: orenk
 */
#pragma once;
#include<string>
#include<vector>

class Zoo {
private:
	const std::string mZooName;
	const std::string mAddress;
	const int mPrice;
	const std::string mOpeningHour;
	const std::string mClosingHour;
	const int mAnimalsAmount;
	std::vector<Animal*> mAnimalsList;

public:
	Zoo();

};


