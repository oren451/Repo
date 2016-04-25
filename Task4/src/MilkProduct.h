/*
 * MilkProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */

#pragma once

enum MilkType { Cheese, Yogurt, Drink, Other};

class MilkProduct: public Product
{
private:
	char* mName;
	int mFat;
	int mColorCount;
	MilkType mMilkType;

public:
	MilkProduct();
	MilkProduct(char* name, int id, ShelfRow place, int weight,
			ProductType type, ExposureValue exposure,int fat, MilkType milktype, int colorcount);
	virtual ~MilkProduct();
	virtual int calculatePrice();
	virtual void print();
};



