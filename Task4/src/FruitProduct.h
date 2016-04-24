/*
 * FruitProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

class FruitProduct: public FarmProduct {

private:
	int mSugarAmount;

public:
	FruitProduct();
	FruitProduct(char* name, int id, ShelfRow place, int weight,
			ProductType type, ExposureValue exposure,int supplierNumber,FarmType farmtype
			,int seasonsnumber, int sugarAmount);
	virtual ~FruitProduct();
	virtual int calculatePrice();
	virtual void print();

};

