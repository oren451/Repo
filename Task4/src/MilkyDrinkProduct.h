/*
 * MilkyDrinkProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

class MilkyDrinkProduct: public MilkProduct
{
public:
	MilkyDrinkProduct();
	MilkyDrinkProduct(char* name, int id, ShelfRow place, int weight,
			ProductType type, ExposureValue exposure,int fat, MilkType milktype, int colorcount);
	virtual ~MilkyDrinkProduct();
	virtual int calculatePrice();
	virtual void print();
};

