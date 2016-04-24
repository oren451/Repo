#include "VegtableProduct.h"
#include "iostream"

VegtableProduct::VegtableProduct() : FarmProduct() {

	mVitaminAmount = 0;
}

VegtableProduct::VegtableProduct(char* name, int id, ShelfRow place, int weight,
		ProductType type, ExposureValue exposure, int supplierNumber, FarmType farmtype,
		int seasonsnumber, int vitaminAmount) : FarmProduct(name, id, place, weight, type, exposure, supplierNumber, farmtype, seasonsnumber)
{
	mVitaminAmount = vitaminAmount;
}

VegtableProduct::~VegtableProduct() {
}

int VegtableProduct::calculatePrice() {
	return FarmProduct::calculatePrice() + (mVitaminAmount * 2);;
}

void VegtableProduct::print() {

	FarmProduct:print();
	cout << " (" << mVitaminAmount << ") " << endl;
}
