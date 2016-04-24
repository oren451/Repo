#include "FruitProduct.h"
#include "iostream"

FruitProduct::FruitProduct() : FarmProduct() {

	mSugarAmount = 0;
}

FruitProduct::FruitProduct(char* name, int id, ShelfRow place, int weight,
		ProductType type, ExposureValue exposure, int supplierNumber, FarmType farmtype,
		int seasonsnumber, int sugarAmount) : FarmProduct(name, id, place, weight, type, exposure, supplierNumber, farmtype, seasonsnumber)
{
	mSugarAmount = sugarAmount;
}

FruitProduct::~FruitProduct() {
}

int FruitProduct::calculatePrice() {
	return FarmProduct::calculatePrice() + (mSugarAmount / 2);;
}

void FruitProduct::print() {

	FarmProduct:print();
	cout << " (" << mSugarAmount << ") " << endl;
}