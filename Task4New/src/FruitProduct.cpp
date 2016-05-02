#include "FruitProduct.h"
#include <iostream>
#include <string>

using namespace std;

FruitProduct::FruitProduct(const string& name, int id, int shelf, char row, int weight,
		ExposureValue exposure, int supplierNumber,
		int seasonsnumber, int sugarAmount) :
						FarmProduct(name, id, shelf, row, weight, exposure, supplierNumber, Fruit, seasonsnumber),
						mSugarAmount(sugarAmount)
{
	if(mSugarAmount<0 || mSugarAmount>100)
	{
		throw "wrong sugar amount";
	}
}

FruitProduct::~FruitProduct() {
}

int FruitProduct::calculatePrice() const{
	return FarmProduct::calculatePrice() + (mSugarAmount / 2);
}

void FruitProduct::print() const{

	FarmProduct::print();
	cout << " (" << mSugarAmount << ") ";
}

FruitProduct::FruitProduct(const FruitProduct& fruitproduct):
								FarmProduct(fruitproduct),
								mSugarAmount(fruitproduct.mSugarAmount)
{
}
/*
void FruitProduct::write(ostream& out) const
{
	FarmProduct::write(out);
	out << "SUGAR AMOUNT: " << mSugarAmount << endl;
}

void FruitProduct::save(ofstream & out) const
{
	FarmProduct::save(out);
	out << mSugarAmount << " ";
}

void FruitProduct::load(ifstream & in)
{
	FarmProduct::load(in);
	in >> mSugarAmount;
}

void FruitProduct::saveBin(ofstream & out) const
{
	FarmProduct::saveBin(out);
	out.write((char*)&mSugarAmount, sizeof(mSugarAmount));
}

void FruitProduct::loadBin(ifstream & in)
{
	FruitProduct::loadBin(in);
	in.read((char*)&mSugarAmount, sizeof(mSugarAmount));
}
 */
