/*
 * MilkyDrinkProduct.cpp
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
using namespace std;
MilkyDrinkProduct::MilkyDrinkProduct(): MilkProduct() {
}

MilkyDrinkProduct::~MilkyDrinkProduct() {
}

MilkyDrinkProduct::MilkyDrinkProduct(char* name, int id, ShelfRow place,
		int weight, ProductType type, ExposureValue exposure, int fat,
		MilkType milktype, int colorcount)
:MilkProduct(name, id, place, weight, type, exposure, fat, milktype,
		colorcount)
{
}

int MilkyDrinkProduct::calculatePrice() {
	return MilkProduct::calculatePrice();
}

void MilkyDrinkProduct::print() {
	MilkProduct::print();
}
