/*
 * Animal.cpp
 *
 *  Created on: May 16, 2016
 *      Author: orenk
 */
#include "Animal.h"
#include <iostream>

Animal::Animal() : mColor("Black"), mOffspringList(NULL), mOffspringAmount(0), mAverageLifetime(0) {
}

Animal::Animal(const std::string& color, Animal**& offspringlist,
		const int offspringamount, const int averagelifetime) : mColor(color), mOffspringAmount(offspringamount), mAverageLifetime(averagelifetime)
{
	mOffspringList = new Animal*[offspringamount];
	for (int i = 0;i < offspringamount; ++i) {
		mOffspringList[i] = offspringlist[i];
	}
}

Animal::~Animal() {
}

Animal::Animal(const Animal& animal) : mColor(animal.mColor), mOffspringAmount(animal.mOffspringAmount)
, mAverageLifetime(animal.mAverageLifetime)
{
	mOffspringList = new Animal*[animal.mOffspringAmount];
	for (int i = 0;i < animal.mOffspringAmount; ++i) {
		mOffspringList[i] = animal.mOffspringList[i];
	}
}

void Animal::print() {
	std::cout << mColor << " " << mAverageLifetime << " " << mOffspringAmount;
}
