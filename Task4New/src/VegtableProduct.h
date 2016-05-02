/*
 * VegtableProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once
#include "FarmProduct.h"

class VegtableProduct: public FarmProduct {

private:
	const int mVitaminAmount;

public:
	VegtableProduct(const string& name, int id,  int shelf, char row, int weight,
			 ExposureValue exposure,int supplierNumber, int seasonsnumber, int vitaminAmount);
	VegtableProduct(const VegtableProduct &vegtableproduct);
	virtual ~VegtableProduct();
	virtual int calculatePrice() const;
	virtual void print() const;

	/*virtual void write(ostream& out) const;
	virtual void read(istream& in);
	virtual void save(ofstream& out) const;
	virtual void load(ifstream& in);
	virtual void saveBin(ofstream& out) const;
	virtual void loadBin(ifstream& in);*/
};


