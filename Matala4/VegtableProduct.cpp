#include "VegtableProduct.h"
#include "iostream"

using namespace std;

VegtableProduct::VegtableProduct(const string& name, int id, int shelf, char row, int weight,
		ExposureValue exposure, int supplierNumber,
		int seasonsnumber, int vitaminAmount) :
				FarmProduct(name, id, shelf, row, weight, exposure, supplierNumber, Vegtable, seasonsnumber),
		mVitaminAmount(vitaminAmount)
{

}

VegtableProduct::~VegtableProduct() {
}

int VegtableProduct::calculatePrice() const{
	return FarmProduct::calculatePrice() + (mVitaminAmount * 2);
}

VegtableProduct::VegtableProduct(const VegtableProduct& vegtableproduct):
										FarmProduct(vegtableproduct),
										mVitaminAmount(vegtableproduct.mVitaminAmount) {
}

void VegtableProduct::print() const{

	FarmProduct::print();
	cout << " (" << mVitaminAmount << ") ";
}

void VegtableProduct::saveBin(ofstream & out) const
{
	FarmProduct::saveBin(out);
	out.write((char*)&mVitaminAmount, sizeof(mVitaminAmount));
}

void VegtableProduct::loadBin(ifstream & in)
{
	FarmProduct::loadBin(in);
	in.read((char*)&mVitaminAmount, sizeof(mVitaminAmount));
}

