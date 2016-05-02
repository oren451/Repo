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
/*
void VegtableProduct::write(ostream& out) const
{
	FarmProduct::write(out);
	out << "SUGAR AMOUNT: " << mVitaminAmount << endl;
}

void VegtableProduct::save(ofstream & out) const
{
	FarmProduct::save(out);
	out << mVitaminAmount << " ";
}

void VegtableProduct::load(ifstream & in)
{
	FarmProduct::load(in);
	in >> mVitaminAmount;
}

void VegtableProduct::saveBin(ofstream & out) const
{
	FarmProduct::saveBin(out);
	out.write((char*)&mVitaminAmount, sizeof(mVitaminAmount));
}

void VegtableProduct::loadBin(ifstream & in)
{
	VegtableProduct::loadBin(in);
	in.read((char*)&mVitaminAmount, sizeof(mVitaminAmount));
}
*/